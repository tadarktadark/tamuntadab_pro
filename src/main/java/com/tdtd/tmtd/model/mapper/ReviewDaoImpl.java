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
	public int insertReview(ReviewVo vo) {
		return sqlSession.insert(NS+"insertReview", vo);
	}

	@Override
	public int deleteReview(Map<String, String[]> map) {
		return sqlSession.insert(NS+"deleteReview", map);
	}

}
