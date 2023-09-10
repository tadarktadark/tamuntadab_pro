package com.tdtd.tmtd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubjectVo {

	private String subjId;
	private String subjTitle;
	private String subjStudId;
	private String subjStatus;
	private String subjBallyeoSayu;
	private String subjRegdate;
	private String subjSujeongil;
	private String subjSujeongSayu;
	private String subjDelflag;
	private String subjCode;

}