package com.tdtd.tmtd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.tdtd.tmtd.model.service.IAlarmService;
import com.tdtd.tmtd.model.service.ITupyoService;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;
import com.tdtd.tmtd.vo.UserProfileVo;

@Controller
public class TupyoController {
	
	@Autowired
	private ITupyoService service;
	@Autowired
	private IAlarmService alarmService;
	
	
	//투표 페이지로 이동
	@RequestMapping(value = "/tupyoPage.do")
	public String tupyoPage(int clasId,String accountId,Model model, HttpSession session) {
		TupyoVo vo = service.getTupyo(clasId);
		System.out.println("vo 체크 : "+vo);
		if(vo==null) {
			model.addAttribute("hasTupyo", "false");
			model.addAttribute("accountId",accountId);
			ChamyeoVo masterVo = service.getClassMaster(clasId);
			String masterId = masterVo.getClchAccountId();
			String isMaster ="false";
			if(masterId.equals(accountId)) {
				isMaster = "true";
			}
			model.addAttribute("clasId",clasId);
			model.addAttribute("isMaster", isMaster);
			return "tupyo";
		}else {
			//투표가 있는데 F이면 usercount체크하고 비어 있으면 삭제하고 새로고침
			
			System.out.println(vo.getTupyStatus());
			if(vo.getTupyStatus().equals("F")) {
				int tupySeq = vo.getTupySeq();
				System.out.println(tupySeq);
				int countVotedUser = service.countVotedUser(tupySeq);
				System.out.println(countVotedUser);
				if(countVotedUser == 0) {
					service.delTupyo(tupySeq);
					model.addAttribute("hasTupyo", "false");
					model.addAttribute("accountId",accountId);
					ChamyeoVo masterVo = service.getClassMaster(clasId);
					String masterId = masterVo.getClchAccountId();
					String isMaster ="false";
					if(masterId.equals(accountId)) {
						isMaster = "true";
					}
					model.addAttribute("clasId",clasId);
					model.addAttribute("isMaster", isMaster);
					
					return "tupyo";
				}
			}
			
			
		model.addAttribute("hasTupyo", "true");
		
		List<TupyoOptionVo> lists = service.getAllTupyoOption(vo.getTupySeq());
		model.addAttribute("vo",vo);
		model.addAttribute("lists",lists);
		
		List<String> instrNicknameList = new ArrayList<>();
		for(int i =0;i<lists.size();i++) {
			String instrId = lists.get(i).getTuopInstr();
			UserProfileVo instrVo = service.getMember(instrId);
			String instrNickname = instrVo.getUserNickname();
			instrNicknameList.add(instrNickname);
		}
		model.addAttribute("instrNicknameList", instrNicknameList);
		
		model.addAttribute("title","투표");
		String isMaster ="false";
		ChamyeoVo masterVo = service.getClassMaster(clasId);
		String masterId = masterVo.getClchAccountId();
		if(masterId.equals(accountId)) {
			isMaster = "true";
		}
		model.addAttribute("clasId",clasId);
		model.addAttribute("isMaster", isMaster);
		model.addAttribute("accountId",accountId);
		return "tupyo";
		}
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
	public String insertTupyo(@RequestBody TupyoUserVo vo) {
		int tuusOptionSeq = vo.getTuusOptionSeq();
		String tuusAccountId = vo.getTuusAccountId();
		TupyoOptionVo tuopVo = service.getTupyoOption(tuusOptionSeq);
		int tupySeq = tuopVo.getTuopTupySeq();
		TupyoVo tupyVo = service.getTupyoBySeq(tupySeq);
		String tupyStatus = tupyVo.getTupyStatus();
		if(tupyStatus.equals("F")) {
			return "finishedTupyo";
		}
		System.out.println("웨엥웨웨웨웽엥"+tuusOptionSeq);
		System.out.println(""+tuusAccountId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tuusOptionSeq", tuusOptionSeq);
		map.put("tuusAccountId", tuusAccountId);
		service.insertTupyoUser(map);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value = "/tupyoResult.do",method = RequestMethod.GET)
	public Map<String, Object> tupyoResult(@RequestParam int tupySeq,@RequestParam int tuopSeq){
		List<TupyoOptionVo> tupyoOptionList = service.getAllTupyoOption(tupySeq);
		
		List<TupyoUserVo> resultList = service.getTupyoResult(tuopSeq);
		System.out.println("리절트리스트"+resultList);
		Map<String, Object> optionMap = new HashMap<String, Object>();
		optionMap.put("tupyoOptionList",tupyoOptionList);
		optionMap.put("resultList",resultList);
		return optionMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/agreeTupyo.do",method = RequestMethod.POST)
	public void agreeTupyo( @RequestBody TupyoUserVo userVo) {
		int tuusOptionSeq =userVo.getTuusOptionSeq();
		String tuusAccountId = userVo.getTuusAccountId();
		String tuusAgree = userVo.getTuusAgree();
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
		List<String> instrNicknameList = new ArrayList<>();
		for(int i =0;i<lists.size();i++) {
			String instrId = lists.get(i).getTuopInstr();
			UserProfileVo instrVo = service.getMember(instrId);
			String instrNickname = instrVo.getUserNickname();
			instrNicknameList.add(instrNickname);
		}
		model.addAttribute("instrNicknameList", instrNicknameList);
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
	@GetMapping("/finishedTupyo.do")
	public void finishedTupyo(int tuopTupySeq) {

		List<TupyoUserVo> votedVo = service.getAllVotedStudents(tuopTupySeq);
		if(votedVo.isEmpty()) {
			service.delTupyo(tuopTupySeq);
		}
	}
	
	
	
	@ResponseBody
	@GetMapping("/finishTupyo.do")
	public String finishTupyo(int tupyClasId,int tupySeq,int tuopSeq) {
		TupyoVo vo = service.getTupyo(tupyClasId);
		List<TupyoUserVo> resultList = service.getTupyoResult(tuopSeq);
		System.out.println("finish의 리절트리스트"+resultList);
		if(resultList.size()==1) {//찬반 투표일 때
			
			List<TupyoUserVo> agreeList = service.getAgreeResult(tupySeq);
			System.out.println(agreeList);
			int agree = 0;
			int disagree = 0;
			for (int i = 0; i < agreeList.size(); i++) {
			    TupyoUserVo tupyoUserVo = agreeList.get(i);
			    String agreement = tupyoUserVo.getTuusAgree();
			    if (agreement.equals("A")) {
			        agree++;
			    } else {
			        disagree++;
			    }
			}

			if(agree > disagree) {
				System.out.println("강사 투표 결과 : 찬성");
				int optionSeq = agreeList.get(0).getTuusOptionSeq();
				TupyoOptionVo maxOptionVo = service.getTupyoOption(optionSeq);
				String clasAccountId = maxOptionVo.getTuopInstr();
				int clasId = vo.getTupyClasId();
				Map<String, Object> insertMap = new HashMap<String, Object>();
				insertMap.put("clasAccountId", clasAccountId);
				insertMap.put("clasId", clasId);
				service.updateClasAccountId(insertMap);
				service.endTupyo(tupySeq);
				
				Map<String, Object> classStatusMap = new HashMap<String, Object>();
				classStatusMap.put("clasId", clasId);
				classStatusMap.put("clasStatus", "모집완료");
				service.updateClassStatus(classStatusMap);
				
				LocalDate currentDate = LocalDate.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		        String formattedDate = currentDate.format(formatter);
				String alarId = "AT_C"+formattedDate;
				List<ChamyeoVo> classMemberAlarmVo = service.getAllClassMember(clasId);
				for(int i=0;i<classMemberAlarmVo.size();i++) {
					String aId = classMemberAlarmVo.get(i).getClchAccountId();
					Map<String, Object> alarmMap = new HashMap<String, Object>();
					alarmMap.put("alarId", alarId);
					alarmMap.put("alarContent", "강사가 확정되었습니다. 내 클래스에서 확인해보세요!");
					alarmMap.put("alarAccountId", aId);
					alarmMap.put("alarReplySeq", "justGoMyClass.do?clasId="+clasId);
					alarmService.insertAlarm(alarmMap);
				}
				
				return "confirm";
			}else {//반대가 더 많거나 같은 경우
				System.out.println("강사 신청이 거부되었습니다");
				service.endTupyo(tupySeq);
				service.delTupyo(tupySeq);
				return "disagree";
			}
		}else {//여러명의 강사 선택지 투표일 때 
			
			int maxCount = 0;
			System.out.println(resultList);
			
			System.out.println(resultList.get(0).getTuusOptionSeq());
			for (int i = 0; i < resultList.size(); i++) {
			    TupyoUserVo result = resultList.get(i);
			    int count = result.getCount();
			    if (count > maxCount) {
			        maxCount = count;
			    }
			}

			System.out.println(maxCount);
			List<Integer> maxOptionList = new ArrayList<>();
			for (int i = 0; i < resultList.size(); i++) {
			    TupyoUserVo result = resultList.get(i);
			    int tuusOptionSeq = result.getTuusOptionSeq();
			    int count = result.getCount();
			    if (count == maxCount) {
			        maxOptionList.add(tuusOptionSeq);
			    }
			}
			System.out.println(maxOptionList);
			if(maxOptionList.size() > 1) {
				System.out.println("동률 발생, 투표를 다시 생성해주세요");
				
				//투표를 다시 생성하게 만들
				service.endTupyo(tupySeq);
				//기존 투표 및 관련 데이터 삭제
				service.delTupyo(tupySeq);
				return "same";
			}
			//아래의 option seq로 선생 정보를 받아와서 accountId를 확정 강사에 넣어준다
			int optionSeq = maxOptionList.get(0);
			TupyoOptionVo maxOptionVo = service.getTupyoOption(optionSeq);
			String clasAccountId = maxOptionVo.getTuopInstr();
			int clasId = vo.getTupyClasId();
			Map<String, Object> insertMap = new HashMap<String, Object>();
			insertMap.put("clasAccountId", clasAccountId);
			insertMap.put("clasId", clasId);
			service.updateClasAccountId(insertMap);
			service.endTupyo(tupySeq);
			Map<String, Object> classStatusMap = new HashMap<String, Object>();
			classStatusMap.put("clasId", clasId);
			classStatusMap.put("clasStatus", "모집완료");
			service.updateClassStatus(classStatusMap);
			return "confirm";
		}
		
	}
	
	@ResponseBody
	@PostMapping("/makeTupyo.do")
	public String makeTupyo(int tupyClasId,String tupyEnddate) throws ParseException {
		
		//String 날짜를 date로 바꿔줘야함
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(tupyEnddate);
		
		
		Map<String, Object> map= new HashMap<String, Object>();
		int tupyTotalUser = service.countTotalClassMember(tupyClasId);
		map.put("tupyClasId", tupyClasId);
		map.put("tupyTotalUser", tupyTotalUser);
		map.put("tupyEnddate", date);
		service.insertTupyo(map);
		
		System.out.println(tupyClasId);
		TupyoVo vo = service.getTupyo(tupyClasId);
		System.out.println(vo);
		int tuopTupySeq = vo.getTupySeq();
		System.out.println(tuopTupySeq);
		
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
		return null;
	}
	


}
