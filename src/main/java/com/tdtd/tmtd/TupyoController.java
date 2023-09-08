package com.tdtd.tmtd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tdtd.tmtd.model.service.ITupyoService;
import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoVo;

@Controller
public class TupyoController {
	
	@Autowired
	private ITupyoService service;
	
	@RequestMapping(value = "/tupyoPage.do")
	public String tupyoPage(Model model) {
		TupyoVo vo = service.getTupyo(1000000033);
		List<TupyoOptionVo> lists = service.getAllTupyoOption(1);
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		return "tupyo";
	}

}
