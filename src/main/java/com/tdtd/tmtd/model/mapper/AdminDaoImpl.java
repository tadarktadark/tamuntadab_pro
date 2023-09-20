package com.tdtd.tmtd.model.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class AdminDaoImpl implements IAdminDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String NS = "com.tdtd.tmtd.model.mapper.AdminDaoImpl.";
	
	@Override
	public int checkIP(String accessIP) {
		return sqlSession.selectOne(NS+"searchIP",accessIP);
	}
	
}
