package com.test.ju;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tdtd.tmtd.model.mapper.ICommUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class JeongUn_JUnitTest {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private ICommUserDao cdao;
	
//	@Test
	public void chkEmail() {
		Map<String,String> map = new HashMap<String, String>();
		map.put("userEmail", "test1@classtest.com");
		map.put("site", "T");
//		map.put("site", "N");
//		map.put("site", "K");
//		map.put("site", "G");
		Boolean isc = cdao.emailCheck(map);
		System.out.println(isc);
		assertNotNull(isc);
	}
	
	@Test
	public void searchNickNameTest() {
		Boolean isc = cdao.searchNickName("멋쟁이토마토");
		System.out.println(isc);
		assertNotNull(isc);
	}
	

}
