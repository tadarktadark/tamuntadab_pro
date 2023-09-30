package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.AlarmVo;

public interface IAlarmService {
	
	public List<AlarmVo> getAlarmList(String alarAccountId);
	
	public int insertAlarm(Map<String, Object> map);
	
	public int delAlarm(String alarId);
	
	public int updateAlarm(String alarId);

}
