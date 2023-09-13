package com.tdtd.tmtd;



import java.util.List;

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

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InstrController {
	
	@Autowired
	private IInstrService service;
	
	@GetMapping("/instrList.do")
	public String instrList(Model model) {
		log.info("InstrController instrList 이동");
		List<InstrVo> lists = service.getAllInstr("like");
		model.addAttribute("title","강사 조회");
		model.addAttribute("pageTitle", "강사 전체 리스트");
		model.addAttribute("lists", lists);
		return null;
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
	
}
