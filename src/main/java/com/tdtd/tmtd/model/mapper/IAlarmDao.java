package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.AlarmVo;

public interface IAlarmDao {

	// 알림 목록 조회
	public List<AlarmVo> getAlarmList(String alarAccountId);

	// 알림 입력
	public int insertAlarm(Map<String, Object> map);

	// 알림 삭제
	public int delAlarm(String alarId);

	// 알림 읽음으로 업데이트
	public int updateAlarm(String alarId);

}
