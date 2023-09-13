package com.tdtd.tmtd.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.ISocialUserDao;
import com.tdtd.tmtd.vo.UserProfileVo;

@Service
public class SocialUserServiceImpl implements ISocialUserService {
	
	@Autowired
	private ISocialUserDao sdao;
	
	public int naverRegist(Map<String, String> map) {
		return sdao.registNaverUser(map);
	}

	@Override
	public int kakaoRegist(Map<String, String> userProfile) {
		return sdao.registKakaoUser(userProfile);
	}

	@Override
	public int googleRegist(Map<String, String> userProfile) {
		return 0;
	}

	@Override
	public int updateRefToken(Map<String, String> userProfile) {
		return sdao.updateRefToken(userProfile);
	}

	@Override
	public UserProfileVo socialLogin(Map<String, String> userProfile) {
		return sdao.SocialLogin(userProfile);
	}
}
