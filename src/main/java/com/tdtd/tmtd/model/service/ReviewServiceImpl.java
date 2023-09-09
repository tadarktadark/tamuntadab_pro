package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IReviewDao;
import com.tdtd.tmtd.vo.ReviewVo;

@Service
public class ReviewServiceImpl implements IReviewService {
	
	@Autowired
	private IReviewDao dao;

	@Override
	public List<ReviewVo> getMyReview(Map<String, Object> map) {
		return dao.getMyReview(map);
	}

	@Override
	public int insertReview(ReviewVo vo) {
		return dao.insertReview(vo);
	}

	@Override
	public int deleteReview(Map<String, String[]> map) {
		return dao.deleteReview(map);
	}

}
