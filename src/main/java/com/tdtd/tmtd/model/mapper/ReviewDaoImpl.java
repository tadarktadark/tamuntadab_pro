package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ReviewVo;


@Repository
public class ReviewDaoImpl implements IReviewDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	private final String NS = "com.tdtd.tmtd.model.mapper.ReviewDaoImpl.";
	
	
	@Override
	public List<ReviewVo> getMyReview(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getMyReview", map);
	}
	
	
	@Override
	public int myReviewTotalCount(String userAccountId) {
		return sqlSession.selectOne(NS+"myReviewTotalCount", userAccountId);
	}

	
	@Override
	public int insertReview(ReviewVo vo) {
		return sqlSession.insert(NS+"insertReview", vo);
	}
	
	
	@Override
	public int updateReviewStatusY(Map<String, Object> map) {
		return sqlSession.update(NS+"updateReviewStatusY", map);
	}

	
	@Override
	public int deleteReview(String seq) {
		return sqlSession.insert(NS+"deleteReview", seq);
	}
	
	
	@Override
	public int updateReviewStatusN(String clasId) {
		return sqlSession.update(NS+"updateReviewStatusN", clasId);
	}

}
