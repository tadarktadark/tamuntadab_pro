package com.test.sh;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tdtd.tmtd.model.service.ICommunityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Test_JUnitTest {

	@Autowired
	private ICommunityService service;
	
	@Test
	@Transactional
	public void test() {
		fail("Not yet implemented");
	}

}
