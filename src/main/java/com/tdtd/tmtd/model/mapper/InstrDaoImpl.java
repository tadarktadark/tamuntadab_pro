package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;

/**
 * 강사 프로필, 강사 게시판 관련 dao
 * @author 문희애
 *
 */
@Repository
public class InstrDaoImpl implements IInstrDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.InstrDaoImpl.";

	/**
	 * 강사 닉네임 업데이트시 프로필 regdate 업데이트를 위한 profile 존재 여부 select문
	 */
	@Override
	public boolean hasInprProfile(Map<String, Object> userAccountId) {
		return sqlSession.selectOne(NS+"hasInprProfile", userAccountId) != null;
	}
	
	/**
	 * 강사 닉네임 업데이트시 프로필 regdate 업데이트
	 */
	@Override
	public int updateInprRegdate(Map<String, Object> userAccountId) {
		return sqlSession.update(NS+"updateInprRegdate",userAccountId);
	}
	
	/**
	 * 마이페이지 강사 등록 프로필 조회
	 */
	@Override
	public InstrVo getMyInstrProfile(String userAccountId) {
		return sqlSession.selectOne(NS+"getMyInstrProfile", userAccountId);
	}

	/**
	 * 강사 프로필 등록
	 */
	@Override
	public int insertInstrProfile(InstrVo vo) {
		return sqlSession.insert(NS+"insertInstrProfile", vo);
	}

	/**
	 * 강사 학력 등록
	 */
	@Override
	public int insertInstrEdulevel(InstrEduVo vo) {
		return sqlSession.insert(NS+"insertInstrEdulevel", vo);
	}

	/**
	 * 강사 프로필 수정
	 */
	@Override
	public int updateInstrProfile(InstrVo vo) {
		return sqlSession.update(NS+"updateInstrProfile", vo);
	}

	/**
	 * 강사 학력 수정
	 */
	@Override
	public int updateInstrEdulevel(InstrEduVo vo) {
		return sqlSession.update(NS+"updateInstrEdulevel", vo);
	}
	
	/**
	 * 강사 학력 삭제
	 */
	@Override
	public int deleteInstrEdulevel(String inedSeq) {
		return sqlSession.delete(NS+"deleteInstrEdulevel", inedSeq);
	}

	/**
	 * 강사 게시판 - 강사 전체 리스트 조회
	 */
	@Override
	public List<InstrVo> getAllInstr(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getAllInstr", map);
	}
	
	/**
	 * 강사 전체 리스트 총 개수
	 */
	@Override
	public int getAllInstrCount() {
		return sqlSession.selectOne(NS+"getAllInstrCount");
	}

	/**
	 * 강사 좋아요 정보 업데이트
	 */
	@Override
	public int updateInstrLike(Map<String, Object> map) {
		return sqlSession.update(NS+"updateInstrLike", map);
	}

	/**
	 * 강사 조회수 정보 업데이트
	 */
	@Override
	public int updateInstrView(Map<String, Object> map) {
		return sqlSession.update(NS+"updateInstrView", map);
	}
	
	/**
	 * 강사 좋아요, 조회수 정보 조회
	 */
	@Override
	public InstrVo getlikeViewUser(String inprAccountId) {
		return sqlSession.selectOne(NS+"getlikeViewUser", inprAccountId);
	}

	/**
	 * 강사 간략 프로필 조회
	 */
	@Override
	public InstrVo getOneInstrSimple(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"getOneInstrSimple", map);
	}

	/**
	 * 강사 상세 프로필 조회
	 */
	@Override
	public InstrVo getOneInstrProfile(String userAccountId) {
		return sqlSession.selectOne(NS+"getOneInstrProfile", userAccountId);
	}

	/**
	 * 강사 클래스 이력 조회
	 */
	@Override
	public List<InstrVo> getOneInstrClass(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getOneInstrClass", map);
	}
	
	/**
	 * 강사 클래스 이력 - 매칭 후 취소된 강의 수
	 */
	@Override
	public int getCountClassCancel(String userAccountId) {
		return sqlSession.selectOne(NS+"getCountClassCancel", userAccountId);
	}

	/**
	 * 강사 후기 리스트 조회
	 */
	@Override
	public List<ReviewVo> getOneIntrReview(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getOneIntrReview", map);
	}
	
	/**
	 * 강사 매칭된 클래스 총 개수
	 */
	@Override
	public int classTotalCount(String userAccountId) {
		return sqlSession.selectOne(NS+"classTotalCount", userAccountId);
	}
	
	/**
	 * 강사 후기 총 개수
	 */
	@Override
	public int reviewTotalCount(String userAccountId) {
		return sqlSession.selectOne(NS+"reviewTotalCount", userAccountId);
	}

}
