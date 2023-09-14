package com.tdtd.tmtd;



import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
import com.tdtd.tmtd.model.service.ElasticsearchService;
import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InstrController {
	
	@Autowired
	ElasticsearchService searchService;
	
	@Autowired
	private IInstrService service;
	
	@GetMapping("/instrList.do")
	public String instrList(Model model) {
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
	
	@ResponseBody
	@GetMapping("/instrView.do")
	public String instrView(@RequestParam(required = false) String order, HttpSession session) {
		log.info("###########order: {}", order);
		String accountId = (String)session.getAttribute("userInfo");
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
	
	@ResponseBody
	@PostMapping(value ="/instrSearch.do", produces = "application/json;charset=UTF-8")
	public String instrSearch(@RequestBody Map<String, Object> formData, HttpSession session) throws IOException {
		log.info("########### instrSearch.do 받아온 값: {}", formData); 
		List<Map<String, Object>> resultList = searchService.search(formData, "instr_profile", 8);
		 
		Object userInfo = session.getAttribute("userInfo");
		
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
		        result.put("login_info", userInfo);
		    }
		}
		
		 Gson gson = new Gson();
		 
		 log.info("########### instrSearch.do 반환할 값: {}", gson.toJson(resultList)); 
		 return gson.toJson(resultList);
	}
	
	@GetMapping("/instrProfileForm.do")
	public String instrProfileForm(HttpSession session, Model model, String accountId) {
//		String accountId = (String)session.getAttribute("userInfo").getAccountId;
		InstrVo vo = service.getMyInstrProfile(accountId);
		if(vo != null) {
			
			model.addAttribute("profile", vo);
		}
		model.addAttribute("title", "프로필");
		model.addAttribute("pageTitle", "소개 프로필 등록/수정");
		model.addAttribute("accountId", accountId);
		
		return "instrProfileForm";
	}

	@GetMapping("/eduLevelForm.do")
	public String eduLevelForm(Model model) {
		model.addAttribute("title", "학력추가");
		return "eduLevelForm";
	}
	
	@PostMapping("/insertInstrProfile.do")
	@ResponseBody
	public String insertInstrProfile(@RequestBody InstrVo vo) {
		
		System.out.println("###########전달받은 값"+ vo.toString());
	    String accountId = vo.getInprAccountId();
	    
	    InstrVo before = service.getMyInstrProfile(accountId);
	    int n = 0;
	    if(before == null) {
	        n = service.insertInstrProfile(vo);
	    } else {
	    	vo.setInprSeq(before.getInprSeq());
	    	 if (before.getInstrEduVo() != null && !before.getInstrEduVo().isEmpty()) {  
	 	        for (int i=0; i<before.getInstrEduVo().size(); i++) { 
	 	            InstrEduVo ined = before.getInstrEduVo().get(i);
	 	            if (!vo.getInstrEduVo().contains(ined)) { 
	 	                vo.getInstrEduVo().add(ined);  
	 	            }
	 	        }
	 	    }
	        n = service.updateInstrProfile(vo);
	    }
	    return (n>0)?"true":"false";
	}
	
	@PostMapping("/deleteInstrEdulevel.do")
	@ResponseBody
	public String deleteInstrEdulevel(String inedSeq) {
		int n = service.deleteInstrEdulevel(inedSeq);
		return (n>0)?"true":"false";
	}
	
	private int calculateAge(String birthDate) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dob = LocalDate.parse(birthDate, formatter);
	    LocalDate now = LocalDate.now();
	    
	    Period period = Period.between(dob, now);
	    
	    return period.getYears();
	}
	
}
