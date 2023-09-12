package com.tdtd.tmtd.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ISocialUserDao;

@Service
public class SocialUserServiceImpl implements ISocialUserService {
	
	@Autowired
	private ISocialUserDao sdao;
	
	public int naverRegist(Map<String, String> map) {
		return sdao.registNaverUser(map);
	}
}
