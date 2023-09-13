package com.tdtd.tmtd.model.service;

import java.util.Map;

import com.tdtd.tmtd.vo.UserProfileVo;

public interface ISocialUserService {
	
	public int naverRegist(Map<String, String> userProfile);
	
	public int kakaoRegist(Map<String,String> userProfile);
	
	public int googleRegist(Map<String,String> userProfile);
	
	public int updateRefToken(Map<String,String> userProfile);
	
	public UserProfileVo socialLogin(Map<String,String> userProfile);
		
}
