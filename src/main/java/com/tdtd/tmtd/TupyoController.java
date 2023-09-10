package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		TupyoVo vo = service.getTupyo(1000000033);//classId 받아야함
		List<TupyoOptionVo> lists = service.getAllTupyoOption(1);//tupySeq받아야함
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		model.addAttribute("title","투표");
		
		model.addAttribute("accountId","TMTD1");//accountId 세션에서 받아야함
		
		return "tupyo";
	}
	
	//투표하기
	@ResponseBody
	@RequestMapping(value = "/insertTupyoUser.do", method = RequestMethod.POST)
	public Map<String, Object> insertTupyoUser(@RequestBody TupyoUserVo vo)  throws Exception {
		int tuusOptionSeq = vo.getTuusOptionSeq();
		String tuusAccountId = vo.getTuusAccountId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuusOptionSeq", tuusOptionSeq);
		map.put("tuusAccountId", tuusAccountId);
		service.insertTupyoUser(map);
		
		List<TupyoOptionVo> tupyoOptionList = service.getAllTupyoOption(tuusOptionSeq);
		System.out.println("이거임?"+tupyoOptionList);

		List<?> resultList = service.getTupyoResult(tuusOptionSeq);
		System.out.println("리절트리스트"+resultList);
		Map<String, Object> optionMap = new HashMap<String, Object>();
		optionMap.put("tupyoOptionList",tupyoOptionList);
		optionMap.put("resultList",resultList);
		return optionMap;
	}
	
	@RequestMapping(value = "/reTupyo.do", method = RequestMethod.GET)
	public String reTupyo(String tuusAccountId,int tuusOptionSeq,Model model) {
		TupyoVo vo = service.getTupyo(1000000033);//classId 받아야함
		TupyoUserVo tupyoUserVo = new TupyoUserVo();
		tupyoUserVo.setTuusAccountId(tuusAccountId);
		tupyoUserVo.setTuusOptionSeq(tuusOptionSeq);
		service.delTupyoUser(tupyoUserVo);
		List<TupyoOptionVo> lists = service.getAllTupyoOption(1);//tupySeq받아야함
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		model.addAttribute("title","투표");
		return "tupyo";
	}
	
	@GetMapping("/checkVoted.do")
	public String checkVoted(String tuusAccountId, int tuopTupySeq) {
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuusAccountId", tuusAccountId);
		map.put("tuopTupySeq", tuopTupySeq);
		if(service.tupyoUserChk(map)==null) {
			result = "false";
		}
		return result;
		
	}
	



}
