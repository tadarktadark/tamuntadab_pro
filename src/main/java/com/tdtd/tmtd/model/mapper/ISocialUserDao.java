package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import com.tdtd.tmtd.vo.UserProfileVo;

public interface ISocialUserDao {
	
	/**
	* WOON 소셜 회원에 대한 기능을 담은 인터페이스
	* @author : 임정운
	* @since : 2023.09.09
	* @version : 1.0
	*/
	
	/**
	* WOON 입력 값을 받아 해당 입력 값을 NAVER_USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<String,String>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registNaverUser(Map<String,String> userProfile);
	
	/**
	* WOON 입력 값을 받아 해당 입력 값을 KAKAO_USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<String,String>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registKakaoUser(Map<String,String> userProfile);
	
	/**
	* WOON 입력 값을 받아 해당 입력 값을 GOOGLE_USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<String,String>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registGoogleUser(Map<String,String> userProfile);
	
	
	/**
	* WOON 사용자의 RefreshToken을 갱신해주는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<String,String>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.13
	*/
	public int updateRefToken(Map<String,String> userProfile);
	

	/**
	 *  WOON 사용자의 RefreshToken을 갱신해주는 
	 * @param userProfile
	 * @return
	 */
	public UserProfileVo SocialLogin(Map<String,String> userProfile);
}
