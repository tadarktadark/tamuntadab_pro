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
		TupyoVo vo = service.getTupyo(1000000017);//classId 받아야함
		List<TupyoOptionVo> lists = service.getAllTupyoOption(2);//tupySeq받아야함
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		model.addAttribute("title","투표");
		
		model.addAttribute("accountId","TMTD1");//accountId 세션에서 받아야함
		
		return "tupyo";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertTupyoUser.do", method = RequestMethod.POST)
	public void insertTupyo(@RequestBody TupyoUserVo vo) {
		int tuusOptionSeq = vo.getTuusOptionSeq();
		String tuusAccountId = vo.getTuusAccountId();
		System.out.println("웨엥웨웨웨웽엥"+tuusOptionSeq);
		System.out.println(""+tuusAccountId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuusOptionSeq", tuusOptionSeq);
		map.put("tuusAccountId", tuusAccountId);
		service.insertTupyoUser(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "/tupyoResult.do",method = RequestMethod.GET)
	public Map<String, Object> tupyoResult(@RequestParam int tuusOptionSeq){
		List<TupyoOptionVo> tupyoOptionList = service.getAllTupyoOption(tuusOptionSeq);
		
		List<TupyoUserVo> resultList = service.getTupyoResult(tuusOptionSeq);
		System.out.println("리절트리스트"+resultList);
		Map<String, Object> optionMap = new HashMap<String, Object>();
		optionMap.put("tupyoOptionList",tupyoOptionList);
		optionMap.put("resultList",resultList);
		return optionMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agreeTupyo.do",method = RequestMethod.POST)
	public void agreeTupyo(int tuusOptionSeq,String tuusAccountId,String tuusAgree) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuusOptionSeq", tuusOptionSeq);
		map.put("tuusAccountId", tuusAccountId);
		service.insertTupyoUser(map);
		Map<String, Object> tupyoMap = new HashMap<String, Object>();
		tupyoMap.put("tuusOptionSeq", tuusOptionSeq);
		tupyoMap.put("tuusAgree", "A");
		List<TupyoUserVo> userList = service.getAgreeUser(tupyoMap);
		int seq = userList.get(0).getTuusSeq();
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("tuusSeq", seq);
		dataMap.put("tuusAgree", tuusAgree);
		service.updateAgreeTupyo(dataMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/agreeTupyoResult.do",method = RequestMethod.GET)
	public Map<String, Object> agreeUserResult(@RequestParam int tupySeq, @RequestParam int tuusOptionSeq){
		
		Map<String, Object> agreeMap = new HashMap<String, Object>();
		agreeMap.put("tuusOptionSeq", tuusOptionSeq);
		agreeMap.put("tuusAgree", "A");
		List<TupyoUserVo> agreeList = service.getAgreeUser(agreeMap);
		System.out.println(agreeList);
		Map<String, Object> disagreeMap = new HashMap<String, Object>();
		disagreeMap.put("tuusOptionSeq", tuusOptionSeq);
		disagreeMap.put("tuusAgree", "D");
		List<TupyoUserVo> disagreeList = service.getAgreeUser(disagreeMap);
		System.out.println(disagreeList);
		Map<String, Object> optionMap = new HashMap<String, Object>();
		optionMap.put("agreeCount", agreeList.size());
		optionMap.put("disagreeCount", disagreeList.size());
		System.out.println(optionMap);
		return optionMap;
	}
	

	
	
	
	
	@RequestMapping(value = "/reTupyo.do", method = RequestMethod.GET)
	public String reTupyo(int tupyClasId,String tuusAccountId,int tupySeq,Model model) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("tuusAccountId", tuusAccountId);
		userMap.put("tuopTupySeq", tupySeq);
		List<TupyoUserVo> userList = service.tupyoUserChk(userMap);
		System.out.println(userList);
		TupyoVo vo = service.getTupyo(tupyClasId);
		TupyoUserVo tupyoUserVo = new TupyoUserVo();
		tupyoUserVo.setTuusAccountId(tuusAccountId);
		tupyoUserVo.setTuusOptionSeq(userList.get(0).getTuusOptionSeq());
		service.delTupyoUser(tupyoUserVo);
		List<TupyoOptionVo> lists = service.getAllTupyoOption(tupySeq);
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		model.addAttribute("title","투표");
		return "tupyo";
	}
	
	@ResponseBody
	@GetMapping("/checkVoted.do")
	public String checkVoted(String tuusAccountId, int tuopTupySeq) {
		String result = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuusAccountId", tuusAccountId);
		map.put("tuopTupySeq", tuopTupySeq);
		System.out.println("투표 여부 체크"+service.tupyoUserChk(map));
		if(!service.tupyoUserChk(map).isEmpty()) {
			result = "voted";
			System.out.println("result 값 : "+result);
		}
		return result;
	}
	
	@ResponseBody
	@GetMapping("/finishTupyo.do")
	public void finishTupyo(int tupySeq) {
		service.endTupyo(tupySeq);
	}
	


}
