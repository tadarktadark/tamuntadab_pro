package com.tdtd.tmtd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tdtd.tmtd.model.service.ICommUserService;
import com.tdtd.tmtd.model.service.ISocialUserService;
import com.tdtd.tmtd.vo.ClientVo;
import com.tdtd.tmtd.vo.URLVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SocialUserController {

	private URLVo uvo = new URLVo();
	private ClientVo cvo = new ClientVo();
	Gson gson = new Gson();

	
	@Autowired
	private ICommUserService commUserService;
	@Autowired
	private ISocialUserService socialUserService;
	
	//네이버 유저 전용
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
				Map<String,String> map = new HashMap<String, String>();
				map.put("userEmail", email);
				map.put("site","N");
				Boolean isc = commUserService.searchEmailService(map);
				if(isc) {
					System.out.println("가입 정보 있음");
				}else{
					map.put("userRefreshToken", refToken);
					map.put("userName", naverinfo.getAsJsonObject("response").get("name").getAsString());
					
					map.put("userGender", naverinfo.getAsJsonObject("response").get("gender").getAsString());
					
					map.put("userBirth", (naverinfo.getAsJsonObject("response").get("birthyear").getAsString())+"-"
							+(naverinfo.getAsJsonObject("response").get("birthday").getAsString()));
					
					map.put("userPhoneNumber", naverinfo.getAsJsonObject("response").get("mobile").getAsString().replace("-",""));
					
					map.put("userProfileFile", naverinfo.getAsJsonObject("response").get("profile_image").getAsString());
					
					
					int n = socialUserService.naverRegist(map);
					
					if(n >0) {
						System.out.println("회원가입 성공");
					}
				}
				
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
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getUserInfo() {
		
		return "";
	}
}
