package com.test.ha;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.service.IInstrService;
import com.tdtd.tmtd.vo.InstrVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Instr_JunitTest {
	
	@Autowired
	private IInstrService service;

//	@Test
	public void getMyInstrProfile() {
		InstrVo vo = service.getMyInstrProfile("TMTD101");
		assertNotNull(vo);		
	}
	
//	@Test
	public void insertInstrProfile() {
		Gson gson = new Gson();
		String subjects = gson.toJson(new String[] {"001", "006","070"});
		String major = gson.toJson(new String[] {"006"});
		
		InstrVo vo = new InstrVo();
		vo.setInprAccountId("TMTD142");
		vo.setInprIntro("잘 부탁드립니당");
		vo.setInprFee(50);
		vo.setInprSiteYoutube("https://www.youtube.com/watch?v=DFvKDNgbPrc");
		vo.setInprSiteWeb("https://s5hy25ni.atlassian.net/jira/software/projects/TDTD/code");
		vo.setInprSiteMobile("m.naver.com");
		vo.setInprSubjects(subjects);
		vo.setInprSubjectsMajor(major);
		/*
		 * vo.setInedStage(2); vo.setInedSchool("한양대학교"); vo.setInedMajor("컴퓨터공학과");
		 * vo.setInedMinor("심리학과"); vo.setInedStart("2014-03-15");
		 * vo.setInedEnd("2019-02-14");
		 */
		
		int n = service.insertInstrProfile(vo);
		assertEquals(1, n);
	}
	
//	@Test
	public void updateInstrProfile() {
		Gson gson = new Gson();
		String subjects = gson.toJson(new String[] {"001", "006","070", "080"});
		String major = gson.toJson(new String[] {"006"});
		
		InstrVo vo = new InstrVo();
		vo.setInprSeq(48);
		vo.setInprSubjects(subjects);
		vo.setInprSubjectsMajor(major);
		vo.setInprAccountId("TMTD142");
		vo.setInprIntro("잘 부탁드립니당");
		vo.setInprFee(50);
		vo.setInprSiteYoutube("https://www.youtube.com/watch?v=DFvKDNgbPrc");
		vo.setInprSiteWeb("https://s5hy25ni.atlassian.net/jira/software/projects/TDTD/code");
		vo.setInprSiteMobile("m.naver.com");
		/*
		 * vo.setInedStage(2); vo.setInedSchool("한양대학교"); vo.setInedMajor("컴퓨터공학과");
		 * vo.setInedMinor("심리학과"); vo.setInedStart("2014-03-15");
		 * vo.setInedEnd("2019-02-14");
		 */
		
		int n = service.updateInstrProfile(vo);
		assertEquals(1, n);
	}
	
//	@Test
	public void getAllInstr() {
		List<InstrVo> list = service.getAllInstr("reg");
		assertNotNull(list);
	}
	
//	@Test
	public void updateInstrLike() {
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(new Date());
		map.put("TMTD142", now);
		map.put("likeCount", 5);
		
		InstrVo vo = new InstrVo();
		int likeCount = (int)map.get("likeCount");
		vo.setInprLike(gson.toJson(map));
		vo.setInprLikeCount(likeCount);
		vo.setInprSeq(48);
		
		service.updateInstrLike(vo);
	}
	
//	@Test
	public void getOneInstrSimple() {
		InstrVo vo = service.getOneInstrSimple("TMTD141");
		assertNotNull(vo);
	}
	
//	@Test
	public void getOneInstrProfile() {
		InstrVo vo = service.getOneInstrProfile("TMTD141");
		assertNotNull(vo);
	}
	
//	@Test
	public void getOneInstrClass() {
		List<InstrVo> vo = service.getOneInstrClass("TMTD131");
		assertNotNull(vo);
	}
	
	@Test
	public void getOneIntrReview() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccountId", "TMTD103");
		map.put("order", "desc");
		
		List<InstrVo> list = service.getOneIntrReview(map);
		
		assertNotNull(list);
	}
	
}
