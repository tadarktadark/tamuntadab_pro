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
import com.tdtd.tmtd.vo.PagingVo;
import com.tdtd.tmtd.vo.UserProfileVo;

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
	public String adminInsert(Model model, HttpSession session) {
		AdminVo adminInfo = (AdminVo) session.getAttribute("adminInfo");
		if(adminInfo.getAdprAuth().equals("T")){
			model.addAttribute("title","총 관리자");
			model.addAttribute("pageTitle", "관리자 추가");
			return "/admin/adminInsert";
		}else{
			return "redirect:/admin/adminMain.do";
		}
	}
	@RequestMapping(value="/admin/searchAdminList.do",method = RequestMethod.GET)
	@ResponseBody
	public String searchAdminList(@RequestParam Map<String,Object> map, 
									@RequestParam(name= "page", defaultValue = "1",required = false)String page,
									Model model){
		log.info("page:{}",page);
		PagingVo pageVo = new PagingVo();
		pageVo.setTotalCount(adminService.countAdmin()); //총 게시물의 개수
		pageVo.setCountList(10); //출력될 게시글의 개수
		pageVo.setCountPage(5); // 화면에 몇 개의 페이지를 보여줄 건지 (페이지 그룹)
		pageVo.setTotalPage(pageVo.getTotalCount()); // 총 페이지의 개수
		pageVo.setPage(Integer.parseInt(page)); // 화면에서 선택된 페이지 번호
		pageVo.setStartPage(Integer.parseInt(page)); // 페이지 그룹의 시작 번호
		pageVo.setEndPage(pageVo.getCountPage()); // 끝 번호
		map.put("start",pageVo.getPage()*pageVo.getCountList()-(pageVo.getCountList()-1));
		map.put("end", pageVo.getPage()*pageVo.getCountList());
		log.info("PageVo : {}",pageVo);
		List<AdminVo> adminList = adminService.getAdminList(map);
		map.put("admin", adminList);
		map.put("page", pageVo);
		return gson.toJson(map);
	}
	
	@RequestMapping(value="/admin/userList.do",method = RequestMethod.GET)
	public String userList(Model model){
		model.addAttribute("title","회원관리");
		model.addAttribute("pageTitle", "회원 목록");
		return "/admin/userList";
	}
	
	@RequestMapping(value="/admin/getUserList.do",method = RequestMethod.GET)
	@ResponseBody
	public String getUserList(@RequestParam Map<String, Object> map) {
	    log.info("map: {}", map);
	    map.put("start", 1);
	    map.put("end", 10);
	    map.put("userAuth", map.get("filter[userAuth]"));
	    map.put("userSite", map.get("filter[userSite]"));
	    map.put("userDelflag", map.get("filter[userDelflag]"));
	    map.put("userJeongJiSangTae", map.get("filter[userJeongJiSangTae]"));
	    map.put("userGender", map.get("filter[userGender]"));
	    log.info("map: {}", map.toString());
	    List<UserProfileVo> userLists = adminService.getUserList(map);
	    map.put("user", userLists);
	    return gson.toJson(map);
	}
}

