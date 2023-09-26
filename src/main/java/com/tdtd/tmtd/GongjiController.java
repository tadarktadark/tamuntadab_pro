package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GongjiController {
	
	@GetMapping("/gongjiList.do")
	public String gongjiList() {
		return "gongjiList";
	}

}
