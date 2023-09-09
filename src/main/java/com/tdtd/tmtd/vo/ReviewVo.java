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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewVo {

	private int reviSeq;
	private int reviClasId;
	private String reviStudName;
	private int reviPro;
	private int reviPrepare;
	private int reviAbil;
	private int reviSigan;
	private String reviDetail;
	private String reviRegdate;
	private int avgScore;
	
	private List<ClassVo> classVo;

}
