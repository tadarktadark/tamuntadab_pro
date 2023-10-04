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

/**
 * WOON 관리자페이지 흐름처리를 위한 컨트롤러 클래스
 * 
 * @author 임정운
 * 
 * @since 2023.09.23
 *
 */
@Controller
@Slf4j
public class AdminController {
	
	@Autowired
	private IAdminService adminService;
	
	Gson gson = new Gson();
	
	/**
	 * WOON 초기 접속시 인터셉터를 이용한 IP 확인 후 관리자 로그인을 위한 메소드
	 * 
	 * @param 첫 접속시 세션 삭제를 위한 session 파라미터
	 * 
	 * @return 관리자 로그인 폼 adminlogin.jsp 반환
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.23
	 * 
	 */
	@RequestMapping(value = "/admin/admin.do",method = RequestMethod.GET)
	public String adminPage(HttpSession session) {
		session.invalidate();
		return "/admin/adminlogin";
	}
	
	/**
	 * WOON 입력 값을 확인 및 판단하는 메소드 
	 * 
	 * 조회되는 값이 없을 경우 status : fail 반환
	 * 
	 * 조회된 값은 있으나 최근 접속 시간이 null인 경우 : 비밀번호 설정 Page로 이동하기 위한 "status", "setPW" 전송
	 * 
	 * 조회된 값이 있을 경우 key = "adminInfo", value = 해당 사용자의 정보 
	 * 		 T는 총관리자를 말하며 총관리자일 경우  세션 유지시간 10분 그 외 권한은 30분
	 * 
	 * @param adminInfo : 사용자가 입력한 입력 값을 담은 맵 {"adminId" : 사용자가 입력한 ID, "adminPW" : 사용자가 입력한 비밀번호}
	 * 
	 * @param session : 성공시 해당 정보를 세션에 담기위한 파라미터
	 * 
	 * @return 결과가 담긴 Map Gson으로 변환 후 전송
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.23
	 * 
	 */
	@RequestMapping(value = "/admin/adminLogin.do",method = RequestMethod.POST)
	@ResponseBody
	public String adminLogin(@RequestParam Map<String,Object> adminInfo, HttpSession session) {
		Map<String,Object> result = new HashMap<String, Object>();
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
	
	/**
	 * 로그인 성공 시 화면 이동을 위한 메소드
	 * 
	 * @return 관리자 페이지 이동을 위한 adminIndex.jsp 반환
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.23
	 * 
	 */
	@RequestMapping(value="/admin/adminMain.do",method = RequestMethod.GET)
	public String adminMainPage() {
		return "/admin/adminIndex";
	}
	/**
	 * 로그인을 성공했지만 비밀번호 설정을 위한 메소드
	 * 
	 * @return 관리자 비밀번호 변경을 위한 adminSetPassword.jsp 반환
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.23
	 * 
	 */
	@RequestMapping(value="/admin/adminSetPassword.do",method = RequestMethod.GET)
	public String setPasswordForm() {
		return "/admin/adminSetPassword";
	}
	
	/**
	 * 비밀번호 재설정 후 페이지 이동
	 * 
	 * 세션에서 ID를 가져온 후 update 서비스 실행
	 * 
	 * @param session : 비밀번호 재설정시 담아둔 세션
	 * 
	 * @param adminPW : 사용자가 입력한 비밀번호
	 * 
	 * @return 재로그인을 위한 adminlogin.jsp 반환
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.23
	 * 
	 */
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
	
	/**
	 * 관리자 목록을 조회 페이지 이동을 위한 메소드
	 * 
	 * @param model : 해당 페이지 정보를 사용자에게 알리기위한 model
	 *  
	 * @return 관리자 목록을 보여주는 adminList.jsp
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.24
	 * 
	 */
	@RequestMapping(value="/admin/adminList.do",method = RequestMethod.GET)
	public String adminList(Model model) {
		model.addAttribute("title","총 관리자");
		model.addAttribute("pageTitle", "관리자 목록");
		return "/admin/adminList";
	}
	
	/**
	 * 관리자 추가를 페이지 이동을 위한 메소드
	 * 
	 * @param model 해당 페이지 정보를 담기위한 model
	 * 
	 * @return 관리자 추가를 위한 페이지 adminInsert.jsp
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.24
	 * 
	 */
	@RequestMapping(value="/admin/adminInsert.do",method = RequestMethod.GET)
	public String adminInsert(Model model) {
			model.addAttribute("title","총 관리자");
			model.addAttribute("pageTitle", "관리자 추가");
			return "/admin/adminInsert";
	}
	
	/**
	 * 비동기식 처리를 이용한 관리자 목록을 반환하는 메소드
	 * 
	 * @param map 필터링 및 검색 등등 조건을 담은 Map 객체
	 * 
	 * 정렬
	 * key = "column" =>  정렬을 위한 컬럼명
	 * key = "value" =>  정렬 방식
	 *
	 * 필터
	 * key = "userAuth" =>  사용자 권한
	 * key = "userDelflag" =>  삭제 여부
	 * 
	 * 검색
	 * key = "tag" =>  검색 컬럼
	 * key = "searchValue" =>  검색 값
	 * 
	 * 페이징
	 * key = "start" =>  시작 번호
	 * key = "end" =>  종료 번호
	 * 
	 * @param page 현재 페이지를 반환받기 위한 String 객체
	 * 
	 * @return 조회된 정보를 담는 map을 gson형태로 변환하여 반환
	 * key = "admin" : value= "조회된 관리자 List" , key="page" : value="페이지 정보를 담는 페이지" 
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.25
	 * 
	 */
	@RequestMapping(value="/admin/searchAdminList.do",method = RequestMethod.GET)
	@ResponseBody
	public String searchAdminList(@RequestParam Map<String,Object> map, 
									@RequestParam(name= "page", defaultValue = "1",required = false)String page){
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
		
		List<AdminVo> adminList = adminService.getAdminList(map);
		
		map.put("admin", adminList);
		map.put("page", pageVo);
		return gson.toJson(map);
	}
	
	/**
	 * 사용자 목록을 조회 페이지 이동을 위한 메소드
	 * 
	 * @param model : 해당 페이지 정보를 사용자에게 알리기위한 model
	 *  
	 * @return 사용자 목록을 보여주는 userList.jsp
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.25
	 * 
	 */
	@RequestMapping(value="/admin/userList.do",method = RequestMethod.GET)
	public String userList(Model model){
		model.addAttribute("title","회원관리");
		model.addAttribute("pageTitle", "회원 목록");
		return "/admin/userList";
	}
	
	/**
	 * 비동기식 처리를 이용한 사용자 목록을 반환하는 메소드
	 * 
	 * @param map 필터링 및 검색 등등 조건을 담은 Map 객체
	 * 
	 * 정렬
	 * key = "column" =>  정렬을 위한 컬럼명
	 * key = "value" =>  정렬 방식
	 *
	 * 필터
	 * key = "userAuth" =>  사용자 권한
	 * key = "userSite" =>  사용자 가입 경로
	 * key = "userDelflag" =>  삭제 여부
	 * key = "userGender" =>  사용자 성별
	 * key = "userJeongJiSangTae" =>  사용자의 정지 상태
	 * key = "siusState" =>  사용자 신고 처리로 인해 정지가 필요한 상태
	 * 
	 * 검색
	 * key = "tag" =>  검색 컬럼
	 * key = "searchValue" =>  검색 값
	 * 
	 * 페이징
	 * key = "start" =>  시작 번호
	 * key = "end" =>  종료 번호
	 * 
	 * @param page 현재 페이지를 반환받기 위한 String 객체
	 * 
	 * @return 조회된 정보를 담는 map을 gson형태로 변환하여 반환
	 * key = "user" : value= "조회된 사용자 List" , key="page" : value="페이지 정보를 담는 페이지" 
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.26
	 * 
	 */
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
	
	/**
	 * 사용자 정지를 처리를 위한 메소드
	 * 
	 * @param map
	 * key = "userId" =>  정지 대상 ID
	 * key = "state" =>  정지 상태
	 * key = "jeongji_day" =>  정지 날짜
	 * 
	 * @return 정지 완료 유무
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.26
	 * 
	 */
	@RequestMapping(value="/admin/userJeongji.do",method = RequestMethod.POST)
	@ResponseBody
	public String userJeongji(@RequestParam Map<String, Object> map) {
		int n = adminService.setuserJeongji(map);
		if(n>0) {
			return"true";
		}else{
			return"false";
		}
	}
	
	/**
	 * 사용자의 상세 내용을 조회하기 위한 메소드
	 * 
	 * @param id 사용자의 ID를 담은 객체
	 * 
	 * @param model 조회된 사용자의 정보를 담은 객체
	 * 
	 * @return 사용자 정볼르 보기위한 userDetail.jsp
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.27
	 * 
	 */
	@RequestMapping(value="/admin/userdetail.do", method = RequestMethod.GET)
	public String getUserDetail(@RequestParam String id ,Model model) {
		model.addAttribute("title","회원관리");
		model.addAttribute("pageTitle", "회원 목록");
		UserProfileVo user = adminService.getuserDetail(id);
		model.addAttribute("user",user);
		return "/admin/userDetail";
	}
	
	/**
	 * 관리자 IP 추가를 위한 메소드
	 * 
	 * @param map 추가를 위해 입력한 값이 담긴 Map객체
	 * key = "ip" =>  추가 IP
	 * key = "adminID" =>  추가 대상 ID
	 * key = "admin" =>  추가한 사용자 ID
	 * 
	 * @param session 추가한 사용자 ID를 받기위한 객체
	 * 
	 * @return 추가 완료 여부를 alert으로 알림 후 이동
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.27
	 * 
	 */
	@RequestMapping(value ="/admin/addIp.do",method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String addIP(@RequestParam Map<String,Object> map, HttpSession session) {
		AdminVo admin = (AdminVo) session.getAttribute("adminInfo");
		map.put("admin", admin.getAdprId());
		int n = adminService.addIp(map);
		if(n>0) {
			return "<script>alert('추가 완료 되었습니다.');location.href='./adminInsert.do';</script>";
		}
			return "<script>alert('추가에 실패하였습니다.');location.href='./adminInsert.do';</script>";
	}
	
	/**
	 * 관리자 ID를 추가하기위한 메소드
	 * 
	 * 설명 : 사용자가 입력한 값을 객체에 담은 후 입력한 ID의 중복여부를 확인한 후 계정 추가 
	 * 
	 * @param map 추가를 위해 입력한 값이 담긴 Map객체
	 * key = "adminId" =>  관리자 ID
	 * key = "adminPW" =>  관리자 비밀번호
	 * key = "adminName" =>  관리자 이름
	 * key = "auth" =>  관리자 권한
	 * key = "admin" =>  추가한 관리자
	 * 
	 * @param session 추가한 사용자 ID를 받기 위한 객체
	 * 
	 * @return 추가 성공 여부
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.27
	 * 
	 */
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
	
	/**
	 * 관리자의 상세정보를 확인하기 위한 메소드
	 * 
	 * @param id 조회하기 위한 관리자 ID
	 * 
	 * @param model 해당 사용자의 정보를 담은 Map
	 * 
	 * @return 해당 사용자의 정보를 보기위한 adminDetail.jsp 반환
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.27
	 * 
	 */
	@RequestMapping(value = "/admin/adminDetail.do",method=RequestMethod.GET)
	public String adminDetail(@RequestParam String id, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		map = adminService.adminDetail(id);
		model.addAttribute("admin",map);
		return "/admin/adminDetail";
	}
	
	/**
	 * 관리자 삭제처리를 위한 메소드
	 * 
	 * @param adminId 삭제처리 하기위한 관리자 ID
	 * 
	 * @return 삭제 성공 여부
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.28
	 * 
	 */
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
	
	/**
	 * 관리자 복구처리를 위한 메소드
	 * 
	 * @param adminId 복구시키기 위한 대상 ID
	 * 
	 * @return 복구 성공 여부
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.28
	 * 
	 */
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
	
	/**
	 * 관리자 IP를 삭제하기 위한 메소드
	 * 
	 * @param map
	 * key = "ipAddr" =>  삭제하기 위한 IP
	 * key = "adpradmin" =>  등록한 관리자
	 * 
	 * @return 삭제 성공 여부
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.28
	 * 
	 */
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
	
	/**
	 * 관리자 비밀번호 초기화를 위한 메소드
	 * 
	 * @param adminId 초기화를 위한 IP
	 * 
	 * @return 초기화 성공 여부
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.28
	 * 
	 */
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
	
	/**
	 * 로그아웃을 위한 메소드
	 * 
	 * @return admin.do에서 세션을 삭제해주기 때문에 저기로 보냄
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.09.28
	 * 
	 */
	@RequestMapping(value="admin/adminLogout.do",method=RequestMethod.GET)
	@ResponseBody
	public String adminLogout() {
		return "<script>location.href='./admin.do';</script>";
	}
	/**
	 * WOON 관리자 페이지에서 메인으로 가는 메소드
	 * 
	 * @return 
	 * 
	 * @author 임정운
	 * 
	 * @since 2023.10.04
	 * 
	 */
	@RequestMapping(value="admin/backToMain.do",method=RequestMethod.GET)
	@ResponseBody
	public String backToMain() {
		return "<script>location.href='../main.do';</script>";
	}
}

