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
	private String writerId;
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
}
