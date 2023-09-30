package com.tdtd.tmtd;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InstrInsertController {
	
	@Autowired
	private IInstrService service;

	//강사 상세 프로필 등록 화면 이동
		@GetMapping("/instrProfileForm.do")
		public String instrProfileForm(HttpSession session, Model model) {
			log.info("InstrController instrProfileForm.do 강사 프로필 작성화면");
			UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
			String accountId = userInfo.getUserAccountId();
			
			InstrVo vo = service.getMyInstrProfile(accountId);
			if(vo != null) {
				
				model.addAttribute("profile", vo);
			}
			model.addAttribute("title", "프로필");
			model.addAttribute("pageTitle", "소개 프로필 등록/수정");
			model.addAttribute("accountId", accountId);
			
			return "instrProfileForm";
		}
		
		//강사 상세 프로필 중 학력 window.open 페이지
		@GetMapping("/eduLevelForm.do")
		public String eduLevelForm(Model model) {
			model.addAttribute("title", "학력추가");
			return "eduLevelForm";
		}
		
		//강사 상세 프로필 DB insert/update 실행
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
		
		//강사 상세 프로필 중 DB에 저장된 학력 사항 삭제시 실행. 
		@PostMapping("/deleteInstrEdulevel.do")
		@ResponseBody
		public String deleteInstrEdulevel(String inedSeq) {
			int n = service.deleteInstrEdulevel(inedSeq);
			return (n>0)?"true":"false";
		}
	
}
