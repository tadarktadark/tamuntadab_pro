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
public class ChamyeoVo {

	
	private int clchId            ;
	private String clchAccountId     ;
	private int clchClasId        ;
	private String clchStatus        ;
	private String clchGyeoljeStatus ;
	private String clchYeokal        ;
	private String clchPilgiStatus   ;
	private String clchReviewStatus  ;
	private int clchInstrSugangryo;
	
	
}
