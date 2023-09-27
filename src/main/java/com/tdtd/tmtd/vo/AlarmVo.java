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
public class AlarmVo {
	
	private String alarId       ;
	private String alarAccountId;
	private String alarContent  ;
	private String alarEventdate;
	private String alarReplySeq ;
	private String alarChecked  ;

}
