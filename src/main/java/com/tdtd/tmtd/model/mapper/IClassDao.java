package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.SugangryoVo;

public interface IClassDao {

	/**
	 * 모든 클래스 조회 (관리자를 위함)
	 * @param 출력할 클래스목록의 시작번호와 끝번호가 담긴 Map
	 * @return 페이징 처리된 클래스 목록
	 * @author 김기훈
	 * @since 2023-09-19
	 */
	public List<ClassVo> getAllClassListForA(Map<String, Object> map);
	
	/**
	 * 모든 클래스 수 조회 (관리자를 위함)
	 * @return 페이징 처리될 전체 클래스 갯수
	 * @author 김기훈
	 * @since 2023-09-19
	 */
	public int getAllClassListForACount();
	
	/**
	 * 페이징 처리 후 출력할 클래스 목록 조회
	 * @param 출력할 클래스목록의 시작번호와 끝번호가 담긴 Map
	 * @return 페이징 처리된 클래스 목록
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public List<ClassVo> getAllClassListForS(Map<String, Object> map);
	
	/**
	 * 페이징 처리될 전체 클래스 수 조회
	 * @return 페이징 처리될 전체 클래스 갯수
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int getAllClassListForSCount();
	
	/**
	 * 카테고리별 모집 중인 클래스 조회 (학생)
	 * @param map
	 * @return
	 */
	public List<ClassVo> getCategoryClassListForS(Map<String, Object> map);
	
	/**
	 *카테고리별 모집 중인 클래스 갯수 조회 (학생)
	 * @return
	 */
	public int getCategoryClassListForSCount(String category);
	
	/**
	 * 참여 가능한 모든 클래스 조회 (강사)
	 * @param map
	 * @return
	 */
	public List<ClassVo> getAllClassListForI(Map<String, Object> map);
	
	/**
	 * 참여 가능한 모든 클래스 갯수 조회 (강사)
	 * @return
	 */
	public int getAllClassListForICount();
	
	/**
	 * 참여 가능한 클래스 카테고리별 조회 (강사)
	 * @param map
	 * @return
	 */
	public List<ClassVo> getCategoryClassListForI(Map<String, Object> map);
	
	/**
	 * 참여 가능한 클래스 카테고리별 조회 총 갯수 (강사)
	 * @return
	 */
	public int getCategoryClassListForICount(String category);
	
	/**
	 * 세션에 있는 회원의 모든 참여중 클래스 조회
	 * @param getChamyeoClass
	 * @return
	 */
	public List<ClassVo> getChamyeoClass(String clchAccountId);
	
	/**
	 * 클래스 개설
	 * @param vo 개설될 클래스의 정보가 담긴 vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addClass(ClassVo vo);
	
	/**
	 * 참여할 세션의 정보 확인
	 * @param clchAccountId
	 * @return
	 */
	public ChamyeoVo getChamyeojaInfo(String clchAccountId);
	
	/**
	 * 해당 클래스의 모든 참여자 정보 조회
	 * @param clasId
	 * @return
	 */
	public List<ChamyeoVo> getChamyeojas(String clasId);
	
	/**
	 * 클래스 현재인원 조절
	 * @param map(nums:변경할 값의 크기, clasId:클래스ID)
	 * @return
	 */
	public int updateClassPeople(Map<String, Object> map);
	
	/**
	 * 클래스 상태 변경
	 * @param map(clasStatus:변경할 상태, clasId:클래스ID)
	 * @return
	 */
	public int updateClassStatus (Map<String, Object> map);
	
	/**
	 * 참여테이블 수정 (위임)
	 * @param vo 필요값(clchYeokal, clchClasId, clchAccountId)
	 * @return
	 */
	public int updateChamyeoYeokal(ChamyeoVo vo);
	
	/**
	 * 참여자 삭제
	 * @param map(clchClasId,clchAccountId)
	 * @return
	 */
	public int delChamyeoja(Map<String, Object> map);
	
	/**
	 * 수강료 확정 요청 생성
	 * @param vo
	 * @return
	 */
	public int dealSugangryo(SugangryoVo vo);
	
	/**
	 * 클래스의 수강료 요청 상태 조회
	 * @param sugaClasId
	 * @return
	 */
	public SugangryoVo getSugangryo(String sugaClasId);
	
	/**
	 * 과목 추가
	 * @param vo 추가할 과목의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addSubject(Map<String, Object> map);
	
	/**
	 * 과목 추가
	 * @param vo 추가할 과목 태그의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addSubjectTag(Map<String, Object> map);
	
	/**
	 * 참여자 추가 (클래스 생성)
	 * @param vo 추가할 클래스 참여자의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public int addChamyeoja(ChamyeoVo vo);
	
	/**
	 * 참여자 추가 (일반)
	 * @param vo 추가할 클래스 참여자의 정보가 담긴 Vo
	 * @return 생성 성공 갯수 (1)
	 * @author 김기훈
	 * @since 2023-09-21
	 */
	public int addChamyeojaGeneral(ChamyeoVo vo);
	
	/**
	 * 과목 이름을 입력하면 과목 id를 반환하는 DAO
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	public String findSubjId(String title);
	
	/**
	 * 클래스 id에 해당하는 클래스의 정보를 가져오는 메소드
	 * @param clasId
	 * @return 클래스 id에 해당하는 클래스의 정보 Vo
	 */
	public ClassVo getClassDetail(String clasId);
	
	/**
	 * 세션 사용자의종료된 클래스 조회
	 * @param map
	 * @return
	 */
	public List<ClassVo> getClassListByStatus(String clchAccountId);
	
	/**
	 * 클래스 like문 검색
	 * @param map
	 * @return
	 */
	public List<ClassVo> searchClassList(Map<String, Object> map);
	/**
	 * 클래스 like문 검색 갯수
	 * @param map
	 * @return
	 */
	public int searchClassListCount (String subject);
	
	
	/**
	 * 참여 중인 클래스 조회 (마이페이지)
	 * @param clchAccountId
	 * @return
	 */
	public List<ClassVo> myPageClassList (Map<String, Object> map);
	/**
	 * 위 쿼리의  ROW 갯수 반환
	 * @param clchAccountId
	 * @return
	 */
	public int myPageClassListCount (String clchAccountId);
	
	
	/**
	 * 종료된 클래스 조회 (마이페이지)
	 * @param clchAccountId
	 * @return
	 */
	public List<ClassVo> myPageEndClassList (Map<String, Object> map);
	/**
	 * 위 쿼리의  ROW 갯수 반환
	 * @param clchAccountId
	 * @return
	 */
	public int myPageEndClassListCount (String clchAccountId);
	
	/**
	 * 수강료 확정 상태 확인
	 * @param map 클래스 ID, 요청상태
	 * @return
	 */
	public SugangryoVo getRequestedSugangryo(String sugaClasId);
	
	/**
	 * 수강료 요청시 강사의 참여자 테이블 결제상태 업데이트
	 * @param map
	 * @return
	 */
	public int updateInstr(Map<String, Object> map);
}
