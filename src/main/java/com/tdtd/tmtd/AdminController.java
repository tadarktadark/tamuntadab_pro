package com.tdtd.tmtd;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
		pageVo.setTotalCount(adminService.countAdmin(map)); //총 게시물의 개수
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
	public String getUserList(@RequestParam Map<String, Object> map,
							@RequestParam(name= "page", defaultValue = "1",required = false)String page) {
		PagingVo pageVo = new PagingVo();
		pageVo.setTotalCount(adminService.countUser(map)); //총 게시물의 개수
		pageVo.setCountList(10); //출력될 게시글의 개수
		pageVo.setCountPage(5); // 화면에 몇 개의 페이지를 보여줄 건지 (페이지 그룹)
		pageVo.setTotalPage(pageVo.getTotalCount()); // 총 페이지의 개수
		pageVo.setPage(Integer.parseInt(page)); // 화면에서 선택된 페이지 번호
		pageVo.setStartPage(Integer.parseInt(page)); // 페이지 그룹의 시작 번호
		pageVo.setEndPage(pageVo.getCountPage()); // 끝 번호
		map.put("start",pageVo.getPage()*pageVo.getCountList()-(pageVo.getCountList()-1));
		map.put("end", pageVo.getPage()*pageVo.getCountList());
	    log.info("map: {} / page : {}", map, pageVo);
	    List<UserProfileVo> userLists = adminService.getUserList(map);
	    map.put("user", userLists);
	    map.put("page", pageVo);
	    return gson.toJson(map);
	}
	
	@RequestMapping(value="/admin/userJeongji.do",method = RequestMethod.POST)
	@ResponseBody
	public String userJeongji(@RequestParam Map<String, Object> map, Model model) {
		int n = adminService.setuserJeongji(map);
		if(n>0) {
			return"true";
		}else{
			return"false";
		}
	}
	
	@RequestMapping(value="/admin/userdetail.do", method = RequestMethod.GET)
	public String getUserDetail(@RequestParam String id ,Model model) {
		model.addAttribute("title","회원관리");
		model.addAttribute("pageTitle", "회원 목록");
		log.info("{}",id);
		UserProfileVo user = adminService.getuserDetail(id);
		model.addAttribute("user",user);
		return "/admin/userDetail";
	}
	
	@RequestMapping(value ="/admin/addIp.do",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String addIP(@RequestParam Map<String,Object> map, HttpSession session) {
		AdminVo admin = (AdminVo) session.getAttribute("adminInfo");
		map.put("admin", admin.getAdprId());
		adminService.addIp(map);
		return "<script>alert('추가 완료 되었습니다.');location.href='./adminInsert.do';</script>";
	}
	@RequestMapping(value ="/admin/addAdmin.do",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String addADMIN(@RequestParam Map<String,Object> map, HttpSession session) {
		AdminVo admin = (AdminVo) session.getAttribute("adminInfo");
		map.put("admin", admin.getAdprId());
		String auth = (String) map.get("auth");
		if(auth.equals("총관리자")) {
			map.put("auth", "T");
		}else if(auth.equals("회원관리자")) {
			map.put("auth", "M");
		}else if(auth.equals("결제관리자")) {
			map.put("auth", "P");
		}else if(auth.equals("게시판관리자")) {
			map.put("auth", "B");
		}
		int n = adminService.adminIdCheck(map);
		if(n>0) {
			return "<script>alert('이미 사용중인 ID 입니다.');location.href='./adminInsert.do';</script>";
		}else {
			adminService.addAdmin(map);
			return "<script>alert('추가 완료 되었습니다.');location.href='./adminInsert.do';</script>";
		}	
	}
	@RequestMapping(value = "/admin/adminDetail.do",method=RequestMethod.GET)
	public String adminDetail(@RequestParam String id, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map = adminService.adminDetail(id);
		log.info("{}",map);
		model.addAttribute("admin",map);
		return "/admin/adminDetail";
	}
	
	@RequestMapping(value="/admin/delAdmin.do",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String adminDelete(@RequestParam String adminId) {
		int n = adminService.delAdmin(adminId);
		if (n>0) {
			return "<script>alert('삭제되었습니다.');location.href='./adminList.do';</script>";
		}else {
			return "<script>alert('다시 시도해주세요');location.href='./adminList.do';</script>";
		}
	}
	@RequestMapping(value="/admin/restoreAdmin.do",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String restoreAdmin(@RequestParam String adminId) {
		int n = adminService.restoreAdmin(adminId);
		if (n>0) {
			return "<script>alert('복구 되었습니다.');location.href='./adminList.do'; </script>";
		}else {
			return "<script>alert('다시 시도해주세요');location.href='./adminList.do';</script>";
		}
	}
	@RequestMapping(value="/admin/delIP.do",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delIP(@RequestParam Map<String,Object> map) {
		int n = adminService.delIP(map);
		if (n>0) {
			return "<script>alert('삭제되었습니다.');location.href='./adminList.do';</script>";
		}else {
			return "<script>alert('다시 시도해주세요');location.href='./adminList.do';</script>";
		}
	}
	@RequestMapping(value="/admin/adminResetPW.do",method=RequestMethod.GET,produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String resetAdminPw(@RequestParam String adminId) {
		int n = adminService.resetAdminPw(adminId);
		if (n>0) {
			return "<script>alert('초기화 되었습니다.');location.href='./adminList.do';</script>";
		}else {
			return "<script>alert('다시 시도해주세요');location.href='./adminList.do';</script>";
		}
	}
	
	@RequestMapping(value="admin/adminLogout.do",method=RequestMethod.GET)
	@ResponseBody
	public String adminLogout() {
		return "<script>location.href='./admin.do';</script>";
	}
}

