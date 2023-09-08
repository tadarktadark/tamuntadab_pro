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
public class TupyoOptionVo {
	
	private int tuopSeq;
	private int tuopTupySeq;
	private String tuopInstr;
	private int tuopFee;

}
