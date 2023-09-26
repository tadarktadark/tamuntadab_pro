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
@NoArgsConstructor
@AllArgsConstructor
public class HwanbulVo {

	private String hwanId;
	private String hwanGyeoljeId;
	private String hwanStatus;
	private String hwanRegdate;
	private String hwanGeumaek;
	private String hwanReason;
}
