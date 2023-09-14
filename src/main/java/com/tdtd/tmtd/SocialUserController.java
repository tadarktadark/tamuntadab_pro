package com.tdtd.tmtd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tdtd.tmtd.model.service.ICommUserService;
import com.tdtd.tmtd.model.service.ISocialUserService;
import com.tdtd.tmtd.vo.ClientVo;
import com.tdtd.tmtd.vo.URLVo;
import com.tdtd.tmtd.vo.UserProfileVo;

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
	
	/**
	 * 사용자가 소셜을 통해 후 Redirect 했을 때 임의로 발급한 code와 state 를 통해서 AccessToken과 RefreshToken을 받아오는 메소드
	 * @param code 소셜에서 발급해준 code
	 * @param state 소셜에 code를 요청 할 때 입력했던 랜덤 Number
	 * @param session 사용자의 정보를 통해 해당 정보가 DB에 있을 경우 Session에 담아 로그인처리 해준다.
	 * @return 
	 */
	@RequestMapping(value = "naverRedirect.do")
	public String naverCallback(String code, String state,HttpSession session, HttpServletResponse resp) {
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
				String accToken = jsonObject.get("access_token").getAsString(); //액세스 토큰 받아오기
				String refToken = jsonObject.get("refresh_token").getAsString();//리프레쉬 토큰 받아오기
				JsonObject userInfoJSON = getNaverInfo(accToken);//엑세스 토큰을 이용해 정보를 받아오기
				if(userInfoJSON == null) {//받아온 정보가 없을 때 에러페이지로 보내기
					return "redirect:/";
				}
				
				Map<String,String> userinfo = JSONToMap(userInfoJSON,"N",refToken); //받아온 JSON 값을 MAP으로 바꾸기
				Boolean isc = commUserService.searchEmailService(userinfo);//이메일에 대한 정보가 있는지 확인한다.
				if(isc) {
					//이메일에 대한 정보를 받아와 만약 그 정보가 있을 경우 조회된 정보를 바탕으로 user정보를 받아온다.
					UserProfileVo uservo = socialUserService.socialLogin(userinfo);
					//해당 유저의 refresh토큰이 다를 경우
					if(!uservo.getUserRefreshToken().equals(userinfo.get("userRefreshToken"))) {
						//리프레쉬 토큰을 갱신해준다.
						socialUserService.updateRefToken(userinfo);
					}
					session.setAttribute("userInfo", uservo);
				}else{
					//가입 정보가 없을 경우 해당 유저의 정보를 insert 해준다.
					int n = socialUserService.naverRegist(userinfo);
					if(n >0) {
						//insert에 성공 했을 경우 해당 유저의 정보를 받아온다.
						UserProfileVo uservo = socialUserService.socialLogin(userinfo);
						//세션에 정보 저장 후
						session.setAttribute("userInfo", uservo);
						//메인으로 이동
						return "redirect:/";
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//토큰 받아오기를 실패 했을 경우 에러페이지로 이동
		return "redirect:/";
	}
	
	/**
	 * 사용자의 Accesstoken을 이용해 사용자의 정보를 받아오는 리턴해주는 메소드
	 * @param accToken 사용자의 정보에 접근하기 위한 accessToken
	 * @return jsonObject 사용자의 정보가 담긴 JSONObject
	 */
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
	
	/**
	 * 사용자가 소셜을 통해 후 Redirect 했을 때 임의로 발급한 code와 state 를 통해서 AccessToken과 RefreshToken을 받아오는 메소드
	 * @param code 소셜에서 발급해준 code
	 * @param state 소셜에 code를 요청 할 때 입력했던 랜덤 Number
	 * @param session 사용자의 정보를 통해 해당 정보가 DB에 있을 경우 Session에 담아 로그인처리 해준다.
	 * @return 
	 */
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
					
					if(kakaoinfo==null) {
						return "";
					}
					kakaoConn.disconnect();
					Map<String,String> userinfo = JSONToMap(kakaoinfo,"K",refToken);
					Boolean isc = commUserService.searchEmailService(userinfo);//이메일에 대한 정보가 있는지 확인한다.
					if(isc) {
						//이메일에 대한 정보를 받아와 만약 그 정보가 있을 경우 조회된 정보를 바탕으로 user정보를 받아온다.
						UserProfileVo uservo = socialUserService.socialLogin(userinfo);
						//해당 유저의 refresh토큰이 다를 경우
						if(!uservo.getUserRefreshToken().equals(userinfo.get("userRefreshToken"))) {
							//리프레쉬 토큰을 갱신해준다.
							socialUserService.updateRefToken(userinfo);
						}
						//사용자의 정지 여부 판단하기
						int cnt = commUserService.searchJeongJi(uservo);
						if(cnt!=0) {
							//정지 상태일 경우 어떤 처리를 해준다.
							log.info("정지 상태임");
						}
						//정지도 아닐 경우 해당 유저의 정보를 세션에 담아준다.
						session.setAttribute("userInfo", uservo);
						return "redirect:/";
					}else{
						//가입 정보가 없을 경우 해당 유저의 정보를 insert 해준다.
						int n = socialUserService.kakaoRegist(userinfo);
						if(n >0) {
							//insert에 성공 했을 경우 해당 유저의 정보를 받아온다.
							UserProfileVo uservo = socialUserService.socialLogin(userinfo);
							//세션에 정보 저장 후
							session.setAttribute("userInfo", uservo);
							//메인으로 이동
							return "redirect:/";
						}
					}
			} catch (IOException e) {
				e.printStackTrace();
			}
			//토큰 받아오기를 실패 했을 경우 에러페이지로 이동
			return "redirect:/";
		}
					
		/**
	 * 사용자의 Accesstoken을 이용해 사용자의 정보를 받아오는 리턴해주는 메소드
	 * @param accToken 사용자의 정보에 접근하기 위한 accessToken
	 * @return jsonObject 사용자의 정보가 담긴 JSONObject
	 */
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
		
	@RequestMapping(value="/googleRedirect.do")
	public String googleCallback(String code,String state, Model model, HttpSession session) {
		
		try {
			URL url = new URL(uvo.getGetGoogleTokenUrl());
			HttpURLConnection googleConn = (HttpURLConnection)url.openConnection();
			googleConn.setRequestMethod("POST");
			googleConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			googleConn.setDoOutput(true);
			
			String postData="grant_type=authorization_code"
	                + "&client_id=" + cvo.getGoogleClientID()
	                + "&redirect_uri=" + uvo.getGoogleRedirect()
	                + "&code=" + code
	                + "&state="+state
	                + "&client_secret=" + cvo.getGoogleSecretCode();
			 try (OutputStream os = googleConn.getOutputStream()) {
		            byte[] input = postData.getBytes("UTF-8");
		            os.write(input, 0, input.length);
		        }
			 
			   int responseCode = googleConn.getResponseCode();
		        BufferedReader br;
			      if (responseCode == 200) { // 정상 호출
			        br = new BufferedReader(new InputStreamReader(googleConn.getInputStream(),"UTF-8"));
			      } else {  // 에러 발생
			        br = new BufferedReader(new InputStreamReader(googleConn.getErrorStream(),"UTF-8"));
			      }
			      String inputLine;
			      StringBuilder res = new StringBuilder();
			      while ((inputLine = br.readLine()) != null) {
			        res.append(inputLine);
			      }
			      br.close();
			      if (responseCode == 200) {
			    		JsonObject jsonObject = JsonParser.parseString(res.toString()).getAsJsonObject();
						String accToken = jsonObject.get("access_token").getAsString();
						String refToken = jsonObject.get("id_token").getAsString();
						JsonObject googleinfo = getGoogleInfo(accToken);
						log.info("{}",googleinfo);
						if(googleinfo==null) {
							return "";
						}
						Map<String,String> userinfo = JSONToMap(googleinfo,"G",refToken);
						Boolean isc = commUserService.searchEmailService(userinfo);//이메일에 대한 정보가 있는지 확인한다.
						if(isc) {
							//이메일에 대한 정보를 받아와 만약 그 정보가 있을 경우 조회된 정보를 바탕으로 user정보를 받아온다.
							UserProfileVo uservo = socialUserService.socialLogin(userinfo);
							//해당 유저의 refresh토큰이 다를 경우
							if(!uservo.getUserRefreshToken().equals(userinfo.get("userRefreshToken"))) {
								//리프레쉬 토큰을 갱신해준다.
								socialUserService.updateRefToken(userinfo);
							}
							//사용자의 정지 여부 판단하기
							int cnt = commUserService.searchJeongJi(uservo);
							if(cnt!=0) {
								//정지 상태일 경우 어떤 처리를 해준다.
								log.info("정지 상태임");
							}
							//정지도 아닐 경우 해당 유저의 정보를 세션에 담아준다.
							session.setAttribute("userInfo", uservo);
							
							return "redirect:/";
						}else{
							//가입 정보가 없을 경우 해당 유저의 정보를 insert 해준다.
							model.addAttribute("userInfo",userinfo);
							return "registGoogle";
						}
			    	 }
			      googleConn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";//errorpage
	}
	
	public JsonObject getGoogleInfo(String accToken) {
		try {
			URL url = new URL(uvo.getGetGoogleInfo());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer "+accToken);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                
                return JsonParser.parseString(response.toString()).getAsJsonObject();
            } else {
                System.out.println("Error: Response code " + responseCode);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/GoogleRegist.do",method=RequestMethod.POST)
	public String googleRegist(@RequestParam Map<String, String> userProfile, HttpSession session) {
		log.info("{}",userProfile.toString());
		int n = socialUserService.googleRegist(userProfile);
		if(n>0) {
			UserProfileVo uservo = socialUserService.socialLogin(userProfile);
			session.setAttribute("userInfo", uservo);
			return "redirect : /";
		}else {
			return "redirect : /";//에러처리하기
		}
	}
	
		/**
		 * 
		 * @param obj 사용자의 정보가 담겨있는 JSON Object
		 * @param site 사용자의 정보를 받아온 소셜 사이트
		 * @param refToken 사용자의 refreshToken
		 * @return Map 사용자의 정보를 Map으로 변환함
		 */
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
				
				if(obj.getAsJsonObject("kakao_account").get("gender").getAsString().equals("male")) {
					userinfo.put("userGender","M" );
				}else {
					userinfo.put("userGender","F" );
				}
				userinfo.put("userBirth", (obj.getAsJsonObject("kakao_account").get("birthyear").getAsString())+"-"
						+(obj.getAsJsonObject("kakao_account").get("birthday").getAsString().substring(0,2)+"-")
						+(obj.getAsJsonObject("kakao_account").get("birthday").getAsString().substring(2,4)));
				userinfo.put("userPhoneNumber", (obj.getAsJsonObject("kakao_account").get("phone_number").getAsString()).replace("+82","0").replace("-","").replace(" ",""));
				userinfo.put("userProfileFile", obj.getAsJsonObject("properties").get("profile_image").getAsString());
			}else if(site.equals("G")) {
				userinfo.put("site","G");
				userinfo.put("userName", obj.get("name").getAsString());
				userinfo.put("userEmail", obj.get("email").getAsString());
				userinfo.put("userProfileFile", obj.get("picture").getAsString());
				userinfo.put("userRefreshToken", refToken);
			}
			return userinfo;
		}
	}