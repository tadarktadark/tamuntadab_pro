package com.tdtd.tmtd.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ICronDao;

@Service
public class CronServiceImpl implements ICronService {
	
	@Autowired
	private ICronDao cronDao;
	
	
	@Override
	public void registHuman() {
		cronDao.registHuman();
	}

	@Override
	public void restoreJeongji() {
		cronDao.restoreJeongji();
	}

}
