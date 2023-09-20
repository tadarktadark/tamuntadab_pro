package com.tdtd.tmtd.model.service;

import java.util.Map;

import com.tdtd.tmtd.vo.UserProfileVo;

public interface ICommUserService {
	
	/**
	* NOTE 입력된 이메일 값의 가입경로에 대한 테이블에서 중복을 확인하는 메소드
	* @param : {userEmail(사용자 입력 이메일) : String , site(가입 사이트) : String} 
	* @return : Boolean 등록 된 이메일이 있을 경우 TRUE / 없을 경우 FALSE
	* @author : 임정운
	* @since : 2023.09.08
	*/
	public Boolean searchEmailService(Map<String, String> map);
	
	/**
	* NOTE 입력 값을 받아 해당 입력 값을 USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<STring,Object>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registCommUser(Map<String,Object> userprofile);
	
	/**
	 * NOTE 사용자의 정보를 바탕으로 해당 유저의 정지 여부를 가져오는 메소드
	 * @param userInfo 사용자 정보가 담긴 vo
	 * @return 정지 갯수
	 */
	public int searchJeongJi(UserProfileVo userInfo);
	
	/**
	 * 로그인 시 기능이 합쳐진 메소드
	 */
	public Map<String,Object>commLogin(Map<String,String> userInput);
	
	public int updateTime(UserProfileVo userInfo);
	
	public UserProfileVo autoLogin(String userAutoLoginToken);
	
	public int updateUserInfo(Map<String,Object> updateInfo);
	
	public int updateResetPwToken(Map<String,Object> resetPassword);

	public int updateUserPassword(Map<String,Object> resetPassword);
	
	public String searchNickName(Map<String,Object> map);
	
	public int updatedelflag(Map<String,Object> userToken);
	
	public int updateUserDelflagToY(UserProfileVo vo);
	
	public String checkPayment (UserProfileVo vo);
}
