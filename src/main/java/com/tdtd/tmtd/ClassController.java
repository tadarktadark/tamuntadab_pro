package com.tdtd.tmtd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IClassService;
import com.tdtd.tmtd.model.service.ISubjectService;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.PagingVo;
import com.tdtd.tmtd.vo.SubjectVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ClassController {

	@Autowired
	private IClassService cService;

	@Autowired
	private ISubjectService sService;

	@GetMapping("/classList.do")
	public String classList(Model model, @RequestParam("page") String pageAttr) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "클래스 목록");

		return "classList";
	}

	@GetMapping("/classListLoad.do")
	@ResponseBody
	public String classListLoad(Model model, HttpSession session, @RequestParam("page") String pageAttr,
			@RequestParam("category") String category) {

		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		if (userInfo != null) {
			log.info("ClassController classListLoad 세션의 유저 정보: {}", userInfo);
		} else {
			log.info("ClassController classListLoad 세션의 유저 정보 : 정보없음");
		}

		// 받아 온 카테고리 정보를 Dao에 넣을 값으로 변환
		switch (category) {
		case "2":
			category = "개발 · 프로그래밍";
			break;
		case "3":
			category = "보안 · 네트워크";
			break;
		case "4":
			category = "데이터 사이언스";
			break;
		case "5":
			category = "게임 개발";
			break;
		case "6":
			category = "하드웨어";
			break;
		}

		// 세션과 조회할 목록에 따라 페이징 처리할 게시글의 총 갯수 지정
		int totalClass = 0;

		String userAuth = (userInfo != null) ? userInfo.getUserAuth() : null;

		if ("I".equals(userAuth)) {
			if ("1".equals(category) || category == null) {
				totalClass = cService.getAllClassListForICount();
			} else {
				totalClass = cService.getCategoryClassListForICount(category);
			}
		} else {
			if ("1".equals(category) || category == null) {
				totalClass = cService.getAllClassListForSCount();
			} else {
				totalClass = cService.getCategoryClassListForSCount(category);
			}
		}

		log.info("ClassController classListLoad 가져온 현재 페이지 = {}", pageAttr);
		int thisPage = 0;
		if (pageAttr == null) {
			thisPage = 1;
		} else {
			thisPage = Integer.parseInt(pageAttr);
		}

		log.info("ClassController classListLoad 형변환 한 페이지 = {}", thisPage);
		// 페이지에 사용될 정보 담기
		PagingVo pVo = new PagingVo();
		pVo.setTotalCount(totalClass);
		pVo.setCountList(20);
		pVo.setCountPage(5);
		pVo.setTotalPage(pVo.getTotalPage());
		pVo.setPage(thisPage);
		pVo.setStartPage(pVo.getPage());
		pVo.setEndPage(pVo.getPage());

		log.info("ClassController 페이징에 사용될 정보 pageVO : {}", pVo);
		Map<String, Object> pagingMap = PagingUtils.paging(pageAttr, pVo.getTotalCount(), pVo.getCountList(),
				pVo.getCountPage());

		// 페이징 처리해서 처리할 리스트를 상황별로 가져오기
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("first", pagingMap.get("start"));
		listMap.put("last", pagingMap.get("end"));

		List<ClassVo> classList;
		if ("I".equals(userAuth)) {
			if ("1".equals(category) || category == null) {
				classList = cService.getAllClassListForI(listMap);
			} else {
				listMap.put("subjCategory", category);
				classList = cService.getCategoryClassListForI(listMap);
			}
		} else {
			if ("1".equals(category) || category == null) {
				classList = cService.getAllClassListForS(listMap);
			} else {
				listMap.put("subjCategory", category);
				classList = cService.getCategoryClassListForS(listMap);
			}
		}

		String userAccountId = (userInfo != null) ? userInfo.getUserAccountId() : null;

		List<ClassVo> chamyeoClass;
		if (userAccountId != null) {
			chamyeoClass = cService.getChamyeoClass(userAccountId);
		} else {
			chamyeoClass = null;
		}

		Map<String, Object> result = new HashMap<>();
		result.put("pVo", pVo);
		result.put("classList", classList);
		if (chamyeoClass != null) {
			result.put("chamyeoClass", chamyeoClass);
		}
		log.info("ClassController 페이징에 쓰일 정보 pVo = {}", pVo);
		log.info("ClassController 페이징에 쓰일 정보 classList = {}", classList);

		Gson gson = new Gson();

		return gson.toJson(result);
	}

	@GetMapping("/classWrite.do")
	public String classWriteForm(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "클래스 생성");
		return "classWrite";
	}

	@PostMapping("classWrite.do")
	public String classWrite(Model model, @RequestParam("classTitle") String classTitle,
			@RequestParam("subject1") String subject1,
			@RequestParam(value = "subject2", defaultValue = "null") String subject2,
			@RequestParam(value = "subject3", defaultValue = "null") String subject3,
			@RequestParam("location") String location, @RequestParam("clasSueopNaljja") String clasSueopNaljja,
			@RequestParam("clasHuimangInwon") int clasHuimangInwon, @RequestParam("classContent") String classContent,
			@RequestParam("clasSeongbyeolJehan") String clasSeongbyeolJehan,
			@RequestParam(value = "minAge", defaultValue = "0") Integer minAge,
			@RequestParam(value = "maxAge", defaultValue = "0") Integer maxAge,
			@RequestParam(value = "clasChoisoSugangnyo", defaultValue = "0") Integer clasChoisoSugangnyo,
			@RequestParam(value = "clasChoidaeSugangnyo", defaultValue = "0") Integer clasChoidaeSugangnyo) {

		List<String> subjTitles = new ArrayList<String>();
		subjTitles.add(subject1);
		if (!"null".equals(subject2)) {
			subjTitles.add(subject2);
		}
		if (!"null".equals(subject3)) {
			subjTitles.add(subject3);
		}
		List<String> subjIds = cService.findSubjId(subjTitles);

		StringJoiner joiner = new StringJoiner(",", "[", "]");
		for (String subjId : subjIds) {
			joiner.add("\"" + subjId + "\"");
		}
		String subjIdsString = joiner.toString();

		if (clasSeongbyeolJehan == "2") {
			clasSeongbyeolJehan = "MONLY";
		} else if (clasSeongbyeolJehan == "3") {
			clasSeongbyeolJehan = "WONLY";
		} else {
			clasSeongbyeolJehan = "GFREE";
		}

		ClassVo cVo = new ClassVo();
		cVo.setClasTitle(classTitle);
		cVo.setClasSueopNaljja(clasSueopNaljja);
		cVo.setClasHuimangInwon(clasHuimangInwon);
		cVo.setClasContent(classContent);
		cVo.setClasSeongbyeolJehan(clasSeongbyeolJehan);
		cVo.setClasChoisoSugangnyo(clasChoisoSugangnyo);
		cVo.setClasChoidaeSugangnyo(clasChoidaeSugangnyo);
		cVo.setClasLocation(location);
		cVo.setClasSubjectJeongbo(subjIdsString);
		cVo.setClasStatus("모집");
		cVo.setClasNaiJehan("" + minAge + ":" + maxAge);
		log.info("만들어진 클래스 정보 = {}", cVo);

		ChamyeoVo cyVo = new ChamyeoVo();
		cyVo.setClchAccountId("TMTD64");
		cyVo.setClchStatus("Y");
		cyVo.setClchYeokal("M");

		cService.addClass(cVo, cyVo);

		return "classList";
	}

	@GetMapping("/subjectManage.do")
	public String subjectManage(Model model, @RequestParam("page") String pageAttr) {
		model.addAttribute("title", "관리자");
		model.addAttribute("pageTitle", "과목 관리");

		// 과목 갯수 가져오기
		int subjectCount = sService.getSubjectListCount();
		log.info("ClassController subjectManage 총 과목 갯수 = {}", subjectCount);

		// 현재 페이지 가져오기
		log.info("ClassController subjectManage 가져온 현재 페이지 = {}", pageAttr);
		int thisPage = 0;
		if (pageAttr == null) {
			thisPage = 1;
		} else {
			thisPage = Integer.parseInt(pageAttr);
		}

		log.info("ClassController subjectManage 형변환 한 페이지 = {}", thisPage);

		// 페이지에 사용될 정보 담기
		PagingVo pVo = new PagingVo();
		pVo.setTotalCount(subjectCount);
		pVo.setCountList(10);
		pVo.setCountPage(5);
		pVo.setTotalPage(pVo.getTotalPage());
		pVo.setPage(thisPage);
		pVo.setStartPage(pVo.getPage());
		pVo.setEndPage(pVo.getPage());
		log.info("ClassController 과목 페이징에 사용될 정보 pageVO, 과목정보 1 : {}", pVo);
		Map<String, Object> pagingMap = PagingUtils.paging(pageAttr, pVo.getTotalCount(), pVo.getCountList(),
				pVo.getCountPage());

		// 페이징 처리해서 처리할 리스트 가져오기
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("first", pagingMap.get("start"));
		listMap.put("last", pagingMap.get("end"));
		List<SubjectVo> subjectList = sService.getSubjectList(listMap);

		model.addAttribute("pVo", pVo);
		model.addAttribute("subjectList", subjectList);
		log.info("ClassController 과목 페이징에 사용될 정보 pageVO, 과목정보 : {}, {}", pVo, subjectList);

		return "subjectManage";
	}

	@GetMapping("/classDetail.do")
	public String classDetail(Model model, HttpSession session, @RequestParam("clasId") String clasId) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "클래스 상세");

		ClassVo cVo = cService.getClassDetail(clasId);
		log.info("ClassController 클래스 상세 페이지 조회, 가져온 클래스 정보 = {}", cVo);

		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		ChamyeoVo chamyeoVo;
		String userId = (userInfo != null) ? userInfo.getUserAccountId() : null;
		if (userId != null) {
			chamyeoVo = cService.getChamyeojaInfo(userId);
		} else {
			chamyeoVo = null;
		}
		log.info("ClassController 클래스 상세 페이지 조회, 가져온 참여자 정보 = {}", chamyeoVo);

		List<Integer> chamyeoClasIds;
		String userAccountId = (userInfo != null) ? userInfo.getUserAccountId() : null;
		List<ClassVo> cVoList;
		if (userAccountId != null) {
			cVoList = cService.getChamyeoClass(userAccountId);
		} else {
			cVoList = null;
		}
		if (cVoList != null) {
			chamyeoClasIds = cVoList.stream().map(ClassVo::getClasId).collect(Collectors.toList());
		} else {
			chamyeoClasIds = new ArrayList<>();
		}
		log.info("ClassController 클래스 상세 페이지 조회, 참여중인 클래스 정보 = {}", chamyeoClasIds);

		// 현재 페이지의 클래스 ID가 사용자가 참여중인 클래스 목록에 포함되어 있는지 판단
		boolean isJoined = chamyeoClasIds.contains(Integer.parseInt(clasId));
		
		model.addAttribute("isJoined", isJoined);
		model.addAttribute("cVo", cVo);
		model.addAttribute("chamyeoVo", chamyeoVo);

		return "classDetail";
	}

	@GetMapping("/myClass.do")
	public String myClass(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "참여 중인 클래스");
		return "myClass";
	}
	
	@GetMapping("/justGoMyClass.do")
	public String justGoMyClass(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "참여 중인 클래스");
		return "myClass";
	}
	
	@PostMapping("/calendarLoad.do")
	@ResponseBody
	public String calendarLoad(Model model, HttpSession session) {
		
		UserProfileVo userInfo = (UserProfileVo) session.getAttribute("userInfo");
		if (userInfo != null) {
			log.info("ClassController classListLoad 세션의 유저 정보: {}", userInfo);
		} else {
			log.info("ClassController classListLoad 세션의 유저 정보 : 정보없음");
		}
		
		String userAccountId = (userInfo != null) ? userInfo.getUserAccountId() : null;
		List<ClassVo> cVoList = cService.getChamyeoClass(userAccountId);
		log.info("ClassController 달력 로드에 쓰일 정보 pVo = {}", cVoList);
		
		Gson gson = new Gson();

		return gson.toJson(cVoList);
	}
}
