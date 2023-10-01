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

@Controller
public class AlarmController {

	@Autowired
	private IAlarmService service;
	
	
	@PostMapping("/notificationList.do")
	@ResponseBody
	public List<AlarmVo> notificationList(String alarAccountId) {
		List<AlarmVo> list = service.getAlarmList(alarAccountId);
		return list;
	}
	
	
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
	
	@GetMapping("/notificationLink.do")
	public String notificationLink(String link,String alarId) {
		service.updateAlarm(alarId);
		String result = "redirect:/"+link;
		return result;
	}
	
	@PostMapping("/delAlarm.do")
	@ResponseBody
	public void delAlarm(@RequestParam("checkedValues") List<String> checkedValues) {
		System.out.println(checkedValues);
		for(int i=0;i<checkedValues.size();i++) {
			service.delAlarm(checkedValues.get(i));
		}
		
	}
	
}
