package com.tdtd.tmtd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeonuiController {
	
	@GetMapping("/geonuiList.do")
	public String geonuiList() {
		return "geonuiList";
	}

}
