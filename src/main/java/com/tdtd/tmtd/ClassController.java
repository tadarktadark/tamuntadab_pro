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
		
		int n = cService.getClassListCount();
		System.out.println(""+n);
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
	public String subjectManage(Model model) {
		model.addAttribute("title", "관리자");
		model.addAttribute("pageTitle", "과목 관리");
		
		//과목 갯수 가져오기
		int subjectCount = sService.getSubjectListCount(); 
		log.info("###################총 과목 갯수 = {}", subjectCount);
		
		//현재 페이지 가져오기
		String pageAttr = (String) model.getAttribute("page");
		int thisPage;
		String pageAttribute = "1";
		if (pageAttr == null) {
		    thisPage = 1;
		} else {
		    try {
		        thisPage = Integer.parseInt(pageAttr);
		        if (thisPage < 1) {
		            thisPage = 1;
		        }
		    } catch (NumberFormatException e) {
		        thisPage = 1;
		        pageAttribute = String.valueOf(thisPage);
		    }
		}
		if(thisPage<1) {
			thisPage=1;
		}
		
		
		//페이지에 사용될 정보 담기
		PagingVo pVo = new PagingVo();
		pVo.setPage(thisPage);
		pVo.setCountList(10);
		pVo.setTotalCount(subjectCount);
		pVo.setCountPage(5);
		pVo.setTotalPage(pVo.getTotalPage());
		pVo.setStartPage(pVo.getPage());
		pVo.setEndPage(pVo.getPage());
		Map<String, Object> pagingMap = PagingUtils.paging(pageAttribute, pVo.getTotalCount(), pVo.getCountList(), pVo.getCountPage());
		
		log.info("###################페이지 {}",thisPage);
		
		//페이징 처리해서 처리할 리스트 가져오기
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("first", pagingMap.get("start"));
		listMap.put("last", pagingMap.get("end"));
		List<SubjectVo> subjectList = sService.getSubjectList(listMap);
		
		model.addAttribute("pVo", pVo);
		model.addAttribute("subjectList", subjectList);
		log.info("###############과목 페이징에 사용될 정보: {}, {}", pVo, subjectList);
		
		return "subjectManage";
	}
}
