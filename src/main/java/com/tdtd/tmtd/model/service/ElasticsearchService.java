package com.tdtd.tmtd.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ElasticsearchService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	
	/**
	 * Elasticsearch를 통한 검색 결과 반환 service<br>
	 * Map formData: 과목 subject(List String) / 이름 nickname(String)/ 제목 title(String)/ 나이 age(Map String, Integer 최소 ageGt, 최대 ageLt)/<br>
	 * 				수업료 fee(Map String, Integer 최소 feeGt, 최대 feeLt)/ 성별 gender(String)/ 지역 location(List String)<br>
	 * 				정렬기준(String "인기순","최신순","정확도순")<br>
	 * String index : 검색 데이터가 들어있는 인덱스(table)<br>
	 * String pageSize : 한번에 출력되는 데이터 개수
	 */
	 public List<Map<String, Object>> search(Map<String, Object> formData, String index, int pageSize) throws IOException {
		 	validateFormData(formData);
		 	BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		 	
		 	
	        List<String> subjects = (List<String>) formData.get("subjects");
	        applySubjectCondition(boolQueryBuilder, subjects);

	        String nickname = (String) formData.get("nickname");
	        applyNicknameCondition(boolQueryBuilder, nickname);

	        String title = (String) formData.get("title");
	        applyTitleCondition(boolQueryBuilder, title);
	        
	        List<Integer> classname = (List<Integer>) formData.get("classname");
	        applyClassNameCondition(boolQueryBuilder, classname);
	        
	        Map<String, Object> ageMap = (Map<String, Object>)formData.get("age");
	        applyAgeCondition(boolQueryBuilder, ageMap);
	        
	        Map<String, Object> feeMap = (Map<String, Object>)formData.get("fee");
	        applyFeeCondition(boolQueryBuilder, feeMap);

	        String gender = (String) formData.get("gender");
	        applyGenderCondition(boolQueryBuilder, gender);
	        
			List<String> locations = (List<String>) formData.get("location");
			applyLocationCondition(boolQueryBuilder, locations);
	        
			 SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		        searchSourceBuilder.query(boolQueryBuilder);
		        
		        // 페이징 설정
		        int pageNumber = (Integer) formData.get("page"); // 현재 페이지 번호
		        searchSourceBuilder.from(pageNumber * pageSize);
		    	searchSourceBuilder.size(pageSize);
		        
//		        String sortType = (String) formData.get("sort");
//				applySortCondition(searchSourceBuilder, sortType);
				
		        SearchRequest searchRequest = new SearchRequest(index);
		        searchRequest.source(searchSourceBuilder);

		        SearchResponse searchResponse;
				try {
					searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
				} catch (IOException e) {
					logger.error("Elasticsearch query execution failed", e);
					throw new RuntimeException("Search failed", e);
				}
		        
				log.info("$$$$$$$$$$$Elasticsearch Sevice 반환값 {}", searchResponse);
				return getSearchResult(searchResponse);
	    }
	 
	 
	 
		 private List<Map<String, Object>> getSearchResult(SearchResponse response){
			    SearchHits hits=response.getHits();
			    List<Map<String,Object>> resultList=new ArrayList<>();
			    
			    for(SearchHit hit:hits){
			        Map<String,Object> hitMap=hit.getSourceAsMap();
			        resultList.add(hitMap);
			    }
			    
			    return resultList;
			
			}
		 
		 // 검색 결과 출력 순서 설정 좋아요순/등록일순/기본(정확도순) -> 그밖에 원하는 order by 필드에 이름 설정해주면 됨
//		 private void applySortCondition(SearchSourceBuilder builder,
//                 String sort){
//					if(sort!=null && !sort.equals("")){
//					switch(sort){
//					case "like":
//					 builder.sort("like_count", SortOrder.DESC);
//					 break;
//					case "reg":
//					 builder.sort("regdate", SortOrder.DESC);
//					 break;
//					case "basic":
//					 // Elasticsearch 기본 정확도 점수(_score)로 정렬
//					 builder.sort("_score", SortOrder.DESC);
//					 break;
//					default:
//						logger.warn("Unknown sort type: {}", sort);
//						break;
//					}
//				}
//
//		 	}
		
		// subjects 일치 단어 검색
	    private void applySubjectCondition(BoolQueryBuilder boolQueryBuilder,
	                                       List<String> subjects) {
	    	if(subjects != null && !subjects.isEmpty()) {
	            BoolQueryBuilder boolSubjectQueryBulider = QueryBuilders.boolQuery();
	            for(String subject : subjects){
	                MultiMatchQueryBuilder multiMatchQueryStringBuilder =
//	                        QueryBuilders.multiMatchQuery(subject).field("subjects").field("inpr_subjects_major");
	                		QueryBuilders.multiMatchQuery(subject, "subjects", "inpr_subjects_major");
	                boolSubjectQueryBulider.should(multiMatchQueryStringBuilder);
	            }
	            boolQueryBuilder.filter(boolSubjectQueryBulider);
	    	}
	    }
	    
	    //nickname * nickname * 일치 단어 검색
		private void applyNicknameCondition(BoolQueryBuilder boolQueryBuilder,
	                                        String nickname) {
	    	if(nickname != null && !nickname.equals("")) {
//	    		MatchPhrasePrefixQueryBuilder matchPhrasePrefixQB =
//	    	               QueryBuilders.matchPhrasePrefixQuery("nickname", nickname).maxExpansions(5);
//	            boolQueryBuilder.filter(matchQB);
	    		 QueryBuilder qb = QueryBuilders.wildcardQuery("nickname", "*" + nickname + "*");
	    	     boolQueryBuilder.must(qb);
	       }
	    }
		
		//title OR content 일치 단어 검색
		private void applyTitleCondition(BoolQueryBuilder boolQueryBuilder, String title) {
		    if (title != null && !title.equals("")) {
		        MultiMatchQueryBuilder multiMatchQB = QueryBuilders.multiMatchQuery(title, "title", "content", "nickname")
		            .type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX)
		            .maxExpansions(10);
		        boolQueryBuilder.should(multiMatchQB);
		    }
		}
		
		private void applyClassNameCondition(BoolQueryBuilder boolQueryBuilder,
					List<Integer> classname) {
			if(classname != null && !classname.isEmpty()) {
				BoolQueryBuilder boolSubjectQueryBulider = QueryBuilders.boolQuery();
	            for(int c : classname){
	                MultiMatchQueryBuilder multiMatchQueryStringBuilder =
	                        QueryBuilders.multiMatchQuery(c).field("classname");
	                boolSubjectQueryBulider.should(multiMatchQueryStringBuilder);
	            }
	            boolQueryBuilder.filter(boolSubjectQueryBulider);
	    	}
//			if(classname != null && !classname.equals("")) {
//			MatchPhrasePrefixQueryBuilder matchPhrasePrefixQB =
//			QueryBuilders.matchPhrasePrefixQuery("classname", classname).maxExpansions(10);
//			boolQueryBuilder.filter(matchPhrasePrefixQB);
				
//				QueryBuilder qb = QueryBuilders.wildcardQuery("classname", "*" + classname + "*");
//	    	     boolQueryBuilder.must(qb);
//			}
		}

		// 나이 최소 최대 범위 검색 ageGt : 최소 나이값 / ageLt : 최대 나이값
		private void applyAgeCondition(BoolQueryBuilder queryBuilder,
	                                   Map<String,Object> ageMap){
			if(ageMap != null) {
		        Integer ageGt=ageMap.get("gt")==null || ageMap.get("gt").toString().isEmpty() ? 
		                        null : Integer.valueOf(ageMap.get("gt").toString());
		        Integer ageLt=ageMap.get("lt")==null || ageMap.get("lt").toString().isEmpty() ? 
		                        null : Integer.valueOf(ageMap.get("lt").toString());

		        RangeQueryBuilder rangeQB= QueryBuilders.rangeQuery(("age"));

		        if(ageGt!=null){
		            rangeQB.gte(ageGt);    
		        }

		        if(ageLt!=null){
		            rangeQB.lte(ageLt);    
		        }

		       queryBuilder.filter(rangeQB);
		    }
		
		}
		
		//수업료 최소 최대 범위 feeGt: 수업료 최소값 / feeLt: 수업료 최대값
		private void applyFeeCondition(BoolQueryBuilder queryBuilder,
												Map<String,Object> feeMap){
			if(feeMap != null) {
			    Integer feeGt=feeMap.get("gt")==null || feeMap.get("gt").toString().isEmpty() ? 
			         null : Integer.valueOf(feeMap.get("gt").toString());
			    Integer feeLt=feeMap.get("lt")==null || feeMap.get("lt").toString().isEmpty() ? 
			         null : Integer.valueOf(feeMap.get("lt").toString());

			    RangeQueryBuilder rangeQB= QueryBuilders.rangeQuery(("fee"));

			    if(feeGt!=null){
			        rangeQB.gte(feeGt);    
			    }

			    if(feeLt!=null){
			        rangeQB.lte(feeLt);    
			    }

				queryBuilder.filter(rangeQB);
		    }
		
		}

		// gender 성별 일치 검색
		private void applyGenderCondition(BoolQueryBuilder queryBuilder,
	                                      String gender){
		   if(gender!=null && !gender.equals("") && !gender.equals(("All"))){
			   MatchQueryBuilder matchQB = QueryBuilders.matchQuery("gender", gender.trim());
			   queryBuilder.filter(matchQB);
		   }
		}

		// location 지역 일치 검색
		private void applyLocationCondition(BoolQueryBuilder queryBuilder,
	                                        List<String> locations){
		    if(locations!=null && !locations.isEmpty()){
		        BoolQueryBuilder boolLocationQB=QueryBuilders.boolQuery();
		        for(String location:locations){
		            TermQueryBuilder termQB=QueryBuilders.termQuery("available_location",location).boost(2.0f);
		            boolLocationQB.should(termQB);
		        }
		        queryBuilder.filter(boolLocationQB);
		    }
		}
		
		// 각 필드별 formData 타입 유효값 검사
		private void validateFormData(Map<String, Object> formData) {
		    if (formData.containsKey("subjects") && !(formData.get("subjects") instanceof List)) {
		        throw new IllegalArgumentException("Invalid subject type");
		    }
		    
		    if (formData.containsKey("nickname") && !(formData.get("nickname") instanceof String)) {
		        throw new IllegalArgumentException("Invalid nickname type");
		    }
		    
		    if (formData.containsKey("title") && !(formData.get("title") instanceof String)) {
		        throw new IllegalArgumentException("Invalid title type");
		    }
		    
		    if (formData.containsKey("classname") && !(formData.get("classname") instanceof List)) {
		        throw new IllegalArgumentException("Invalid title type");
		    }
		    
		    if(formData.containsKey("age")) {
		    	validateAge(formData.get("age"));
		    }
		    
		    if(formData.containsKey("fee")) {
		    	validateFee(formData.get("fee"));
		    }
		    if (formData.containsKey("gender") && !(formData.get("gender") instanceof String)) {
		        throw new IllegalArgumentException("Invalid gender type");
		    }
		    
		    if(formData.containsKey("location")) {
			validateLocation(formData.get("location"));
		    }
			
			if (formData.containsKey("page") && !(formData.get("page") instanceof Integer)) {
			    throw new IllegalArgumentException("Invalid page type");
			}
			
			if (formData.containsKey("sort") && !(formData.get("sort") instanceof String) ) { 
				throw new IllegalArgumentException ("Invalid sort type"); 
			}
			
		}

		private void validateAge(Object ageObject) {
	    	if (!(ageObject instanceof Map)) {
	            throw new IllegalArgumentException("Invalid age type");
	        }
	        
	        Map<?, ?> ageMap = (Map<?, ?>) ageObject;
	        
	        for (Object key : ageMap.keySet()) {
	            if (!key.equals("gt") && !key.equals("lt")) {
	                throw new IllegalArgumentException(String.format(
	                    "Unknown key in the 'age' map: %s", key));
	            }
	            
	            Object value = ageMap.get(key);
	            
	            if (!(value == null || value instanceof Integer || value.toString().isEmpty())) { 
	                throw new IllegalArgumentException(String.format(
	                    "The 'age' map should contain only integer values or null or empty string. Invalid value: %s", value));
	            }
	        }	
	    }
		
		private void validateFee(Object feeObject) {
		    if (!(feeObject instanceof Map)) {
		        throw new IllegalArgumentException("Invalid fee type");
		    }
		    
		    Map<?, ?> feeMap = (Map<?, ?>) feeObject;
		    
		    for (Object key : feeMap.keySet()) {
		        if (!key.equals("gt") && !key.equals("lt")) {
		            throw new IllegalArgumentException(String.format(
		                "Unknown key in the 'fee' map: %s", key));
		        }
		        
		        Object value = feeMap.get(key);
		        
		        if (!(value == null || value instanceof Integer || value.toString().isEmpty())) { 
		            throw new IllegalArgumentException(String.format(
		                "The 'fee' map should contain only integer values or null or empty string. Invalid value: %s", value));
		        } 
		    }	
		}

		private void validateLocation(Object locationObject){
	    	if(!(locationObject instanceof List)){
	    		throw new IllegalArgumentException ("Invalid location type"); 
	    	}
	    	
	    	List<?> locationList = (List<?>) locationObject;
	    	
	    	for(Object item : locationList){
	    		if(!(item instanceof String)){
	    			throw new IllegalArgumentException ("The 'location' list should contain only strings."); 
	    		}
	    	}
	    }
	}


