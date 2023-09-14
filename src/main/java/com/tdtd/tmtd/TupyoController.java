package com.tdtd.tmtd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.ITupyoService;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;

@Controller
public class TupyoController {
	
	@Autowired
	private ITupyoService service;
	
	//투표 페이지로 이동
	@RequestMapping(value = "/tupyoPage.do")
	public String tupyoPage(int clasId,String accountId,Model model) {
		TupyoVo vo = service.getTupyo(clasId);
		if(vo==null) {
			model.addAttribute("hasTupyo", "false");
			model.addAttribute("accountId",accountId);
			return "tupyo";
		}
		model.addAttribute("hasTupyo", "true");
		
		List<TupyoOptionVo> lists = service.getAllTupyoOption(vo.getTupySeq());
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		model.addAttribute("title","투표");
		ChamyeoVo masterVo = service.getClassMaster(clasId);
		String masterId = masterVo.getClchAccountId();
		String isMaster ="false";
		if(masterId.equals(accountId)) {
			isMaster = "true";
		}
		model.addAttribute("isMaster", isMaster);
		model.addAttribute("accountId",accountId);
		return "tupyo";
	}
	
	
	@PostMapping("/insertTupyoOption.do")
	public void insertTupyoOption(int clasId, String tuopInstr, int tuopFee) {
		TupyoVo vo = service.getTupyo(clasId);
		int tuopTupySeq = vo.getTupySeq(); 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuopTupySeq", tuopTupySeq);
		map.put("tuopInstr", tuopInstr);
		map.put("tuopFee", tuopFee);
		service.insertTupyoOption(map);
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
	public boolean finishTupyo(int tupySeq) {
		TupyoVo vo = service.getTupyo(tupySeq);
		List<TupyoUserVo> resultList = service.getTupyoResult(tupySeq);
		if(resultList.size()==1) {
			//찬반 수 계산해서 확정 후 종료
		}else {
			
			int maxCount = 0;
			for (TupyoUserVo result : resultList) {
			    int count = result.getCount();
			    if (count > maxCount) {
			        maxCount = count;
			    }
			}
			System.out.println(maxCount);
			List<TupyoUserVo> maxOptionList = new ArrayList<TupyoUserVo>();
			for (TupyoUserVo result : resultList) {
				int count = result.getCount();
				if(count==maxCount) {
					maxOptionList.add(result);
				}
			}
			System.out.println(maxOptionList);
			if(maxOptionList.size() > 1) {
				System.out.println("동률 발생 재투표 진행");
				return false;
			}
			//아래의 option seq로 선생 정보를 받아와서 accountId를 확정 강사에 넣어준다
			int optionSeq = maxOptionList.get(0).getTuusOptionSeq();
			TupyoOptionVo maxOptionVo = service.getTupyoOption(optionSeq);
			String clasAccountId = maxOptionVo.getTuopInstr();
			int clasId = vo.getTupyClasId();
			Map<String, Object> insertMap = new HashMap<String, Object>();
			insertMap.put("clasAccountId", clasAccountId);
			insertMap.put("clasId", clasId);
			service.updateClasAccountId(insertMap);
			service.endTupyo(tupySeq);
			
		}
		
		//투표 안한 인원만큼 무효표 처리
		
		//최다 득표수 강사 조회해서 클래스에 확정 강사로 등록
		
		
		//투표 상태 '종료'로 변경
		return true;
		
		
		
		//투표를 안한 인원은 무효표로 처리
		//결과에 맞는 강사가 클래스 accountId에 추가되고 매칭 완료로 상태 변경
	}
	
	@PostMapping("/makeTupyo.do")
	public String makeTupyo(int tupyClasId,Date tupyEnddate) {
		
		Map<String, Object> map= new HashMap<String, Object>();
		int tupyTotalUser = service.countTotalClassMember(tupyClasId);
		map.put("tupyClasId", tupyClasId);
		map.put("tupyTotalUser", tupyTotalUser);
		map.put("tupyEnddate", tupyEnddate);
		service.insertTupyo(map);
		
		
		int tuopTupySeq = service.getTupyo(tupyClasId).getTupySeq();
		
		List<ChamyeoVo> instrList = service.getAllInstr(tupyClasId);
		System.out.println(instrList);
		for (ChamyeoVo chamyeoVo : instrList) {
			String tuopInstr = chamyeoVo.getClchAccountId();
			int tuopFee = chamyeoVo.getClchInstrSugangryo();
		
			Map<String, Object> insertMap = new HashMap<String, Object>();
			insertMap.put("tuopTupySeq", tuopTupySeq);
			insertMap.put("tuopInstr", tuopInstr);
			insertMap.put("tuopFee", tuopFee);
			service.insertTupyoOption(insertMap);
		}
		
		
		return "redirect:/tupyoPage.do";
	}
	


}
