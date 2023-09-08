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
public class GangeuisiilVo {
	private String gaco_id;
	private String gaco_name;
	private String gaco_sido;
	private String gaco_sigungu;
	private String gaco_juso;
	private long gaco_lat;
	private long gaco_lon;
	private String gaco_open;
	private String gaco_close;
	private String gaga_id;
	private String gaga_name;
	private int gaga_max;
	private int gaga_hour_price;
}
