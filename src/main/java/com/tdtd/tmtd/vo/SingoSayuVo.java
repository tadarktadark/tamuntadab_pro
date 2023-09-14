package com.tdtd.tmtd.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SingoSayuVo {
	private String id;
	private String category;
	
	private int seq;
	private String sayuAccountId;
	private String content;
	private String regdate;
}
