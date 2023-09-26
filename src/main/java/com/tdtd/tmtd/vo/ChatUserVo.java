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
public class ChatUserVo {
	
	private int chusSeq      ;
	private String chusChroId   ;
	private String chusAccountId;
	private String chusType     ;
	private int chusCount;

}
