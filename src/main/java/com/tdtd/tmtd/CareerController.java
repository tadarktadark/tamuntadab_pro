package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CareerController {
	
	@GetMapping("/instrCareer.do")
	public String instrCareer(Model model) {
		model.addAttribute("title","프로필");
		model.addAttribute("pageTitle", "경력 인증");
		return "instrCareer";
	}

}
