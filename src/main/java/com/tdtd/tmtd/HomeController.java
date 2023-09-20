package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/main.do")
	public String home() {
		return "redirect:/";
	}
	@RequestMapping(value = "/error.do")
	public String error() {
		return "";
	}
}
