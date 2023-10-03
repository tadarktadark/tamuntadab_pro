package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.AdminVo;
import com.tdtd.tmtd.vo.UserProfileVo;

public interface IAdminService {
	
	/**
	 * 
	 * WOON 관리자 페이지 접속시 IP를 확인하는 기능을 가진 메소드
	 *
	 * @param accessIP 사용자의 IP
	 * @return 1 등록 IP 0 미등록 IP
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int checkIP(String accessIP);
	
	/**
	 * 
	 * WOON 관리자가 입력한 정보에 대한 정보를 가져오는 기능을 가진 메소드
	 *
	 * @param adminInput 사용자의 입력 값
	 * @return 사용자의 정보
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public AdminVo adminLogin(Map<String,Object> adminInput);
	
	/**
	 * 
	 * WOON 사용자의 정보 조회 성공시 사용자의 최근 접속시간을 갱신하는 기능을 가진 메소드
	 *
	 * @param adminInfo 사용자의 정보
	 * @return 갱신 성공 1 갱신 실패 0 
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int updateAdminAccTime(AdminVo adminInfo);
	
	/**
	 * 
	 * WOON 사용자의 비밀번호를 갱신하고 접속시간을 설정하는 메소드
	 *
	 * @param adminInfo 사용자의 정보 및 사용자가 입력한 값
	 * @return 갱신 성공시 1 실패시 0 
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int updateAdminPw(AdminVo adminInfo, Map<String,Object> adminInput);
	
	/**
	 * 
	 * WOON 관리자 리스트를 가져요는 매소드
	 * 정렬
	 * key = "column" =>  정렬을 위한 컬럼명
	 * key = "value" =>  정렬 방식
	 *
	 * 필터
	 * key = "userAuth" =>  사용자 권한
	 * key = "userDelflag" =>  삭제 여부
	 * 
	 * 검색
	 * key = "tag" =>  검색 컬럼
	 * key = "searchValue" =>  검색 값
	 * 
	 * 페이징
	 * key = "start" =>  시작 번호
	 * key = "end" =>  종료 번호
	 * @param map 사용자의 필터, 검색, 페이징 정보
	 * @return 필터, 검색, 페이징 정보에 대한 조회 관리자 리스트
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public List<AdminVo> getAdminList(Map<String,Object> map);
	
	/**
	 * 
	 * WOON 페이징 처리를 위한 필터, 검색에 대한 조회 숫자
	 *
	 * @param map
	 * 정렬
	 * key = "column" =>  정렬을 위한 컬럼명
	 * key = "value" =>  정렬 방식
	 *
	 * 필터
	 * key = "userAuth" =>  사용자 권한
	 * key = "userDelflag" =>  삭제 여부
	 * 
	 * 검색
	 * key = "tag" =>  검색 컬럼
	 * key = "searchValue" =>  검색 값
	 * 
	 * @return 조회된 관리자의 수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int countAdmin(Map<String,Object>map);
	
	/**
	 * 
	 * WOON 사용자 리스트를 가져오는 메소드
	 *
	 * @param map
	 * 정렬
	 * key = "column" =>  정렬을 위한 컬럼명
	 * key = "value" =>  정렬 방식
	 *
	 * 필터
	 * key = "userAuth" =>  사용자 권한
	 * key = "userSite" =>  사용자 가입 경로
	 * key = "userDelflag" =>  삭제 여부
	 * key = "userGender" =>  사용자 성별
	 * key = "userJeongJiSangTae" =>  사용자의 정지 상태
	 * key = "siusState" =>  사용자 신고 처리로 인해 정지가 필요한 상태
	 * 
	 * 검색
	 * key = "tag" =>  검색 컬럼
	 * key = "searchValue" =>  검색 값
	 * 
	 * 페이징
	 * key = "start" =>  시작 번호
	 * key = "end" =>  종료 번호
	 * @return 조회된 사용자 리스트
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public List<UserProfileVo> getUserList(Map<String,Object> map);

	/**
	 * 
	 * WOON 페이징 처리를 위한 사용자의 총 숫자
	 *
	 * @param map
 	 * 정렬
	 * key = "column" =>  정렬을 위한 컬럼명
	 * key = "value" =>  정렬 방식
	 *
	 * 필터
	 * key = "userAuth" =>  사용자 권한
	 * key = "userSite" =>  사용자 가입 경로
	 * key = "userDelflag" =>  삭제 여부
	 * key = "userGender" =>  사용자 성별
	 * key = "userJeongJiSangTae" =>  사용자의 정지 상태
	 * key = "siusState" =>  사용자 신고 처리로 인해 정지가 필요한 상태
	 * 
	 * 검색
	 * key = "tag" =>  검색 컬럼
	 * key = "searchValue" =>  검색 값
	 * 
	 * @return 조회된 사용자의 수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int countUser(Map<String, Object> map);
	
	/**
	 * 
	 * WOON 사용자의 정보를 가져오는 메소드
	 *
	 * @param userId 조회될 사용자의 ID
	 * @return 사용자의 정보를 담은 vo
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public UserProfileVo getuserDetail(String userId);
	
	/**
	 * 
	 * WOON 사용자를 정지 및 신고 내역을 갱신하는 기능
	 *
	 * @param map 사용자의 정보를 담은 map 객체
	 * @return 정지 성공 1  실패 0
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int setuserJeongji(Map<String,Object> map);
	
	/**
	 * 
	 * WOON 관리자를 추가하는 기능
	 *
	 * @param map 추가될 관리자의 정보
	 * @return 성공한 삽입의 갯수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int addAdmin(Map<String,Object> map);

	/**
	 * 
	 * WOON 관리자에게 IP를 부여하는 기능을 가진 메소드
	 *
	 * @param map 부여할 IP 및 부여할 관리자 및 부여한 관리자
	 * @return 성공한 삽입의 갯수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int addIp(Map<String,Object> map);
	/**
	 * 
	 * WOON 관리자를 삭제시키는 기능
	 *
	 * @param adminId 삭제 될 관리자의 ID
	 * @return 성공한 갱신 갯수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int adminIdCheck(Map<String,Object>map);
	
	/**
	 * 
	 * WOON 등록된 관리자의 정보를 와 IP리스트를 가져오는 메소드
	 *
	 * @param userId 조회할 관리자 ID
	 * @return 관리자 정보 및 해당 관리자에게 등록된 IP
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public Map<String,Object> adminDetail(String userId);
	
	/**
	 * 
	 * WOON 관리자를 삭제 시키고 해당 관리자에게 연결된 IP를 모두 삭제시키는 기능
	 *
	 * @param adminId 삭제 시킬 관리자 ID
	 * @return 삭제 성공 1 실패 0
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int delAdmin(String adminId);
	
	/**
	 * 
	 * WOON 관리자에게 할당된 특정 IP를 삭제하는 기능
	 *
	 * @param map 해당 IP의 정보
	 * @return 성공한 삭제 갯수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int delIP(Map<String,Object> map);

	/**
	 * 
	 * WOON 삭제된 관리자를 복구하는 기능
	 *
	 * @param adminId 복구할 관리자 ID
	 * @return 복구 성공한 갯수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int restoreAdmin(String adminId);

	/**
	 * 
	 * WOON 관리자 비밀번호 재설정을 위해 최근 접속시간을 초기화 시키는 기능
	 *
	 * @param adminId 초기화 시킬 관리자 ID
	 * @return 성공한 초기화 갯수
	 * @author : Administrator
	 * @since : 2023.09.08
	 */
	public int resetAdminPw(String adminId);
	
}
