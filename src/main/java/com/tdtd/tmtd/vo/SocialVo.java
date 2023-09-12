package com.tdtd.tmtd.vo;

public class SocialVo {
	private String email;
	private String name;
	private String nickname;
	private String mobile;
	private String gender;
	private String birth;
	private String refreshtoken;
	
	public String getRefreshtoken() {
		return refreshtoken;
	}
	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}
	public SocialVo() {
		super();
	}
	public SocialVo(String email, String name, String nickname) {
		super();
		this.email = email;
		this.name = name;
		this.nickname = nickname;
	}
	public SocialVo(String email, String name, String nickname, String mobile, String gender, String birth,String refreshtoken) {
		super();
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.mobile = mobile;
		this.gender = gender;
		this.birth = birth;
		this.refreshtoken = refreshtoken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "SocialVo [email=" + email + ", name=" + name + ", nickname=" + nickname + ", mobile=" + mobile
				+ ", gender=" + gender + ", birth=" + birth + "]";
	}
	
	
	
}
