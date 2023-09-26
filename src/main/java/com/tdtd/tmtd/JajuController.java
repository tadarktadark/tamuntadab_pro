package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JajuController {
	
	@GetMapping("/fagList.do")
	public String fagList() {
		return "jaju";
	}
	

}
