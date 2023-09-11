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
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
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

@Service
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
		 	
		 	
	        List<String> subjects = (List<String>) formData.get("subject");
	        applySubjectCondition(boolQueryBuilder, subjects);

	        String nickname = (String) formData.get("nickname");
	        applyNicknameCondition(boolQueryBuilder, nickname);

	        String title = (String) formData.get("title");
	        applyTitleCondition(boolQueryBuilder, title);
	        
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
		        
		        String sortType = (String) formData.get("sort");
				applySortCondition(searchSourceBuilder, sortType);
				
		        SearchRequest searchRequest = new SearchRequest(index);
		        searchRequest.source(searchSourceBuilder);

		        SearchResponse searchResponse;
				try {
					searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
				} catch (IOException e) {
					logger.error("Elasticsearch query execution failed", e);
					throw new RuntimeException("Search failed", e);
				}
		        
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
		 
		 private void applySortCondition(SearchSourceBuilder builder,
                 String sortType){
					if(sortType!=null && !sortType.equals("")){
					switch(sortType){
					case "인기순":
					 builder.sort("like", SortOrder.DESC);
					 break;
					case "최신순":
					 builder.sort("regdate", SortOrder.DESC);
					 break;
					case "정확도순":
					 // Elasticsearch 기본 정확도 점수(_score)로 정렬
					 builder.sort("_score", SortOrder.DESC);
					 break;
					default:
						logger.warn("Unknown sort type: {}", sortType);
						break;
					}
				}

		 	}

	    private void applySubjectCondition(BoolQueryBuilder boolQueryBuilder,
	                                       List<String> subjects) {
	    	if(subjects != null && !subjects.isEmpty()) {
	            BoolQueryBuilder boolSubjectQueryBulider = QueryBuilders.boolQuery();
	            for(String subject : subjects){
	                MultiMatchQueryBuilder multiMatchQueryStringBuilder =
	                        QueryBuilders.multiMatchQuery(subject).field("subject").field("introduction");
	                boolSubjectQueryBulider.should(multiMatchQueryStringBuilder);
	            }
	            boolQueryBuilder.filter(boolSubjectQueryBulider);
	    	}
	    }

		private void applyNicknameCondition(BoolQueryBuilder boolQueryBuilder,
	                                        String nickname) {
	    	if(nickname != null && !nickname.equals("")) {
	    	   MatchPhrasePrefixQueryBuilder matchPhrasePrefixQB =
	               QueryBuilders.matchPhrasePrefixQuery("nickname", nickname).maxExpansions(5);
	    	   boolQueryBuilder.filter(matchPhrasePrefixQB);
	       }
	    }
		
		private void applyTitleCondition(BoolQueryBuilder boolQueryBuilder,
											String title) {
			if(title != null && !title.equals("")) {
			MatchPhrasePrefixQueryBuilder matchPhrasePrefixQB =
			QueryBuilders.matchPhrasePrefixQuery("title", title).maxExpansions(10);
			boolQueryBuilder.filter(matchPhrasePrefixQB);
			}
		}

		private void applyAgeCondition(BoolQueryBuilder queryBuilder,
	                                   Map<String,Object> ageMap){
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
		
		private void applyFeeCondition(BoolQueryBuilder queryBuilder,
												Map<String,Object> feeMap){
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

		private void applyGenderCondition(BoolQueryBuilder queryBuilder,
	                                      String gender){
		   if(gender!=null && !gender.equals("") && !gender.equals(("전체"))){
		       TermQueryBuilder termQB=QueryBuilders.termQuery("gender",gender);
		       queryBuilder.filter(termQB);
		   }
		}

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
		
		private void validateFormData(Map<String, Object> formData) {
		    if (!(formData.get("subject") instanceof List)) {
		        throw new IllegalArgumentException("Invalid subject type");
		    }
		    
		    if (!(formData.get("nickname") instanceof String)) {
		        throw new IllegalArgumentException("Invalid nickname type");
		    }
		    
		    if (!(formData.get("title") instanceof String)) {
		        throw new IllegalArgumentException("Invalid title type");
		    }
		    
		    validateAge(formData.get("age"));
		    
		    validateFee(formData.get("fee"));

		    if (!(formData.get("gender") instanceof String)) {
		        throw new IllegalArgumentException("Invalid gender type");
		    }
		    
			validateLocation(formData.get("location"));
			
			if (!(formData.get("page") instanceof Integer)) {
			    throw new IllegalArgumentException("Invalid page type");
			}
			
			if ( !(formData.get("sort") instanceof String) ) { 
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


