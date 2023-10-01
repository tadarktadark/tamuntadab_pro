package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IInstrDao;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;

/**
 * 강사 프로필, 강사 게시판 관련 Service
 * @author 문희애
 *
 */
@Service
public class InstrServiceImpl implements IInstrService {
	
	@Autowired
	private IInstrDao dao;

	/**
	 * 마이페이지 강사 등록 프로필 조회
	 */
	@Override
	public InstrVo getMyInstrProfile(String userAccountId) {
		return dao.getMyInstrProfile(userAccountId);
	}

	/**
	 * 강사 프로필 등록 (학력 관련 transaction)
	 */
	@Override
	public int insertInstrProfile(InstrVo vo) {
		int m = dao.insertInstrProfile(vo);
		if (vo.getInstrEduVo() != null && !vo.getInstrEduVo().isEmpty()) {
			System.out.println("$$$$$$$$$$$$$insertInstrEdulevel실행");
			System.out.println("$$$$$$$$$$$$$ seq:"+vo.getInprSeq());
	        List<InstrEduVo> eduLevels = vo.getInstrEduVo();
	        for (InstrEduVo eduLevel : eduLevels) {
	            eduLevel.setInedInprSeq(vo.getInprSeq());
	            
	            int result = dao.insertInstrEdulevel(eduLevel);
	            if (result == 0) {
	                return 0; 
	            }
	        }
	        
	        return 1; 
	    }
		return m;
	}

	/**
	 * 강사 프로필 수정 (학력 관련 transaction)
	 */
	@Override
	public int updateInstrProfile(InstrVo vo) {
		int m = dao.updateInstrProfile(vo);
		if (vo.getInstrEduVo() != null && !vo.getInstrEduVo().isEmpty()) {
	        List<InstrEduVo> eduLevels = vo.getInstrEduVo();
	        for (InstrEduVo eduLevel : eduLevels) {
	            eduLevel.setInedInprSeq(vo.getInprSeq());
	            
	            int result = dao.updateInstrEdulevel(eduLevel);
	            if (result == 0) {
	            	result = dao.insertInstrEdulevel(eduLevel);
	            }
	        }
	        
	        return 1;  // 성공적으로 업데이트되었을 경우 반환값 변경 가능
	    }
	    
	    return m;
	}
	
	/**
	 * 강사 학력 삭제
	 */
	@Override
	public int deleteInstrEdulevel(String inedSeq) {
		return dao.deleteInstrEdulevel(inedSeq);
	}

	/**
	 * 강사 게시판 - 강사 전체 리스트 조회
	 */
	@Override
	public List<InstrVo> getAllInstr(Map<String, Object> map) {
		return dao.getAllInstr(map);
	}
	
	/**
	 * 강사 전체 리스트 총 개수
	 */
	@Override
	public int getAllInstrCount() {
		return dao.getAllInstrCount();
	}

	/**
	 * 강사 좋아요 정보 업데이트
	 */
	@Override
	public int updateInstrLike(Map<String, Object> map) {
		return dao.updateInstrLike(map);
	}

	/**
	 * 강사 조회수 정보 업데이트
	 */
	@Override
	public int updateInstrView(Map<String, Object> map) {
		return dao.updateInstrView(map);
	}
	
	/**
	 * 강사 좋아요, 조회수 정보 조회
	 */
	@Override
	public InstrVo getlikeViewUser(String inprAccountId) {
		return dao.getlikeViewUser(inprAccountId);
	}

	/**
	 * 강사 간략 프로필 조회
	 */
	@Override
	public InstrVo getOneInstrSimple(Map<String, Object> map) {
		return dao.getOneInstrSimple(map);
	}

	/**
	 * 강사 상세 프로필 조회
	 */
	@Override
	public InstrVo getOneInstrProfile(String userAccountId) {
		return dao.getOneInstrProfile(userAccountId);
	}

	/**
	 * 강사 클래스 이력 조회
	 */
	@Override
	public List<InstrVo> getOneInstrClass(Map<String, Object> map) {
		return dao.getOneInstrClass(map);
	}
	
	/**
	 * 강사 클래스 이력 - 매칭 후 취소된 강의 수
	 */
	@Override
	public int getCountClassCancel(String userAccountId) {
		return dao.getCountClassCancel(userAccountId);
	}

	/**
	 * 강사 후기 리스트 조회
	 */
	@Override
	public List<ReviewVo> getOneIntrReview(Map<String, Object> map) {
		return dao.getOneIntrReview(map);
	}
	
	/**
	 * 강사 매칭된 클래스 총 개수
	 */
	@Override
	public int classTotalCount(String userAccountId) {
		return dao.classTotalCount(userAccountId);
	}
	
	/**
	 * 강사 후기 총 개수
	 */
	@Override
	public int reviewTotalCount(String userAccountId) {
		return dao.reviewTotalCount(userAccountId);
	}

}
