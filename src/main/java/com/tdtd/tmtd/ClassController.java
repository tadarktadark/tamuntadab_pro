package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IClassService;
import com.tdtd.tmtd.model.service.ISubjectService;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.PagingVo;
import com.tdtd.tmtd.vo.SubjectVo;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ClassController {

	@Autowired
	private IClassService cService;
	
	@Autowired
	private ISubjectService sService;
	
	@GetMapping("/classList.do")
	public String classList(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "클래스 목록");
		
		
		
		return "classList";
	}
	
	@GetMapping("/classListLoad.do")
	public String classListLoad(Model model,
							@RequestParam("page") String pageAttr) {
		
		
		int totalClass = cService.getClassListCount();
		
		log.info("ClassController subjectManage 가져온 현재 페이지 = {}", pageAttr);
		int thisPage = 0;
		if (pageAttr == null) {
		    thisPage = 1;
		} else {
			thisPage = Integer.parseInt(pageAttr);
		}
		
		log.info("ClassController subjectManage 형변환 한 페이지 = {}", thisPage);
		
		//페이지에 사용될 정보 담기
		PagingVo pVo = new PagingVo();
		pVo.setTotalCount(totalClass);
		pVo.setCountList(20);
		pVo.setCountPage(5);
		pVo.setPage(thisPage);
		pVo.setTotalPage(pVo.getTotalPage());
		pVo.setStartPage(pVo.getPage());
		pVo.setEndPage(pVo.getPage());
		
		log.info("ClassController 과목 페이징에 사용될 정보 pageVO, 과목정보 1 : {}", pVo);
		Map<String, Object> pagingMap = PagingUtils.paging(pageAttr, pVo.getTotalCount(), pVo.getCountList(), pVo.getCountPage());
			
		//페이징 처리해서 처리할 리스트 가져오기
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("first", pagingMap.get("start"));
		listMap.put("last", pagingMap.get("end"));
		List<ClassVo> classList = cService.getClassList(listMap);
		
		model.addAttribute("pVo", pVo);
		model.addAttribute("classList",classList);
		log.info("ClassController 과목 페이징에 사용될 정보 pageVO, 클래스 리스트 정보 : {}, {}", pVo, classList);
		return "classList";
	}

	@GetMapping("/classWrite.do")
	public String classWriteForm(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "클래스 생성");
		return "classWrite";
	}

	@PostMapping("classWrite.do")
	public String classWrite(Model model, 
	                         @RequestParam("classTitle") String classTitle,
	                         @RequestParam("subjCode") String subjCode,
	                         @RequestParam("location") String location,
	                         @RequestParam("clasSueopNaljja") String clasSueopNaljja,
	                         @RequestParam("clasHuimangInwon") int clasHuimangInwon,
	                         @RequestParam("classContent") String classContent,
	                         @RequestParam(value="clasSeongbyeolJehan", required=false) Integer clasSeongbyeolJehan, 
							 @RequestParam(value="minAge", required=false) Integer minAge, 
							 @RequestParam(value="maxAge", required=false) Integer maxAge, 
							 @RequestParam(value="clasChoisoSugangnyo", required=false) Integer clasChoisoSugangnyo, 
							 @RequestParam(value="clasChoidaeSugangnyo", required=false) Integer clasChoidaeSugangnyo
	                        ) {
	    
		System.out.println("classTitle: " + classTitle);
	    System.out.println("subjCode: " + subjCode);
	    System.out.println("location: " + location);
	    System.out.println("clasSueopNaljja: " + clasSueopNaljja);
	    System.out.println("clasHuimangInwon: " + clasHuimangInwon);
	    System.out.println("classContent: " + classContent);

		if(clasSeongbyeolJehan != null)
	    	System.out.println("clasSeongbyeolJehan: " + clasSeongbyeolJehan);

		if(minAge != null)
	    	System.out.println("minAge: " + minAge);

		if(maxAge != null)
	    	System.out.println("maxAge: " + maxAge);

		if(clasChoisoSugangnyo != null)
	    	System.out.println(clasChoisoSugangnyo); 

		if(clasChoidaeSugangnyo != null)
	    	System.out.println(clasChoidaeSugangnyo);
		
		
	    return "classList";
	}
	
	@GetMapping("/subjectManage.do")
	public String subjectManage(Model model,
								@RequestParam("page") String pageAttr) {
		model.addAttribute("title", "관리자");
		model.addAttribute("pageTitle", "과목 관리");
		
		//과목 갯수 가져오기
		int subjectCount = sService.getSubjectListCount(); 
		log.info("ClassController subjectManage 총 과목 갯수 = {}", subjectCount);
		
		//현재 페이지 가져오기
		log.info("ClassController subjectManage 가져온 현재 페이지 = {}", pageAttr);
		int thisPage = 0;
		if (pageAttr == null) {
		    thisPage = 1;
		} else {
			thisPage = Integer.parseInt(pageAttr);
		}
		
		log.info("ClassController subjectManage 형변환 한 페이지 = {}", thisPage);
		
		//페이지에 사용될 정보 담기
		PagingVo pVo = new PagingVo();
		pVo.setTotalCount(subjectCount);
		pVo.setCountList(10);
		pVo.setCountPage(5);
		pVo.setTotalPage(pVo.getTotalPage());
		pVo.setStartPage(pVo.getPage());
		pVo.setEndPage(pVo.getPage());
		pVo.setPage(thisPage);
		log.info("ClassController 과목 페이징에 사용될 정보 pageVO, 과목정보 1 : {}", pVo);
		Map<String, Object> pagingMap = PagingUtils.paging(pageAttr, pVo.getTotalCount(), pVo.getCountList(), pVo.getCountPage());
		
		
		//페이징 처리해서 처리할 리스트 가져오기
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("first", pagingMap.get("start"));
		listMap.put("last", pagingMap.get("end"));
		List<SubjectVo> subjectList = sService.getSubjectList(listMap);
		
		model.addAttribute("pVo", pVo);
		model.addAttribute("subjectList",subjectList);
		log.info("ClassController 과목 페이징에 사용될 정보 pageVO, 과목정보 : {}, {}", pVo, subjectList);
		
		return "subjectManage";
	}
	
	@GetMapping("/classDetail.do")
	public String classDetail(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "클래스 상세");
		return "classDetail";
	}
	
	@GetMapping("/myClass.do")
	public String myClass(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "참여 중인 클래스");
		return "myClass";
	}
}
