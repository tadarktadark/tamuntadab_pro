package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstrController {
	
	@GetMapping("/instrProfileForm.do")
	public String instrProfileForm(Model model) {
		model.addAttribute("title", "프로필");
		model.addAttribute("pageTitle", "소개 프로필 등록/수정");
		return "instrProfileForm";
	}

}
