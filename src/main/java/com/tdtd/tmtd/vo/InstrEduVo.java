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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InstrEduVo {
	
	private int inedSeq;
	private int inedInprSeq;
	private int inedStage;
	private String inedSchool;
	private String inedMajor;
	private String inedMinor;
	private String inedStart;
	private String inedEnd;
	private String inedRegdate;

}
