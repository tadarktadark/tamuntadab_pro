package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.service.IAdminService;
import com.tdtd.tmtd.vo.AdminVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/admin/admin.do",method = RequestMethod.GET)
	public String adminPage(HttpSession session) {
		session.invalidate();
		return "/admin/adminlogin";
	}
	@RequestMapping(value = "/admin/adminLogin.do",method = RequestMethod.POST)
	@ResponseBody
	public String adminLogin(@RequestParam Map<String,Object> adminInfo, HttpSession session) {
		Map<String,Object> result = new HashMap<String, Object>();
		log.info("{}",adminInfo);
		AdminVo vo = adminService.adminLogin(adminInfo);
		if(vo==null) {
			result.put("status", "fail");
			return gson.toJson(result);
		}else if (vo.getAdprLastAccess()==null){
			result.put("status", "setPW");
			session.setAttribute("adminInfo", vo);
			return gson.toJson(result);
		}else {
			result.put("status", "success");
			session.setAttribute("adminInfo", vo);
			adminService.updateAdminAccTime(vo);
			if(vo.getAdprAuth().equals("T")) {
				session.setMaxInactiveInterval(600);
				return gson.toJson(result);
			}else {
				session.setMaxInactiveInterval(1800);
				return gson.toJson(result);
			}
		}
	}
	@RequestMapping(value="/admin/adminMain.do",method = RequestMethod.GET)
	public String adminMainPage(HttpServletResponse response) {
		return "/admin/adminIndex";
	}
	
	@RequestMapping(value="/admin/adminSetPassword.do",method = RequestMethod.GET)
	public String setPasswordForm() {
		return "/admin/adminSetPassword";
	}
	@RequestMapping(value="/admin/adminSetPassword.do",method = RequestMethod.POST)
	public String setPassword(HttpSession session, @RequestParam String adminPW) {
		AdminVo adminInfo = (AdminVo) session.getAttribute("adminInfo");
		Map<String,Object> setPW = new HashMap<String,Object>();
		setPW.put("adminId", adminInfo.getAdprId());
		setPW.put("adminPW", adminPW);
		adminService.updateAdminPw(adminInfo, setPW);
		session.invalidate();
		return "/admin/adminlogin";
	}
	
	@RequestMapping(value="/admin/adminList.do",method = RequestMethod.GET)
	public String adminList(Model model) {
		model.addAttribute("title","총 관리자");
		model.addAttribute("pageTitle", "관리자 목록");
		return "/admin/adminList";
	}
	@RequestMapping(value="/admin/adminInsert.do",method = RequestMethod.GET)
	public String adminInsert(Model model) {
		model.addAttribute("title","총 관리자");
		model.addAttribute("pageTitle", "관리자 추가");
		return "/admin/adminInsert";
	}
	@RequestMapping(value="/admin/searchAdminList.do",method = RequestMethod.GET)
	@ResponseBody
	public String searchAdminList(Map<String,Object> map) {
		List<AdminVo> adminList = adminService.getAdminList(map);
		map.put("admin", adminList);
		return gson.toJson(map);
	}
}
