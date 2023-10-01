package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ReviewVo;

/**
 * REVIEW 테이블 관련 dao
 * @author 문희애
 *
 */
@Repository
public class ReviewDaoImpl implements IReviewDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	private final String NS = "com.tdtd.tmtd.model.mapper.ReviewDaoImpl.";
	
	/**
	 * 마이페이지 작성 후기 리스트 조회
	 */
	@Override
	public List<ReviewVo> getMyReview(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getMyReview", map);
	}
	
	/**
	 * 마이페이지 작성 후기 리스트 총 개수
	 */
	@Override
	public int myReviewTotalCount(String userAccountId) {
		return sqlSession.selectOne(NS+"myReviewTotalCount", userAccountId);
	}

	/**
	 * 후기 등록
	 */
	@Override
	public int insertReview(ReviewVo vo) {
		return sqlSession.insert(NS+"insertReview", vo);
	}
	
	/**
	 * 후기 등록시 CHAMYEOJA 테이블 후기 status 업데이트
	 */
	@Override
	public int updateReviewStatusY(Map<String, Object> map) {
		return sqlSession.update(NS+"updateReviewStatusY", map);
	}

	/**
	 * 후기 삭제
	 */
	@Override
	public int deleteReview(String seq) {
		return sqlSession.insert(NS+"deleteReview", seq);
	}
	
	/**
	 * 후기 삭제시 CHAMYEOJA 테이블 후기 status 업데이트
	 */
	@Override
	public int updateReviewStatusN(String clasId) {
		return sqlSession.update(NS+"updateReviewStatusN", clasId);
	}

}
