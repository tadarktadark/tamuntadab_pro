package com.test.ju;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.tdtd.tmtd.model.mapper.ICommUserDao;
import com.tdtd.tmtd.model.mapper.ISocialUserDao;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

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
//	@Test
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
	
	//WOON[메세지 전송 테스트]
	@Test
	public void sendSMS() {
		Map<String,String> sendMap = new HashMap<String, String>();
		
		//랜덤 난수 발생
		Random ran = new Random();
		sendMap.put("code", ""+ran.nextInt(10000));
		
		//coolSMS API사용
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSLBXKI8KF3NOKC", "4RC5BBKPJOLNURRUZA1ARTZQXPH7ZAHQ", "https://api.coolsms.co.kr");
		Message message = new Message();
		message.setFrom("01066389809");
		message.setTo("01046465753");
		message.setText("타문타답 문자 인증 번호 : "+sendMap.get("code")+"안녕 나는 임정운이야");
		try {
		  messageService.send(message);
		  sendMap.put("result", "true");
		} catch (NurigoMessageNotReceivedException exception) {
		  System.out.println(exception.getFailedMessageList());
		  System.out.println(exception.getMessage());
		} catch (Exception exception) {
			sendMap.put("result", "false");
		  System.out.println(exception.getMessage());
		}
		Gson gson = new Gson();
		String result = gson.toJson(sendMap);
	}

}
