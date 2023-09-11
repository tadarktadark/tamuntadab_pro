package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassController {

	@GetMapping("/classList.do")
	public String classList(Model model) {
		model.addAttribute("title", "클래스");
		model.addAttribute("pageTitle", "클래스 목록");
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

}
