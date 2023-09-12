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
public class SingoVo {
	private String sicaId;
	private String sicaExplanation;
	private String sicaDelflag;
	private String sidaId;
	private String sidaDaesangId;
	private int sidaCount;
	private String sidaState;
	private int sisaSeq;
	private String sisaAccountId;
	private String sisaContent;
	private String sisaRegdate;
	private int siusSeq;
	private String siusAccountId;
	private int siusCount;
	private String siusState;
}
