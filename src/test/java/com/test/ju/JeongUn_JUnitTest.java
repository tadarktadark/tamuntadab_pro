package com.test.ju;

import static org.junit.Assert.assertEquals;
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
import com.tdtd.tmtd.model.mapper.ISocialUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class JeongUn_JUnitTest {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private ICommUserDao cdao;
	
	@Autowired
	private ISocialUserDao sdao;
	
	//WOON [회원관리 : 이메일 중복 테스트]
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
	//WOON [회원관리 : 닉네임 중복 테스트]
//	@Test
	public void searchNickNameTest() {
		Boolean isc = cdao.searchNickName("멋쟁이토마토");
		System.out.println(isc);
		assertNotNull(isc);
	}
	
	//WOON [회원관리 : 일반유저 회원가입 테스트]
//	@Test
	public void insertCommUser() {
		Map<String, Object> userProfile = new HashMap<String, Object>(){{
			put("userEmail", "hansome@tomato.com"); //
			put("userPassword", "tomato1");//
			put("userName", "멋쟁이토마토");//
			put("userPhoneNumber", "01066389809");//
			put("userAutoLoginToken", "tomatoo");
			put("userGender", "F");//
			put("userBirth", "19910101");//
		}};
		int n = cdao.registCommUser(userProfile);
		assertEquals(n, 1);
	}
	
	//WOON [회원관리 : 네이버 회원가입 테스트]
	@Test
	public void insertNaverUser() {
		Map<String, Object> userProfile = new HashMap<String, Object>(){{
			put("userEmail", "hansome@tomato.com");
			put("userRefreshToken", "tomato1");
			put("userName", "멋쟁이토마토");
			put("userPhoneNumber", "01066389809");
			put("userAutoLoginToken", "tomatoo");
			put("userGender", "F");
			put("userBirth", "19910101");
			put("userProfileFile", "이미지");
		}};
		int n = sdao.registNaverUser(userProfile);
		assertEquals(n, 1);
	}
	
	//WOON [회원관리 : 카카오 회원가입 테스트]
	public void insertKAKAOUser() {
		Map<String, Object> userProfile = new HashMap<String, Object>(){{
			put("userEmail", "hansome@tomato.com");
			put("userRefreshToken", "tomato1");
			put("userName", "멋쟁이토마토");
			put("userPhoneNumber", "01066389809");
			put("userAutoLoginToken", "tomatoo");
			put("userGender", "F");
			put("userBirth", "19910101");
		}};
		int n = sdao.registKakaoUser(userProfile);
		assertEquals(n, 1);
	}
	
	//WOON [회원관리 : 구글 회원가입 테스트]
	public void insertGoogleUser() {
		Map<String, Object> userProfile = new HashMap<String, Object>(){{
			put("userEmail", "hansome@tomato.com");
			put("userRefreshToken", "tomato1");
			put("userName", "멋쟁이토마토");
			put("userPhoneNumber", "01066389809");
			put("userAutoLoginToken", "tomatoo");
			put("userGender", "F");
			put("userBirth", "19910101");
		}};
		int n = sdao.registGoogleUser(userProfile);
		assertEquals(n, 1);
	}

}
