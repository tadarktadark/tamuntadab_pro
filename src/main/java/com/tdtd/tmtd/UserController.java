package com.tdtd.tmtd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tdtd.tmtd.model.service.ICommUserService;
import com.tdtd.tmtd.vo.ClientVo;
import com.tdtd.tmtd.vo.URLVo;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
@Slf4j
public class UserController {
	
	private URLVo uvo = new URLVo();
	private ClientVo cvo = new ClientVo();
	Gson gson = new Gson();
	
	@Autowired
	private ICommUserService commUserService;
	
	@Autowired
	private JavaMailSender mailsender;
	
	@RequestMapping(value = "/regist.do", method=RequestMethod.GET)
	public String registForm() {
		return "regist";
	}
	
	@RequestMapping(value = "/searchEmail.do", method =RequestMethod.POST)
	@ResponseBody
	public Boolean searchEmail(@RequestParam Map<String,String> map) {
		return commUserService.searchEmailService(map);
	}
	
	@RequestMapping(value = "/sendMail.do", method =RequestMethod.POST)
	@ResponseBody
	public String sendmail(@RequestParam Map<String,String> map) {
		log.info("받은 매일 {}",map.get("userEmail"));
		
		Map<String,String> sendmap = new HashMap<String, String>();
		
		MimeMessage msg = mailsender.createMimeMessage();
		
		try {
			MimeMessageHelper msgHelper
					= new MimeMessageHelper(msg,false,"UTF-8");
			map.put("code", UUID.randomUUID().toString().substring(0,8));
			msgHelper.setFrom("juojuo9809@naver.com");
			msgHelper.setTo(map.get("userEmail"));
			msgHelper.setSubject("타문타답 인증번호"+map.get("code"));
			msgHelper.setText("타문타답 인증번호"+map.get("code"));
			map.put("isc", "true");
			mailsender.send(msg);
		} catch (MessagingException e) {
			map.put("isc", "false");
		}
		Gson gson = new Gson();
		String result = gson.toJson(map);
		return result;
	}
	
	@RequestMapping(value="/sendSMS.do", method=RequestMethod.POST)
	@ResponseBody
	public String sendSMS(@RequestParam String phoneNumber) {
		Map<String,String> sendMap = new HashMap<String, String>();
		
		//랜덤 난수 발생
		Random ran = new Random();
		sendMap.put("code", ""+ran.nextInt(10000));
		//coolSMS API사용
		DefaultMessageService messageService = NurigoApp.INSTANCE.initialize("NCSLBXKI8KF3NOKC", "4RC5BBKPJOLNURRUZA1ARTZQXPH7ZAHQ", "https://api.coolsms.co.kr");
		Message message = new Message();
		message.setFrom("01066389809");
		message.setTo(phoneNumber);
		message.setText("타문타답 문자 인증 번호 : "+sendMap.get("code"));
		try {
		  messageService.send(message);
		  sendMap.put("isc", "true");
		} catch (NurigoMessageNotReceivedException exception) {
		  System.out.println(exception.getFailedMessageList());
		  System.out.println(exception.getMessage());
		} catch (Exception exception) {
			sendMap.put("isc", "false");
		  System.out.println(exception.getMessage());
		}
		String result = gson.toJson(sendMap);
		return result;
	}
	
	@RequestMapping(value = "/registration.do", method = RequestMethod.POST)
	public String registration(@RequestParam Map<String,Object> map) {
			map.put("userAutoLoginToken",(UUID.randomUUID())+(map.get("userPassword").toString().substring(0, 4))+(map.get("userGender"))+(map.get("userAuth"))+(map.get("userEmail").toString().substring(0, 2)));
			int n = commUserService.registCommUser(map);
			if(n!=1) {
				return "redirect:/regist.do";
			}else {
				return "redirect:/home.do";
			}
	}
	
	@RequestMapping(value="/registForm.do",method= RequestMethod.GET)
	public String registinputForm() {
		return "registform";
	}
	
	@RequestMapping(value = "naverRedirect.do")
	public String naverCallback(String code, String state,HttpSession session) {
		log.info("naverCallback");
		String tokenUrl = uvo.getGetNaverTokenUrl();
		
		try {
			URL url = new URL(tokenUrl);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			//POST 요청 수행을 위한 setDoOutPOST()
			con.setDoOutput(true);
			
			String postData = 
	        		"grant_type=authorization_code"
	                + "&client_id=" + cvo.getNaverClientID()
	                + "&code=" + code
	                + "&state=" + state
	                + "&client_secret=" + cvo.getNaverSecretCode();
			
	        try (OutputStream os = con.getOutputStream()) {
	            byte[] input = postData.getBytes("utf-8");
	            os.write(input, 0, input.length);
	        }
	        
			int resCode = con.getResponseCode();
			
			BufferedReader br;
			
			if(resCode==200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"UTF-8"));
			}
			String inputLine;
			StringBuilder res = new StringBuilder();
			
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if(resCode==200) { //200일 때 정보 받아오기
				JsonObject jsonObject = JsonParser.parseString(res.toString()).getAsJsonObject();
				String accToken = jsonObject.get("access_token").getAsString();
				String refToken = jsonObject.get("refresh_token").getAsString();
				JsonObject naverinfo = getNaverInfo(accToken);
				
				String email = naverinfo.getAsJsonObject("response").get("email").getAsString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public JsonObject getNaverInfo(String accToken) {
		try {
			URL url = new URL(uvo.getGetNaverInfo());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + accToken);
			
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // API 응답 읽기
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                // JSON 응답 파싱
                JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
                return jsonObject;
            } else {
                return null;
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}