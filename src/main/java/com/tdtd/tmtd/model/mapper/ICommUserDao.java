package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import com.tdtd.tmtd.vo.UserProfileVo;

/**
* WOON 일반 회원에 대한 기능을 담은 인터페이스
* @author : 임정운
* @since : 2023.09.08
* @version : 1.0
*/

public interface ICommUserDao {

	/**
	* WOON 입력된 이메일 값의 가입경로에 대한 테이블에서 중복을 확인하는 기능을 가진 메소드
	* @param : {userEmail(사용자 입력 이메일) : String , site(가입 사이트) : String} 
	* @return : Boolean 등록 된 이메일이 있을 경우 TRUE / 없을 경우 FALSE
	* @author : 임정운
	* @since : 2023.09.08
	*/
	public Boolean emailCheck(Map<String,String> map);
	/**
	* WOON 입력된 이메일 값을 모든 유저 정보 테이블에서 중복을 확인하는 기능을 가진  메소드
	* @param : {userNickName(사용자 입력 닉네임) : String} 
	* @return : Boolean 등록 된 닉네임 있을 경우 TRUE / 없을 경우 FALSE
	* @author : 임정운
	* @since : 2023.09.08
	*/
	public boolean searchNickName(Map<String,Object> map);
	
	/**
	* WOON 입력 값을 받아 해당 입력 값을 USER_PROFILE DB에 저장시키는 기능을 가진 메소드
	* @param : 사용자가 입력한 값 / 타입 : Map<STring,Object>
	* @return : 1 == 입력 성공 0 == 입력 실패 / 타입 : int
	* @author : 임정운
	* @since : 2023.09.09
	*/
	public int registCommUser(Map<String,Object> userprofile);
	
	/**
	 * WOON 사용자의 정보를 바탕으로 해당 유저의 정지 여부를 가져오는 기능을 가진 메소드
	 * @param userInfo 사용자 정보가 담긴 vo
	 * @return 정지 갯수
	 */
	public int searchJeongJi(UserProfileVo userInfo);
	
	/**
	 * WOON 사용자의 정지된 상태일 때 날짜를 가져오는 기능을 가진 메소드
	 * @param userInfo 사용자의 정보
	 * @return 정지 해제일
	 */
	public String jeongjidate(UserProfileVo userInfo);

	/**
	 * WOON 사용자가 입력한 ID와 비밀번호를 사용자의 정보를 가져오는 기능을 가진 메소드
	 * @param userInput 사용자가 입력한 ID 와 비밀번호
	 * @return 사용자의 정보
	 */
	public UserProfileVo commLogin(Map<String,String> userInput);
	
	/**
	 * WOON 사용자의 ID는 맞았으나 비밀번호가 다를경우 차단 횟수를 올려주는 기능을 가진  메소드
	 * @param userEmail 사용자가 입력한 이메일
	 * @return 업데이트 성공시 1 실패시 0
	 */
	public int updateChadanCnt(String userEmail);
	
	/**
	 * WOON 사용자의 차단횟수를 확인하는 기능을 가진 메소드
	 * @param userEmail 사용자가 입력한 이메일
	 * @return 사용자의 차단 횟수
	 */
	public int checkUserChadanCount(String userEmail);
	
	/**
	 * WOON  사용자가 비밀번호를 5번이상 틀렸을 경우 해당 계정의 로그인을 특정 시간동안 제한하는 기능을 가진 메소드
	 * @param userEmail 사용자가 입력한 이메일
	 * @return update 성공시 1
	 */
	public int updateUserChadanDate(String userEmail);
	
	/**
	 * WOON 사용자가 로그인 시 차단됐을 경우 차단 해제일을 확인하는 기능을 가진 메소드
	 * @param userEmail 사용자가 입력한 이메일
	 * @return 차단 날짜
	 */
	public String checkUserChadanDate(String userEmail);
	
	/**
	 * WOON 사용자가 차단된 상태이지만 그 시간이 10분을 넘어간 후에 로그인 성공시 시간을 초기화 시키는 기능을 가진메소드 
	 * @param userEmail 사용자가 입력한 이메일
	 * @return 초기화 성공시 1 실패시 0
	 */
	public int restoreUserChadanDate(String userEmail);

	/**
	 * WOON 로그인 성공시 사용자의 차단 횟수를 초기화 시키는 기능을 가진 메소드
	 * @param userInfo 사용자의 정보
	 * @return 성공 시 1 실패시 0
	 */
	public int restoreUserChadanCount(UserProfileVo userInfo);
	
	/**
	 * WOON 사용자가 로그인 성공 시 최근 접속시간을 갱신하는 기능을 가진 메소드
	 * @param userInfo 사용자의 정보
	 * @return 업데이트 성공시 1 실패시 0
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
	 * WOON 사용자가 갱신 성공시 해당 사용자의 토큰값을 삭제 시키는 메소드
	 * @param resetPassword
	 * key : "tokenValue" => 비밀번호 초기화 시 필요한 토큰
	 * @return 갱신 성공시 1 실패시 0
	 */
	public int deleteResetPwToken(Map<String,Object> resetPassword);
	
	/**
	 * WOON 사용자의 비밀번호를 변경하는 기능을 가진 메소드
	 * @param resetPassword 사용자의 정보를 가지고 있는 객체
	 * key : "tokenValue" value ="토큰값 or mypage(마이페이지에서 변경시 )" => 비밀번호 초기화 시 필요한 토큰
	 * key : "userAccountId" => 사용자 ID
	 * key : "userPassword" => 사용자가 변경 할 비밀번호
	 * @return 갱신 성공시 1 실패시 0
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
	 * WOON 사용자의 계정을 휴면 처리를 해제 하는 기능을 가진 메소드
	 * @param userToken 사용자 메일에 전송된 Token값
	 * @return 갱신 성공시 1 실패시 0
	 */
	public int updatedelflag(Map<String,Object> userToken);
	
	/**
	 * WOON 사용자의 게정 정보를 삭제된 테이블에 저장하는 기능을 가진 메소드
	 * @param vo 해당 사용자의 정보
	 * @return 삽입 성공시 1 실패시 0
	 */
	public int insertUserDelTable(UserProfileVo vo);
	
	/**
	 * WOON 사용자를 삭제처리 하는 기능을 가진 메소드
	 * @param vo 사용자의 정보
	 * @return 갱신 성공시 1 실패시 0
	 */
	public int updateUserDelflagToY(UserProfileVo vo);
	
	/**
	 * WOON 학생 사용자의 잔여 결제 내역을 확인하는 기능을 가진 메소드
	 * @param vo 사용자의 정보
	 * @return 미완료된 결제의 수
	 */
	public int searchUserGyeolje(UserProfileVo vo);
	
	/**
	 * WOON 강사 사용자의 잔여 정산내역을 확인하는 기능을 가진 메소드
	 * @param vo 사용자의 정보
	 * @return 미완료된 정산 갯수
	 */
	public int searchUserJeongSan(UserProfileVo vo);
	
	/**
	 * 
	 * WOON 사용자 삭제시 삭제 테이블 인원을 가져오는 메소드
	 *
	 * @return 테이블 row의 갯수
	 * @author : Administrator
	 * @since : 2023.09.
	 */
	public int countDeluser();
	
}
