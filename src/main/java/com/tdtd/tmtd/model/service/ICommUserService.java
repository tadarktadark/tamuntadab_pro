package com.tdtd.tmtd.model.service;

import java.util.Map;

import com.tdtd.tmtd.vo.UserProfileVo;

public interface ICommUserService {
	
	/**
	* WOON 입력된 이메일 값의 가입경로에 대한 테이블에서 중복을 확인하는 메소드
	* @param : {userEmail(사용자 입력 이메일) : String , site(가입 사이트) : String} 
	* @return : Boolean 등록 된 이메일이 있을 경우 TRUE / 없을 경우 FALSE
	* @author : 임정운
	* @since : 2023.09.08
	*/
	public Boolean searchEmailService(Map<String, String> map);
	
	/**
	* WOON 입력 값을 받아 해당 입력 값을 USER_PROFILE DB에 저장시키는 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<STring,Object>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registCommUser(Map<String,Object> userprofile);
	
	/**
	 * WOON 사용자의 정보를 바탕으로 해당 유저의 정지 여부를 가져오는 메소드
	 * @param userInfo 사용자 정보가 담긴 vo
	 * @return 정지 갯수
	 */
	public int searchJeongJi(UserProfileVo userInfo);
	
	public String jeongjidate(UserProfileVo userInfo);
	
	/**
	 * WOON 사용자가 입력한 정보를 바탕으로 정보를 조회하고 및 판단하는 기능들을 합친 서비스
	 * 설명 : 사용자 로그인 시 해당 사용자의 차단된 상태인지를이 있는 지 확인하고
	 * 			비밀번호가 틀렸을 경우 차단 횟수를 증가 시키며 그 횟수가 5회 이상일 경우 
	 * 			차단 시간을 갱신하며 해당 계정의 정보가 휴면 계정인지를 판단하고
	 * 			정상적인 정보 조회가 됐을 경우 해당 차단 일수 및 차단 횟수를 초기화하는 기능들이 합쳐진 메소드
	 * @param userInput 사용자 입력 값
	 * @return Map형식으로 리턴함
	 * key = "status" 로그인 상태를 return
	 * key = "userInfo" 로그인 성공시 사용자의 정보를 return
	 * key = "time" 차단된 시간을 return
	 */
	public Map<String,Object>commLogin(Map<String,String> userInput);
	
	/**
	 * WOON 사용자의 접속 시간 갱신 및 차단 시간 초기화 시키는 기능들이 합쳐진 메소드   
	 * @param userInfo 사용자의 정보
	 * @return 1 갱신 성공 0 갱신 실패
	 */
	public int updateTime(UserProfileVo userInfo);
	
	/**
	 * WOON 사용자가 자동로그인을 체크했을 때 해당 토큰을 기반으로 사용자의 정보를 가져오는 기능을 가진 메소드 
	 * @param userAutoLoginToken 사용자의 자동 로그인 토큰
	 * @return 사용자의 정보
	 */
	public UserProfileVo autoLogin(String userAutoLoginToken);
	
	/**
	 * WOON 사용자의 정보를 업데이트 하는 기능을 가진 메소드
	 * @param updateInfo 사용자의 정보를 가진 메소드
	 * key : "changeNickName" => 변경 할 닉네임
	 * key : "changeProfile" => 프로필 사진
	 * key : "userSite" => 사용자의 가입 경로
	 * key : "userAccountId" => 사용자를 구분하기위한 ID
	 * 
	 * @return 갱신 성공시 1 실패시 0
	 */
	public int updateUserInfo(Map<String,Object> updateInfo);
	
	/**
	 * WOON 사용자가 비밀번호 초기화를 원할 경우 사용자의 토큰을 갱신하는 기능을 가진 메소드
	 * @param resetPassword 사용자의 정보를 가진 객체
	 * key : "tokenValue" => 비밀번호 초기화 시 필요한 토큰
	 * key : "userEmail" => 사용자가 입력한 이메일
	 * @return 갱신 성공시 1 실패시 0
	 */
	public int updateResetPwToken(Map<String,Object> resetPassword);

	/**
	 * WOON 사용자의 비밀번호를 갱신 및 사용자의 resetToken을 삭제하는 기능을 가진 메소드
	 * @param resetPassword 사용자의 정보를 가진 객체
	 * @return  갱신 성공시 1 실패시 0
	 */
	public int updateUserPassword(Map<String,Object> resetPassword);
	
	/**
	 * WOON 사용자의 비밀번호가 현재 비밀번호와 같은지 비교하는 메소드
	 * @param resetPassword 사용자의 정보를 가지고 있는 메소드
	 * key : "tokenValue" value ="토큰값 or mypage(마이페이지에서 변경시 )" => 비밀번호 초기화 시 필요한 토큰
	 * key : "userAccountId" => 사용자 ID
	 * key : "userPassword" => 사용자가 변경 할 비밀번호
	 * @return 같을 경우 1 다를 경우 0
	 */
	public int checkPassword(Map<String,Object> resetPassword);
	
	/**
	 * WOON 사용자의 닉네임 중복을 확인 후 닉네임을 갱신하는 메소드
	 * @param map 사용자의 입력 값
	 * @return 갱신 성공 실패 여부 true 성공 false 실패
	 */
	public String searchNickName(Map<String,Object> map);
	
	/**
	 * WOON 사용자의 휴면 상태를 해제하고 토큰을 삭제하는 메소드
	 * @param userToken 사용자의 토큰
	 * @return 성공시 1 실패시 0
	 */
	public int updatedelflag(Map<String,Object> userToken);
	/**
	 * WOON 사용자의 정보를 따로 저장 시킨 후  삭제 처리하는 기능을 가진 메소드
	 * @param 사용자의 정보
	 * @return 성공시 1 실패시 0
	 */
	public int updateUserDelflagToY(UserProfileVo vo);
	
	/**
	 * WOON 사용자의 권한에 따라 정산 상태 확인 혹은 결제 상태 확인하는 기능을 가진 메소드
	 * @param vo 사용자의 정보
	 * @return 있을경우 false 없을경우 true
	 */
	public String checkPayment (UserProfileVo vo);
	
	
}
