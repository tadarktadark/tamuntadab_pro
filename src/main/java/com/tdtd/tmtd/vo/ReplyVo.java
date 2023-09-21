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
	private String state;
	
	public ReplyVo(String boardId, String writerId, String content, int rootSeq) {
		super();
		this.boardId = boardId;
		this.writerId = writerId;
		this.content = content;
		this.rootSeq = rootSeq;
	}

	public ReplyVo(int seq, String content) {
		super();
		this.seq = seq;
		this.content = content;
	}

	public ReplyVo(int seq, String boardId, int rootSeq, int step, int depth) {
		super();
		this.seq = seq;
		this.boardId = boardId;
		this.rootSeq = rootSeq;
		this.step = step;
		this.depth = depth;
	}
}
