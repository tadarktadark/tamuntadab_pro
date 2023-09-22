package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import com.tdtd.tmtd.vo.UserProfileVo;

/**
* NOTE 일반 회원에 대한 기능을 담은 인터페이스
* @author : 임정운
* @since : 2023.09.08
* @version : 1.0
*/

public interface ICommUserDao {

	/**
	* NOTE 입력된 이메일 값의 가입경로에 대한 테이블에서 중복을 확인하는 메소드
	* @param : {userEmail(사용자 입력 이메일) : String , site(가입 사이트) : String} 
	* @return : Boolean 등록 된 이메일이 있을 경우 TRUE / 없을 경우 FALSE
	* @author : 임정운
	* @since : 2023.09.08
	*/
	public Boolean emailCheck(Map<String,String> map);
	/**
	* NOTE 입력된 이메일 값을 모든 유저 정보 테이블에서 중복을 확인하는 메소드
	* @param : {userNickName(사용자 입력 닉네임) : String} 
	* @return : Boolean 등록 된 닉네임 있을 경우 TRUE / 없을 경우 FALSE
	* @author : 임정운
	* @since : 2023.09.08
	*/
	public boolean searchNickName(Map<String,Object> map);
	
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
	
	public String jeongjidate(UserProfileVo userInfo);

	public UserProfileVo commLogin(Map<String,String> userInput);
	
	public int updateChadanCnt(String userEmail);
	
	public int checkUserChadanCount(String userEmail);
	
	public int updateUserChadanDate(String userEmail);
	
	public String checkUserChadanDate(String userEmail);
	
	public int restoreUserChadanDate(String userEmail);

	public int restoreUserChadanCount(UserProfileVo userInfo);
	
	public int updateTime(UserProfileVo userInfo);
	
	public UserProfileVo autoLogin(String userAutoLoginToken);
	
	public int updateUserInfo(Map<String,Object> updateInfo);
	
	public int updateResetPwToken(Map<String,Object> resetPassword);
	
	public int deleteResetPwToken(Map<String,Object> resetPassword);
	
	public int updateUserPassword(Map<String,Object> resetPassword);

	public int checkPassword(Map<String,Object> resetPassword);
	
	public int updatedelflag(Map<String,Object> userToken);
	
	public int insertUserDelTable(UserProfileVo vo);
	
	public int updateUserDelflagToY(UserProfileVo vo);
	
	public int searchUserGyeolje(UserProfileVo vo);
	
	public int searchUserJeongSan(UserProfileVo vo);
	
}
