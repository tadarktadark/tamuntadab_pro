package com.tdtd.tmtd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.IAlarmService;
import com.tdtd.tmtd.vo.AlarmVo;

/**
 * 알림 관련 컨트롤러
 * 
 * @author 김다현
 *
 */
@Controller
public class AlarmController {

	@Autowired
	private IAlarmService service;
	
	/**
	 * 알림 목록 조회 ajax
	 * 
	 * @param alarAccountId 사용자 아이디
	 * @return - list 알림 리스트
	 */
	@PostMapping("/notificationList.do")
	@ResponseBody
	public List<AlarmVo> notificationList(String alarAccountId) {
		List<AlarmVo> list = service.getAlarmList(alarAccountId);
		return list;
	}
	
	/**
	 * 알림 추가 ajax
	 * 
	 * @param gubun 알림 분류
	 * @param content 알림 내용
	 * @param accountId 알림 사용자 아이디
	 * @param url 알람 이동 경로
	 */
	@PostMapping("/insertAlarm.do")
	@ResponseBody
	public void insertAlarm(String gubun, String content, String accountId,String url) {
		
		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = currentDate.format(formatter);
		String alarId = gubun+formattedDate;
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("alarId", alarId);
		insertMap.put("alarContent", content);
		insertMap.put("alarAccountId", accountId);
		insertMap.put("alarReplySeq", url);
		service.insertAlarm(insertMap);
	}
	
	/**
	 * 알람 관련 페이지 이동
	 * 
	 * @param link 알람 이동 경로
	 * @param alarId 알람 아이디
	 * @return
	 */
	@GetMapping("/notificationLink.do")
	public String notificationLink(String link,String alarId) {
		service.updateAlarm(alarId);
		String result = "redirect:/"+link;
		return result;
	}
	
	/**
	 * 알람 삭제 ajax
	 * 
	 * @param checkedValues 선택한 알람 리스트
	 */
	@PostMapping("/delAlarm.do")
	@ResponseBody
	public void delAlarm(@RequestParam("checkedValues") List<String> checkedValues) {
		System.out.println(checkedValues);
		for(int i=0;i<checkedValues.size();i++) {
			service.delAlarm(checkedValues.get(i));
		}
		
	}
	
}
