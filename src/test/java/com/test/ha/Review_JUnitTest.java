package com.test.ha;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.model.service.IReviewService;
import com.tdtd.tmtd.vo.ReviewVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Review_JUnitTest {

	@Autowired
	private IReviewService service;
	
//	@Test
	public void getMyReview() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccountId", "TMTD103");
		map.put("start", 1);
		map.put("end", 5);
		
		List<ReviewVo> list = service.getMyReview(map);
		assertNotNull(list);
	}
	
//	@Test
	public void insertReview() {
		ReviewVo vo = new ReviewVo(0, 1000000073, "자바의 신", 4, 3, 1, 5, "무난무난", 0, 0, null);
		int n = service.insertReview(vo);
		assertEquals(1, n);
	}
	
	@Test
	public void deleteReview() {
		
	}

}
