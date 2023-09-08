package com.tdtd.tmtd.vo;

import java.util.Date;

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
	private Date tupyStartdate;
	private Date tupyEnddate;
	private String tupyStatus;

}
