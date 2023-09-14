package com.tdtd.tmtd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tdtd.tmtd.model.service.IPilgiService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PilgiController {

	@Autowired
	private IPilgiService service;
	
	@RequestMapping(value="/pilgi.do", method=RequestMethod.GET)
	public String pilgi(Model model) {
		log.info("@@@@@@@@@@@@@@@ 필기 게시판 이동");
		model.addAttribute("title","커뮤니티");
		model.addAttribute("pageTitle", "필기");
		return "pilgi";
	}
	
}
