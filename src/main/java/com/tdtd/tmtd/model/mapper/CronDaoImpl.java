package com.tdtd.tmtd.model.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CronDaoImpl implements ICronDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private String NS = "com.tdtd.tmtd.model.mapper.CronDaoImpl.";
	
	@Override
	public void registHuman() {
		session.update(NS+"registHuman");
	}

	@Override
	public void restoreJeongji() {
		session.update(NS+"restoreJeongji");
	}

}
