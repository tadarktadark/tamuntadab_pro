package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@RequestMapping(value = "/regist.do", method=RequestMethod.GET)
	public String registForm() {
		log.info("###regist.do###");
		return "registpage";
	}
}