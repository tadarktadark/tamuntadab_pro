package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ReviewVo;

public interface IReviewService {
	
		//TDT026 getMyReview
		public List<ReviewVo> getMyReview(Map<String, Object> map);
		
		//TDT027 insertReview
		public int insertReview(ReviewVo vo);
		
		//TDT028 deleteReview
		public int deleteReview(Map<String, String[]> map);

}
