package com.tdtd.tmtd;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.vo.InstrVo;

@Controller
public class InstrController {
	
	@Autowired
	private IInstrService service;
	
	@GetMapping("/instrProfileForm.do")
	public String instrProfileForm(HttpSession session, Model model) {
//		String accountId = (String)session.getAttribute("userAccountId");
		model.addAttribute("title", "프로필");
		model.addAttribute("pageTitle", "소개 프로필 등록/수정");
		model.addAttribute("accountId", "TMTD143");
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
	        n = service.updateInstrProfile(vo);
	    }
	    return (n>0)?"저장에 성공하였습니다":"처리중 오류 발생";
	}
	
}
