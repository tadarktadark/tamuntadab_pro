package com.tdtd.tmtd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tdtd.tmtd.model.service.IChatService;

@Controller
public class ChatController {
	
	@Autowired
	private IChatService service;
	
	
	@GetMapping("/chatPage.do")
	public String chatPage(HttpServletRequest request) {
		request.setAttribute("title", "채팅");
		return "chat";
	}

}
