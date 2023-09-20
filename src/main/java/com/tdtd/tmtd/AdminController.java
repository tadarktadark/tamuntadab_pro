package com.tdtd.tmtd;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tdtd.tmtd.model.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {
	
//	@Autowired
//	private IAdminService adminService;
	
	@RequestMapping(value = "admin.do",method = RequestMethod.GET)
	public String adminPage(HttpSession session) {
		session.invalidate();
		return "adminlogin";
	}
	@RequestMapping(value = "adminLogin.do",method = RequestMethod.POST)
	public String adminLogin() {
		return "adminindex";
	}
}
