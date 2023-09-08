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
public class TupyoUserVo {

	private int tuusSeq;
	private int tuusOptionSeq;
	private String tuusAccountId;
	private String tuusAgree;
}
