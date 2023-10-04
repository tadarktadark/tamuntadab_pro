package com.tdtd.tmtd.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Format;

public class URLVo {
	
	//oAuth접근 URL
	private String naverUrl="https://nid.naver.com/oauth2.0/authorize?response_type=code";
	private String kakaoUrl="https://kauth.kakao.com/oauth/authorize?response_type=code";
	private String googleUrl="https://accounts.google.com/o/oauth2/auth";
	
	//리다이렉트 URL
	private String naverRedirect = "http://158.180.71.118:8089/tamuntadab_pro/naverRedirect.do";
	private String kakaoRedirect = "http://158.180.71.118:8089/tamuntadab_pro/kakaoRedirect.do";
	private String googleRedirect = "http://localhost:8090/tamuntadab_pro/googleRedirect.do";

	//토큰 요청 URL
	private String getNaverTokenUrl="https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	private String getKakaoTokenUrl="https://kauth.kakao.com/oauth/token";
	private String getGoogleTokenUrl="https://oauth2.googleapis.com/token";
	
	//토큰을 이용한 정보를 가져오는 URL
	private String getNaverInfo = "https://openapi.naver.com/v1/nid/me";
	private String getKakaorInfo = "https://kapi.kakao.com/v2/user/me";
	private String getGoogleInfo = "https://www.googleapis.com/oauth2/v2/userinfo";
	
	public String getNaverUrl() {
		return naverUrl;
	}
	public String getKakaoUrl() {
		return kakaoUrl;
	}
	public String getGoogleUrl() {
		return googleUrl;
	}
	public String getGetNaverTokenUrl() {
		return getNaverTokenUrl/*
								 * +"&client_id="+vo.getNaverClientID()+
								 * "&client_secret="+vo.getNaverSecretCode()+
								 * "&redirect_uri"+naverRedirect+"&code="+code+"&state"+state
								 */;
	}
	public String getGetKakaoTokenUrl() {
		return getKakaoTokenUrl;
	}
	public String getGetGoogleTokenUrl() {
		return getGoogleTokenUrl;
	}
	public String getGetNaverInfo() {
		return getNaverInfo;
	}
	public String getGetKakaorInfo() {
		return getKakaorInfo;
	}
	public String getGetGoogleInfo() {
		return getGoogleInfo;
	}
	public String getNaverRedirect() throws UnsupportedEncodingException {
		return URLEncoder.encode(naverRedirect,"UTF-8");
	}
	public String getKakaoRedirect() throws UnsupportedEncodingException {
		return URLEncoder.encode(kakaoRedirect,"UTF-8");
	}
	public String getGoogleRedirect() throws UnsupportedEncodingException {
		return URLEncoder.encode(googleRedirect,"UTF-8");
	}
}
