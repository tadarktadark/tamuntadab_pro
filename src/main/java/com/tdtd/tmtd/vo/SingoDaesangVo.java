package com.tdtd.tmtd.vo;

import java.util.List;

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
public class SingoDaesangVo {
	private String id;
	private String daesangId;
	private String daesangContent;
	private String accountId;
	
	private int count;
	private String state;
	
	private List<SingoSayuVo> sayuList;
}
