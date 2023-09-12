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
public class ReplyVo {
	private int seq;
	private String boardId;
	private String writerId;
	private String content;
	private int rootSeq;
	private int step;
	private int depth;
	private String chaetaek;
	private String regdate;
	private String update;
}
