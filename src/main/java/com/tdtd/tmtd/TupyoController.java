package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.ITupyoService;
import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;

@Controller
public class TupyoController {
	
	@Autowired
	private ITupyoService service;
	
	//투표 페이지로 이동
	@RequestMapping(value = "/tupyoPage.do")
	public String tupyoPage(Model model) {
		TupyoVo vo = service.getTupyo(1000000033);
		List<TupyoOptionVo> lists = service.getAllTupyoOption(1);
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		model.addAttribute("title","투표");
		return "tupyo";
	}
	
	//투표하기
	@ResponseBody
	@RequestMapping(value = "/insertTupyoUser.do", method = RequestMethod.POST)
	public List<TupyoOptionVo> insertTupyoUser(@RequestBody TupyoUserVo vo)  throws Exception {
		int tuusOptionSeq = vo.getTuusOptionSeq();
		String tuusAccountId = vo.getTuusAccountId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuusOptionSeq", tuusOptionSeq);
		map.put("tuusAccountId", tuusAccountId);
		service.insertTupyoUser(map);
		
		List<TupyoOptionVo> tupyoOptionList = service.getAllTupyoOption(tuusOptionSeq);
		System.out.println("이거임?"+tupyoOptionList);
		return tupyoOptionList;
	}
	



}
