package com.test.ha;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Test
	public void insertInstrProfile() {
		InstrVo vo = new InstrVo();
		vo.setInprAccountId("TMTD142");
		vo.setInprIntro("잘 부탁드립니당");
		vo.setInprFee(50);
		vo.setInprSiteYoutube("https://www.youtube.com/watch?v=DFvKDNgbPrc");
		vo.setInprSiteWeb("https://s5hy25ni.atlassian.net/jira/software/projects/TDTD/code");
		vo.setInprSiteMobile("m.naver.com");
		vo.setInprSubjects("['001', '006', '070']");
		vo.setInprSubjectsMajor("['006']");
		vo.setInedStage(2);
		vo.setInedSchool("한양대학교");
		vo.setInedMajor("컴퓨터공학과");
		vo.setInedMinor("심리학과");
		vo.setInedStart("2014-03-15");
		vo.setInedEnd("2019-02-14");
		
		int n = service.insertInstrProfile(vo);
		assertEquals(1, n);
	}
	
//	@Test
	public void updateInstrProfile() {
		InstrVo vo = new InstrVo();
		vo.setInprSeq(42);
		vo.setInprSubjects("['001', '006', '070', '080']");
		
		int n = service.updateInstrProfile(vo);
		assertEquals(1, n);
	}
	
//	@Test
	public void getAllInstr() {
		
	}

}
