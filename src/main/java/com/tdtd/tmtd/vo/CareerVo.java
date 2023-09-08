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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerVo {

	private String careId;
	private String careAccountId;
	private String careName;
	private String careContact;
	private String careSosok;
	private String carePosition;
	private String carePeriod;
	private String careJob;
	private String careIssuer;
	private String careIssuerContact;
	private String careDate;
	private String careCompany;
	private String careStatus;
	private String careStatusDate;
	private String careReason;

}
