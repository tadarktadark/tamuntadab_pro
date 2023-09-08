package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommUserDaoImpl implements ICommUserDao {
	
	private final String NS = "com.tdtd.tmtd.model.mapper.CommUserDaoImpl.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Boolean emailCheck(Map<String,String> map) {
		return sqlSession.selectOne(NS+"searchEmail",map);
	}

	@Override
	public Boolean searchNickName(String userNickName) {
		return sqlSession.selectOne(NS+"searchNickName",userNickName);
	}
}
