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
	public int insertReview(ReviewVo vo, Map<String, Object> map) {
		
		int m = dao.insertReview(vo);
		int n = dao.updateReviewStatusY(map);
		
		return (m>0||n>0)?1:0;
	}

	@Override
	public int deleteReview(Map<String, String[]> map) {
		
		int m = dao.deleteReview(map);
		int n = dao.updateReviewStatusN(map);
		
		return (m>0||n>0)?1:0;
	}

}
