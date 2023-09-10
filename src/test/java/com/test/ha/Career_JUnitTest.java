package com.test.ha;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tdtd.tmtd.model.service.ICareerService;
import com.tdtd.tmtd.vo.CareerVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Career_JUnitTest {

	@Autowired
	private ICareerService service;
	
	private String createId() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		
		String datePrefix = "CA"+ sdf.format(date);
		
		String maxIdToday = service.selectMaxIdToday(datePrefix);
		
		int seq;
		
		if(maxIdToday == null) {
			seq = 1;
		} else {
			seq = Integer.parseInt(maxIdToday.substring(8))+1;
		}
		return datePrefix + String.format("%03d", seq);
	}
	
//	@Test
	public void insertCareer() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("careId", createId());
			put("careAccountId","TMTD142");
			put("careName","홍길동");
			put("careContact","010-1234-5678");
			put("careSosok","개발팀");
			put("carePosition","대리");
			put("carePeriod","2020.01.07~2022.04.10");
			put("careJob","개발");
			put("careIssuer","김발급");
			put("careIssuerContact","02-123-1234");
			put("careDate","2023년 09월 01일");
			put("careCompany","(주)자바카페");
		}};
		int n = service.insertCareer(map);
		assertEquals(1, n);
	}
	
	@Test
	public void getMyCareerList() {
		Map<String, Object> map =new HashMap<String, Object>(){{
			put("userAccountId", "TMTD142");
			put("start", "1");
			put("end", "4");
		}};
		List<CareerVo> list = service.getMyCareerList(map);
		assertNotNull(list);
	}
	
//	@Test
	public void getCareerList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 1);
		map.put("end", 4);
		List<CareerVo> list = service.getCareerList(map);
		assertNotNull(list);
	}
	
//	@Test
	public void updateCareer() {
		CareerVo vo = new CareerVo();
		vo.setCarePosition("기획");
		int n = service.updateCareer(vo);
		assertEquals(1, n);
	}
	
//	@Test
	public void updateCareerS() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("careId", "CA230909001");
		map.put("userAccountId", "TMTD142");
		
		int n = service.updateCareerS(map);
		assertEquals(1, n);
	}
	
//	@Test
	public void updateCareerB() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("careId", "CA230909002");
		map.put("careReason", "뭔가 맘에 안들어용 다시해오세요");
		int n = service.updateCareerB(map);
		assertEquals(1, n);
	}
	
//	@Test
	public void updateCareerD() {
		int n = service.updateCareerD("CA230909001");
		assertEquals(1, n);
	}
	
//	@Test
	public void deleteCareer() {
		int n = service.deleteCareer("CA230909002");
		assertEquals(1, n);
	}
	
	@Test
	public void getOneInstrCareer() {
		CareerVo vo = service.getOneInstrCareer("TMTD142");
		assertNotNull(vo);
	}

}
