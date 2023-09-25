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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GeoljeVo {

	private String gyeoId;
	private int gyeoGeumaek;
	private String gyeoDaesangId;
	private String gyeoAccountId;
	private String gyeoRegdate;
	private String gyeoWanryoil;
	private String gyeoBangbeop;
	private String gyeoStatus;
	
	private List<ClassVo> clasVo;
	private List<GangeuisilVo> gasiVo;
}
