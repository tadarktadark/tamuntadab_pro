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
				JsonObject userInfoJSON = getNaverInfo(accToken);
				if(userInfoJSON == null) {
					return "";
				}
				
				Map<String,String> userinfo = JSONToMap(userInfoJSON, "N",refToken);
				Boolean isc = commUserService.searchEmailService(userinfo);//이메일에 대한 정보가 있는지 확인한다.
				if(isc) {
					System.out.println("가입 정보 있음");
				}else{//가입정보가 없을 때 해당 메일로 조회된 정보를 가져와 파싱 해해서 맵에 넣어준다..
					int n = socialUserService.naverRegist(userinfo);
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
	
	public JsonObject getNaverInfo(String accToken) { // 토큰을 이용해 해당 토큰에 정보를 가져온다.
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
	
	@RequestMapping(value = "kakaoRedirect.do")
	public String kakaoCallback(String code, String state, HttpSession session) {
		
		try {
			URL url = new URL(uvo.getGetKakaoTokenUrl());
			 HttpURLConnection kakaoConn = (HttpURLConnection) url.openConnection();
		        kakaoConn.setRequestMethod("POST");
		        kakaoConn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		        // POST 요청을 수행하려면 setDoOutput()을 true로 설정한다.
		        kakaoConn.setDoOutput(true);
		        
		        // POST 요청 본문을 생성한다.
		        String postData = 
		        		"grant_type=authorization_code"
		                + "&client_id=" + cvo.getKakaoClientID()
		                + "&redirect_uri=" + uvo.getKakaoRedirect()
		                + "&code=" + code
		                + "&state=" + state
		                + "&client_secret=" + cvo.getKakaoSecretCode();
		        
		        try (OutputStream os = kakaoConn.getOutputStream()) {
		            byte[] input = postData.getBytes("utf-8");
		            os.write(input, 0, input.length);
		        }
		        int responseCode = kakaoConn.getResponseCode();
		        
		        BufferedReader br;
			      if (responseCode == 200) { // 정상 호출
			        br = new BufferedReader(new InputStreamReader(kakaoConn.getInputStream(),"UTF-8"));
			      } else {  // 에러 발생
			        br = new BufferedReader(new InputStreamReader(kakaoConn.getErrorStream(),"UTF-8"));
			      }
			      String inputLine;
			      StringBuilder res = new StringBuilder();
			      while ((inputLine = br.readLine()) != null) {
			        res.append(inputLine);
			      }
			      br.close();
			      	JsonObject jsonObject = JsonParser.parseString(res.toString()).getAsJsonObject();
					String accToken = jsonObject.get("access_token").getAsString();
					String refToken = jsonObject.get("refresh_token").getAsString();
					JsonObject kakaoinfo = getKakaoInfo(accToken);
//					log.info(kakaoinfo.toString());
					Map<String,String> userinfo = JSONToMap(kakaoinfo,"K",refToken);
					
					System.out.println(userinfo.toString());
					
					kakaoConn.disconnect();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return "";
		}	 
	
		public JsonObject getKakaoInfo(String accToken) {
			try {
				URL url = new URL(uvo.getGetKakaorInfo());
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", "Bearer " + accToken);
				conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
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
		
		
		public Map<String,String> JSONToMap(JsonObject obj , String site,String refToken){
			Map<String,String> userinfo = new HashMap<String, String>();
			if(site.equals("N")) {
				userinfo.put("userEmail", obj.getAsJsonObject("response").get("email").getAsString());
				userinfo.put("site","N");
				userinfo.put("userRefreshToken", refToken);
				userinfo.put("userName", obj.getAsJsonObject("response").get("name").getAsString());
				userinfo.put("userGender", obj.getAsJsonObject("response").get("gender").getAsString());
				userinfo.put("userBirth", (obj.getAsJsonObject("response").get("birthyear").getAsString())+"-"
						+(obj.getAsJsonObject("response").get("birthday").getAsString()));
				userinfo.put("userPhoneNumber", obj.getAsJsonObject("response").get("mobile").getAsString().replace("-",""));
				userinfo.put("userProfileFile", obj.getAsJsonObject("response").get("profile_image").getAsString());
			}else if(site.equals("K")) {
				
				userinfo.put("userEmail", obj.getAsJsonObject("kakao_account").get("email").getAsString());
				
				userinfo.put("site","K");
				
				userinfo.put("userRefreshToken", refToken);
				
				userinfo.put("userName", obj.getAsJsonObject("kakao_account").get("name").getAsString());
				
				userinfo.put("userGender", obj.getAsJsonObject("kakao_account").get("gender").getAsString());
			
				userinfo.put("userBirth", (obj.getAsJsonObject("kakao_account").get("birthyear").getAsString())+"-"
						+(obj.getAsJsonObject("kakao_account").get("birthday").getAsString().substring(0,2)+"-")
						+(obj.getAsJsonObject("kakao_account").get("birthday").getAsString().substring(2,4)));
				userinfo.put("userPhoneNumber", (obj.getAsJsonObject("kakao_account").get("phone_number").getAsString()).replace("+82","0").replace("-","").replace(" ",""));
				userinfo.put("userProfileFile", obj.getAsJsonObject("kakao_account").get("profile_image_url").getAsString());
			}else if(site.equals("G")) {
				
			}
			return userinfo;
		}
	}