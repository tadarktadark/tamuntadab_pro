package com.tdtd.tmtd;



import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.model.service.ElasticsearchService;
import com.tdtd.tmtd.model.service.ICareerService;
import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.vo.CareerVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InstrController {
	
	@Autowired
	ElasticsearchService searchService;
	
	@Autowired
	private IInstrService service;
	
	@Autowired
	private ICareerService careerService;
	
	//강사 게시판 페이지 이동
	@GetMapping("/instrList.do")
	public String instrList(Model model, @RequestParam(value="start", defaultValue = "1") int start, 
							@RequestParam(value="end", defaultValue = "12") int end) {
		log.info("InstrController instrList 이동");
		
		
		List<InstrVo> lists = service.getAllInstr("like");
		
		for (InstrVo instrVo : lists) {
			String subjectsMajorTitle = instrVo.getSubjectsMajorTitle();
			String subjectsTitle = instrVo.getSubjectsTitle();
			subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
			subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");
			
			instrVo.setSubjectsMajorTitle(subjectsMajorTitle);
			instrVo.setSubjectsTitle(subjectsTitle);
			
			//userBirth가 Date타입일 경우
//			LocalDate birthDate = instrVo.getUserProfileVo().get(0).getUserBirth().toInstant()
//					.atZone(ZoneId.systemDefault())
//					.toLocalDate();
			String birthDateString = instrVo.getUserProfileVo().get(0).getUserBirth();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime birthDateTime = LocalDateTime.parse(birthDateString, formatter);
			
			LocalDate birthDate = birthDateTime.toLocalDate();
			
			int age = Period.between(birthDate, LocalDate.now()).getYears();
			instrVo.setInprAge(age);
		};
		
		model.addAttribute("title","강사 조회");
		model.addAttribute("pageTitle", "강사 전체 리스트");
		model.addAttribute("lists", lists);
		return "instrList";
	}
	
	//강사 게시판 페이지 내 조회순서 변경시 작동 AJAX
	@ResponseBody
	@GetMapping("/instrView.do")
	public String instrView(@RequestParam(required = false) String order, HttpSession session) {
		log.info("###########order: {}", order);
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String accountId = userInfo != null ? userInfo.getUserAccountId() : null;
		
		List<InstrVo> lists = service.getAllInstr(order);
		for (InstrVo instrVo : lists) {
			String subjectsMajorTitle = instrVo.getSubjectsMajorTitle();
			String subjectsTitle = instrVo.getSubjectsTitle();
			subjectsMajorTitle = subjectsMajorTitle.replace("[", "").replace("]", "").replace("\"", "");
			subjectsTitle = subjectsTitle.replace("[", "").replace("]", "").replace("\"", "");
			
			instrVo.setSubjectsMajorTitle(subjectsMajorTitle);
			instrVo.setSubjectsTitle(subjectsTitle);
			
			String birthDateString = instrVo.getUserProfileVo().get(0).getUserBirth();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime birthDateTime = LocalDateTime.parse(birthDateString, formatter);
			
			LocalDate birthDate = birthDateTime.toLocalDate();
			
			int age = Period.between(birthDate, LocalDate.now()).getYears();
			instrVo.setInprAge(age);
			
			instrVo.getUserProfileVo().get(0).setUserAccountId(accountId);
		};
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		log.info("$$$$$$$$$$조회 ajax 결과 : {}", gson.toJson(lists));
		return gson.toJson(lists);		
	}
	
	//강사 게시판 페이지 내 강사 검색 실행 AJAX
	@ResponseBody
	@PostMapping(value ="/instrSearch.do", produces = "application/json;charset=UTF-8")
	public String instrSearch(@RequestBody Map<String, Object> formData, HttpSession session) throws IOException {
		log.info("########### instrSearch.do 받아온 값: {}", formData); 
		List<Map<String, Object>> resultList = searchService.search(formData, "instr_profile", 8);
		 
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String accountId = userInfo != null ? userInfo.getUserAccountId() : null;
		
		for (Map<String,Object> result : resultList) {
			log.info("%%%%%%%%%%%%%%%%%%%%%% result : {}", result);
		        if(result != null && result.containsKey("user_birth")) {
		            String birthDateStr = (String) result.get("user_birth");
		            if(birthDateStr != null){
		                birthDateStr= birthDateStr.split("T")[0]; //날짜 부분만 추출
		                int age = calculateAge(birthDateStr);
		                result.put("user_age", age);
		                log.info("%%%%%%%%%%%%%%%%%%%%%% user_age : {}", result.get("user_age"));
		            }

		        // login_info 필드 추가
		        result.put("login_info", accountId);
		    }
		}
		
		 Gson gson = new Gson();
		 
		 log.info("########### instrSearch.do 반환할 값: {}", gson.toJson(resultList)); 
		 return gson.toJson(resultList);
	}
	
	
	
	//elasticsearch 데이터 강사 만 나이 변환 메소드
	private int calculateAge(String birthDate) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dob = LocalDate.parse(birthDate, formatter);
	    LocalDate now = LocalDate.now();
	    
	    Period period = Period.between(dob, now);
	    
	    return period.getYears();
	}
	
	// 강사 상세 조회 페이지 이동
	@GetMapping("/instrDetail.do")
	public String instrDetail(Model model, @RequestParam(required = false) Map<String, Object> map) {
		if(map.get("loginId") == null || map.get("loginId").equals("")) {
			map.put("loginId", "null");
		}
		
		log.info("InstrController /instrDetail.do 실행 받아온 강사 아이디 : {} 로그인한 아이디 : {}", map.get("inprAccountId"), map.get("loginId"));
		InstrVo simpleVo = service.getOneInstrSimple(map);
		InstrVo profileVo = service.getOneInstrProfile((String)map.get("seq"));
		String userAccountId = simpleVo.getInprAccountId();
		Map<String, Object> classMap = new HashMap<String, Object>();
		classMap.put("userAccountId", userAccountId);
		classMap.put("start", 1);
		classMap.put("end", 5);
		
		List<InstrVo> classVo = service.getOneInstrClass(classMap);
		int cancelCount = service.getCountClassCancel(userAccountId);
		
		double successRate = (double)classVo.size() / (classVo.size() + cancelCount) * 100;
		String formattedSuccessRate = String.format("%.2f", successRate);
		
		int allClass = classVo.size() + cancelCount;
		
		Map<String, Object> reviewMap = new HashMap<String, Object>();
		reviewMap.put("userAccountId", userAccountId);
		reviewMap.put("order", "desc");
		reviewMap.put("start", 1);
		reviewMap.put("end", 5);
		
		List<ClassVo> instrReviewVo = service.getOneIntrReview(reviewMap);
		
		String nickname = simpleVo.getUserProfileVo().get(0).getUserNickname();
		
		model.addAttribute("title", "강사 조회");
		model.addAttribute("pageTitle", nickname+"님의 프로필");
		model.addAttribute("simpleVo", simpleVo);
		model.addAttribute("profileVo", profileVo);
		model.addAttribute("classVo", classVo);
		model.addAttribute("instrReviewVo", instrReviewVo);
		model.addAttribute("allClass", allClass);
		model.addAttribute("successRate", formattedSuccessRate);
		
		return "instrDetail";
	}
	
	//경력사항 탭 클릭시 ajax 실행
	@GetMapping("/instrCareerDetail.do")
	@ResponseBody
	public List<CareerVo> instrCareer(@RequestParam String userAccountId) {
		log.info("instrCareer.do 실행 받아온 강사 아이디: {}", userAccountId);
		List<CareerVo> careerVo = careerService.getOneInstrCareer(userAccountId);
		return careerVo;
	}
	
	//클래스 이력 클릭시 ajax 실행
	@GetMapping("/instrClassDetail.do")
	@ResponseBody
	public Map<String, Object> instrClass(@RequestParam Map<String, Object> map){
		log.info("instrClassDetail.do 받아온 map : {}",map);
		List<InstrVo> classVo = service.getOneInstrClass(map);
		
		if (classVo == null) {
	        classVo = new ArrayList<>(); // 빈 리스트 생성
	    }
		
		 Map<String, Object> response = new HashMap<>();
		    
		    response.put("historyVo", classVo);
		    response.put("hasMore", !classVo.isEmpty()); 
		
		return response;
	}
	
	//후기 클릭시 ajax 실행
	@GetMapping("/instrReviewDetail.do")
	@ResponseBody
	public Map<String, Object> instrReview(@RequestParam Map<String, Object> map){
		log.info("instrReviewDetail.do 받아온 map : {}",map);
		
		List<ClassVo> instrReviewVo = service.getOneIntrReview(map);
		
		if (instrReviewVo == null) {
	        instrReviewVo = new ArrayList<>(); // 빈 리스트 생성
	    }
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("instrReviewVo", instrReviewVo);
	    response.put("hasMore", !instrReviewVo.isEmpty());
	    
		return response;
	}
	
	//좋아요 클릭시 update
//	public String instrLike(@RequestParam Map<String, Object> map) {
//		log.info("instrLike 받아온 map : {}", map);
//		String type = map.get("type").toString();
//		String accountId =  map.get("loginId").toString();
//		InstrVo simpleVo = service.getOneInstrSimple(map);
//		simpleVo.
//		LikeViewUtils.like(type, accountId, null);
//		return null;
//	}
	
}
