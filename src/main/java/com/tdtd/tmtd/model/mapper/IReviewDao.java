package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ReviewVo;

public interface IReviewDao {

	//TDT026 getMyReview
	public List<ReviewVo> getMyReview(Map<String, Object> map);
	
	public int myReviewTotalCount(String userAccountId);
	
	//TDT027 insertReview
	public int insertReview(ReviewVo vo);
	
	public int updateReviewStatusY(Map<String, Object> map);
	
	//TDT028 deleteReview
	public int deleteReview(String seq);
	
	public int updateReviewStatusN(String clasId);
}
