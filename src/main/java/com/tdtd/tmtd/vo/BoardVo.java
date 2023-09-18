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
public class BoardVo {
	private String id;
	private String accountId;
	private String title;
	private String clasId;
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
	
	private List<FileVo> fileList;
	
	public BoardVo(String accountId, String clasId, String content, String viewGroup, String downloadGroup) {
		this.accountId = accountId;
		this.clasId = clasId;
		this.content = content;
		this.viewGroup = viewGroup;
		this.downloadGroup = downloadGroup;
	}

	public BoardVo(String id, String clasId, String subjectCode, String content) {
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
