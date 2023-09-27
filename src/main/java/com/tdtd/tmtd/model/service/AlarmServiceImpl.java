package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IAlarmDao;
import com.tdtd.tmtd.vo.AlarmVo;

@Service
public class AlarmServiceImpl implements IAlarmService {
	
	@Autowired
	private IAlarmDao dao;

	@Override
	public List<AlarmVo> getAlarmList(String alarAccountId) {
		return dao.getAlarmList(alarAccountId);
	}

	@Override
	public int insertAlarm(Map<String, Object> map) {
		return dao.insertAlarm(map);
	}

	@Override
	public int delAlarm(String alarId) {
		return dao.delAlarm(alarId);
	}

}
