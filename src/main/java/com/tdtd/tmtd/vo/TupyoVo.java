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
public class TupyoVo {
	
	private int tupySeq;
	private int tupyClasId;
	private int tupyTotalUser;
	private String tupyStartdate;
	private String tupyEnddate;
	private String tupyStatus;

}
