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
public class BoardVo {
	private String id;
	private String accountId;
	private String title;
	private int clasId;
	private String subjectCode;
	private String content;
	private int replyCount;
	private String likeUser;
	private int likeCount;
	private String viewUser;
	private int viewCount;
	private String viewGroup;
	private String downloadGroup;
	private String chaetaek;
	private String regdate;
	private String update;
	private String state;
	
	public BoardVo(String accountId, int clasId, String content, String viewGroup, String downloadGroup) {
		this.accountId = accountId;
		this.clasId = clasId;
		this.content = content;
		this.viewGroup = viewGroup;
		this.downloadGroup = downloadGroup;
	}

	public BoardVo(String id, String content, String viewGroup, String downloadGroup) {
		super();
		this.id = id;
		this.content = content;
		this.viewGroup = viewGroup;
		this.downloadGroup = downloadGroup;
	}

	public BoardVo(String accountId, String title, int clasId, String subjectCode, String content) {
		super();
		this.accountId = accountId;
		this.title = title;
		this.clasId = clasId;
		this.subjectCode = subjectCode;
		this.content = content;
	}

	public BoardVo(String id, int clasId, String subjectCode, String content) {
		super();
		this.id = id;
		this.clasId = clasId;
		this.subjectCode = subjectCode;
		this.content = content;
	}

	public BoardVo(String accountId, String title, String content) {
		super();
		this.accountId = accountId;
		this.title = title;
		this.content = content;
	}

	public BoardVo(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	
}
