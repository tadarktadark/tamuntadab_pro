package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ReviewVo;

/**
 * IReviewDao 관련 Service
 * @author 문희애
 *
 */
public interface IReviewService {
	
		/**
		 * 마이페이지 작성 후기 리스트 조회
		 */
		public List<ReviewVo> getMyReview(Map<String, Object> map);
		
		/**
		 * 마이페이지 작성 후기 리스트 총 개수
		 */
		public int myReviewTotalCount(String userAccountId);
		
		/**
		 * 후기 등록 (CHAMYEOJA 업데이트 transaction)
		 */
		public int insertReview(ReviewVo vo, Map<String, Object> map);
		
		/**
		 * 후기 삭제 (CHAMYEOJA 업데이트 transaction)
		 */
		public int deleteReview(String seq, String clasId);

}
