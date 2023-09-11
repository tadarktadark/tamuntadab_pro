package com.test.sh;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tdtd.tmtd.model.service.IYeyakService;
import com.tdtd.tmtd.vo.GangeuisilVo;
import com.tdtd.tmtd.vo.GyeoljeVo;
import com.tdtd.tmtd.vo.YeyakVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@Transactional
public class YeyakJUnitTest {

	@Autowired
	private IYeyakService service;
	
//	@Test
	public void getGangeuisilCount() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("gacoSido", "광주");
//			put("gacoSigungu", "원주시");
		}};
		int n = service.getGangeuisilCount(map);
		assertEquals(12, n);
	}
	
//	@Test
	public void getGangeuisilSidoList() {
		List<String> list = service.getGangeuisilSidoList();
		assertNotNull(list);
	}

//	@Test
	public void getGangeuisilSigunguList() {
		List<String> list = service.getGangeuisilSigunguList("강원");
		assertNotNull(list);
	}
	
//	@Test
	public void getGangeuisilList() {
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("start", 1);
			put("end", 5);
//			put("gacoSido", "강원");
//			put("gacoSigungu", "원주시");
		}};
		List<GangeuisilVo> list = service.getGangeuisilList(map);
		assertNotNull(list);
	}
	
//	@Test
	public void getGangeuisilDetailList() {
		List<GangeuisilVo> list = service.getGangeuisilDetailList("GACO00001");
		assertNotNull(list);
	}
	
//	@Test
//	public void getYeyakDateList() {
//		List<GangeuisilVo> list = service.getYeyakDateList("GAGA00277");
//		assertNotNull(list);
//	}
	
//	@Test
	/*
	 * public void getYeyakTimeList(){ Map<String, Object> map = new HashMap<String,
	 * Object>(){{ put("gayeGagaId", "GAGA00265"); put("gayeYeyakDate", "20230912");
	 * }}; List<YeyakVo> list = service.getYeyakTimeList(map); assertNotNull(list);
	 * }
	 */
	
//	@Test
	public void insertYeakInfo() {
		YeyakVo yVo = new YeyakVo("GAGA00271", "TMTD1", "01000000001", "20230913", "1400", 2, 1000000013 , "B");
		GyeoljeVo gVo = new GyeoljeVo(30000*yVo.getGayeHours());
		int n = service.insertYeakInfo(yVo, gVo);
		assertNotNull(n);
		System.out.println(n);
	}
	
//	@Test
	public void getMyYeyakList() {
		List<YeyakVo> list = service.getMyYeyakList("TMTD1");
		assertNotNull(list);
	}
	
//	@Test
	public void updateYeyakDelflag() {
		int n = service.updateYeyakDelflag("YY202309090001");
		assertEquals(1, n);
	}
	
	@Test
	public void updateYeoyuTimeAdd() {
		int n = service.updateYeoyuTimeAdd("GAGA00277");
		assertEquals(1, n);
	}

}
