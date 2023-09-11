package com.test.kh;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tdtd.tmtd.model.mapper.IClassDao;
import com.tdtd.tmtd.model.service.IClassService;
import com.tdtd.tmtd.model.service.ISubjectService;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.SubjectTagVo;
import com.tdtd.tmtd.vo.SubjectVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ClassJunitTest {

	
	@Autowired
	private IClassService cService;
	
	@Autowired
	private IClassDao cDao;
	
//	@Autowired
//	private ISubjectService sService;
	
	
	@Test
	public void test() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("first", 1);
//		map.put("last", 20);		
//		List<ClassVo> list = cService.getClassList(map);
//		log.info("출력값 : {}", list);
//		assertNotNull(list);
		
//		int n = cService.getClassListCount();
//		assertNotNull(n);
		
//		ClassVo cVo = new ClassVo();
//		cVo.setClasTitle("도커 알려주실 분 구합니다");
//		cVo.setClasLocation(1129068500);
//		cVo.setClasStatus("모집");
//		cVo.setClasHuimangInwon(8);
//		cVo.setClasSueopNaljja("2023-09-23");
//		cVo.setClasChoisoSugangnyo(20000);
//		cVo.setClasChoidaeSugangnyo(40000);
//		cVo.setClasSeongbyeolJehan("GFREE");
//		cVo.setClasNaiJehan("21:30");
//		cVo.setClasSubjectJeongbo("[{\"SUBJ_ID\":\"1000000132\"},{\"SUBJ_ID\":\"1000000252\"}]");
//		cVo.setClasContent("혼자 하기 너무 힘드네요");
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("subjTitle", "확률과 통계3");
//		map.put("subjStudId", "TMTD74");
//		map.put("subjStatus", "A");
//		map.put("subjDelflag", "N");
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		map2.put("sutaTitle", "확률과 통계3");
//		int n = cService.addClassWithSub(cVo, map, map2);
//		assertEquals(1, n);
	}

}
