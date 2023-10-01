package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IReviewDao;
import com.tdtd.tmtd.vo.ReviewVo;

/**
 * IReviewDao 관련 Service
 * @author 문희애
 *
 */
@Service
public class ReviewServiceImpl implements IReviewService {
	
	@Autowired
	private IReviewDao dao;

	/**
	 * 마이페이지 작성 후기 리스트 조회
	 */
	@Override
	public List<ReviewVo> getMyReview(Map<String, Object> map) {
		return dao.getMyReview(map);
	}
	
	/**
	 * 마이페이지 작성 후기 리스트 총 개수
	 */
	@Override
	public int myReviewTotalCount(String userAccountId) {
		return dao.myReviewTotalCount(userAccountId);
	}

	/**
	 * 후기 등록 (CHAMYEOJA 업데이트 transaction)
	 */
	@Override
	public int insertReview(ReviewVo vo, Map<String, Object> map) {
		
		int m = dao.insertReview(vo);
		int n = dao.updateReviewStatusY(map);
		
		return (m>0||n>0)?1:0;
	}

	/**
	 * 후기 삭제 (CHAMYEOJA 업데이트 transaction)
	 */
	@Override
	public int deleteReview(String seq, String clasId) {
		
		int m = dao.deleteReview(seq);
		int n = dao.updateReviewStatusN(clasId);
		
		return (m>0||n>0)?1:0;
	}

}
