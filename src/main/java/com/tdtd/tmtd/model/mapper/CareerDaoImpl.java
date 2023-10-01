package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.CareerVo;

/**
 * CAREER 테이블 관련 dao
 * @author 문희애
 */
@Repository
public class CareerDaoImpl implements ICareerDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.CareerDaoImpl.";
	
	/**
	 * careId 생성시 오늘 기준 max id 조회
	 */
	@Override
	public String selectMaxIdToday(String datePrefix) {
		return sqlSession.selectOne(NS+"selectMaxIdToday",datePrefix);
	}
	
	/**
	 * (개인)경력 인증 내용 등록
	 */
	@Override
	public int insertCareer(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertCareer", map);
	}
	
	/**
	 * (개인)경력 인증 요청 전체 개수 조회
	 */
	@Override
	public int getMyCareerCount(String userAccountId) {
		return sqlSession.selectOne(NS+"getMyCareerCount", userAccountId);
	}

	/**
	 * (개인)경력 인증 요청 리스트 조회
	 */
	@Override
	public List<CareerVo> getMyCareerList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getMyCareerList", map);
	}
	
	/**
	 * (관리자)경력 요청 총 개수
	 */
	@Override
	public int getCareerCount() {
		return sqlSession.selectOne(NS+"getCareerCount");
	}

	/**
	 * (관리자)경력 요청 전체 리스트 조회
	 */
	@Override
	public List<CareerVo> getCareerList(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getCareerList", map);
	}

	/**
	 * (관리자)경력 내용 수정
	 */
	@Override
	public int updateCareer(CareerVo vo) {
		return sqlSession.update(NS+"updateCareer", vo);
	}

	/**
	 * (관리자)경력 인증시 status 업데이트
	 */
	@Override
	public int updateCareerS(Map<String, Object> map) {
		return sqlSession.update(NS+"updateCareerS",map);
	}

	/**
	 * (관리자)경력 인증시 인증된 강사로 업데이트
	 */
	@Override
	public int updateCareerCert(Map<String, Object> map) {
		return sqlSession.update(NS+"updateCareerCert", map);
	}
	
	/**
	 * (개인)기존 동일 회사명의 경력증명서 등록시 status 업데이트 
	 */
	@Override
	public int updateCareerR(String careId) {
		return sqlSession.update(NS+"updateCareerR", careId);
	}

	/**
	 * (관리자)반려시 status 및 반려사유 업데이트
	 */
	@Override
	public int updateCareerB(Map<String, Object> map) {
		return sqlSession.update(NS+"updateCareerB", map);
	}

	/**
	 * (관리자)경력 승인 후 목록 삭제시 status 업데이트
	 */
	@Override
	public int updateCareerD(String careId) {
		return sqlSession.update(NS+"updateCareerD", careId);
	}

	/**
	 * (관리자)경력 내용 DB 삭제
	 */
	@Override
	public int deleteCareer(String careId) {
		return sqlSession.delete(NS+"deleteCareer" ,careId);
	}

	/**
	 * (Cron)반려 후 10일 이내 재등록 안된 요청 자동 삭제
	 */
	@Override
	public int deleteCareerCron() {
		return sqlSession.delete(NS+"deleteCareerCron");
	}

	/**
	 * (강사 게시판) 강사의 경력 사항 조회
	 */
	@Override
	public List<CareerVo> getOneInstrCareer(String userAccountId) {
		return sqlSession.selectList(NS+"getOneInstrCareer", userAccountId);
	}

}
