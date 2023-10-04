package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;

/**
 * 강사 프로필, 강사 게시판 관련 dao
 * @author 문희애
 *
 */
public interface IInstrDao {

	/**
	 * 강사 닉네임 업데이트시 프로필 regdate 업데이트를 위한 profile 존재 여부 select문
	 */
	public boolean hasInprProfile(Map<String, Object> userAccountId);
	
	/**
	 * 강사 닉네임 업데이트시 프로필 regdate 업데이트
	 */
	public int updateInprRegdate(Map<String, Object> userAccountId);
	
	/**
	 * 마이페이지 강사 등록 프로필 조회
	 */
	public InstrVo getMyInstrProfile(String userAccountId);
	
	/**
	 * 강사 프로필 등록
	 */
	public int insertInstrProfile(InstrVo vo);
	
	/**
	 * 강사 학력 등록
	 */
	public int insertInstrEdulevel(InstrEduVo vo);
	
	/**
	 * 강사 프로필 수정
	 */
	public int updateInstrProfile(InstrVo vo);
	
	/**
	 * 강사 학력 수정
	 */
	public int updateInstrEdulevel(InstrEduVo vo);
	
	/**
	 * 강사 학력 삭제
	 */
	public int deleteInstrEdulevel(String inedSeq);
	
	/**
	 * 강사 게시판 - 강사 전체 리스트 조회
	 */
	public List<InstrVo> getAllInstr(Map<String, Object> map);
	
	/**
	 * 강사 전체 리스트 총 개수
	 */
	public int getAllInstrCount();
	
	/**
	 * 강사 좋아요 정보 업데이트
	 */
	public int updateInstrLike(Map<String, Object> map);
	
	/**
	 * 강사 조회수 정보 업데이트
	 */
	public int updateInstrView(Map<String, Object> map);
	
	/**
	 * 강사 좋아요, 조회수 정보 조회
	 */
	public InstrVo getlikeViewUser(String inprAccountId);
	
	/**
	 * 강사 간략 프로필 조회
	 */
	public InstrVo getOneInstrSimple(Map<String, Object> map);
	
	/**
	 * 강사 상세 프로필 조회
	 */
	public InstrVo getOneInstrProfile(String userAccountId);
	
	/**
	 * 강사 클래스 이력 조회
	 */
	public List<InstrVo> getOneInstrClass(Map<String, Object> map);
	
	/**
	 * 강사 클래스 이력 - 매칭 후 취소된 강의 수
	 */
	public int getCountClassCancel(String userAccountId);
	
	/**
	 * 강사 후기 리스트 조회
	 */
	public List<ReviewVo> getOneIntrReview(Map<String, Object> map);
	
	/**
	 * 강사 매칭된 클래스 총 개수
	 */
	public int classTotalCount(String userAccountId);
	
	/**
	 * 강사 후기 총 개수
	 */
	public int reviewTotalCount(String userAccountId);
	
}
