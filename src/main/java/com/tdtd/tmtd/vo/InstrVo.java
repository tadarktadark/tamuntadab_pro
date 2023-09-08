package com.tdtd.tmtd.vo;

import java.util.List;

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
public class InstrVo {

	private int inprSeq;
	private String inprAccountId;
	private String inprIntro;
	private int inprFee;
	private String inprSiteYoutube;
	private String inprSiteWeb;
	private String inprSiteMobile;
	private String inprSubjects;
	private String inprSubjectsMajor;
	private String inprRegdate;
	private String inprView;
	private String inprLike;
	private int inprViewCount;
	private int inprLikeCount;
	private String inprCert;

	private int inedSeq;
	private int inedInprSeq;
	private int inedStage;
	private String inedSchool;
	private String inedMajor;
	private String inedMinor;
	private String inedStart;
	private String inedEnd;
	private String inedRegdate;
	
	private List<UserProfileVo> userProfileVo;
	private List<ClassVo> classVo;
	private List<ReviewVo> reviewVo;
	private List<SubjectTagVo> subjectTagVo;
}
