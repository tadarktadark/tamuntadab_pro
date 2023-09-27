package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.AlarmVo;

@Repository
public class AlarmDaoImpl implements IAlarmDao {
	
	private final String NS = "com.tdtd.tmtd.model.mapper.AlarmDaoImpl.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<AlarmVo> getAlarmList(String alarAccountId) {
		return sqlSession.selectList(NS+"getAlarmList",alarAccountId);
	}

	@Override
	public int insertAlarm(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertAlarm",map);
	}

	@Override
	public int delAlarm(String alarId) {
		return sqlSession.delete(NS+"delAlarm",alarId);
	}

}
