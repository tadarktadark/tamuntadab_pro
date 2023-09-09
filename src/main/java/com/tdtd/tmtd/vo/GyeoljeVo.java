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
public class GyeoljeVo {
	private String gyeoId;
	private int gyeoGeumaek;
	private String gyeoDaesangId;
	private String gyeoAccountId;
	private String gyeoRegdate;
	private String gyeoWanryoil;
	private String gyeoBangbeop;
	private String gyeoStatus;
	public GyeoljeVo(int gyeoGeumaek) {
		this.gyeoGeumaek = gyeoGeumaek;
	}
	
	
}
