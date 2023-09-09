package com.tdtd.tmtd.model.mapper;

import java.util.Map;

public interface ISocialUserDao {
	
	/**
	* NOTE 소셜 회원에 대한 기능을 담은 인터페이스
	* @author : 임정운
	* @since : 2023.09.09
	* @version : 1.0
	*/
	
	/**
	* NOTE 입력 값을 받아 해당 입력 값을 NAVER_USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<String,Object>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registNaverUser(Map<String,Object> userProfile);
	
	/**
	* NOTE 입력 값을 받아 해당 입력 값을 KAKAO_USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<String,Object>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registKakaoUser(Map<String,Object> userProfile);
	
	/**
	* NOTE 입력 값을 받아 해당 입력 값을 GOOGLE_USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<String,Object>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registGoogleUser(Map<String,Object> userProfile);
}
