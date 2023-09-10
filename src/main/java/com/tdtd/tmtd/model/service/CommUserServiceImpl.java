package com.tdtd.tmtd.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ICommUserDao;

@Service
public class CommUserServiceImpl implements ICommUserService {
	
	@Autowired
	private ICommUserDao cdao;
	
	public boolean searchEmailService(Map<String, String> map) {
		return cdao.emailCheck(map);
	}

	@Override
	public int registCommUser(Map<String, Object> userprofile) {
		return cdao.registCommUser(userprofile);
	}
	
}
