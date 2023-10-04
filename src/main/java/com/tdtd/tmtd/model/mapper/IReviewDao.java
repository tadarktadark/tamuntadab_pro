package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ReviewVo;

/**
 * REVIEW 테이블 관련 dao
 * @author 문희애
 *
 */
public interface IReviewDao {

	/**
	 * 마이페이지 작성 후기 리스트 조회
	 */
	public List<ReviewVo> getMyReview(Map<String, Object> map);
	
	/**
	 * 마이페이지 작성 후기 리스트 총 개수
	 */
	public int myReviewTotalCount(String userAccountId);
	
	/**
	 * 후기 등록
	 */
	public int insertReview(ReviewVo vo);
	
	/**
	 * 후기 등록시 CHAMYEOJA 테이블 후기 status 업데이트
	 */
	public int updateReviewStatusY(Map<String, Object> map);
	
	/**
	 * 후기 삭제
	 */
	public int deleteReview(String seq);
	
	/**
	 * 후기 삭제시 CHAMYEOJA 테이블 후기 status 업데이트
	 */
	public int updateReviewStatusN(String clasId);
}
