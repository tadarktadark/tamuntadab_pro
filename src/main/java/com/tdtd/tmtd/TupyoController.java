package com.tdtd.tmtd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tdtd.tmtd.model.service.ITupyoService;
import com.tdtd.tmtd.vo.TupyoVo;

@Controller
public class TupyoController {
	
	@Autowired
	private ITupyoService service;
	
	@RequestMapping(value = "/tupyoPage.do")
	public String tupyoPage(Model model) {
		TupyoVo vo = service.getTupyo(1000000033);
		model.addAttribute("vo",vo);
		return "tupyo";
	}

}
