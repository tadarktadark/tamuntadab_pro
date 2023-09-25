package com.tdtd.tmtd.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileVo {

	private String userAccountId;
	private String userEmail;
	private String userPassword;
	private String userName;
	private String userNickname;
	private String userAuth;
	private String userPhoneNumber;
	private String userAutoLoginToken;
	private String userRefreshToken;
	private String userJoinDate;
	private String userLastAccessDate;
	private String userGender;
	private String userSite;
	private String userBirth;
	private String userProfileFile;
	private String userDelflag;
	private String userChadanCount;
	private String userChadanRegistDate;
	private String userJeongJiSangTae;
}


