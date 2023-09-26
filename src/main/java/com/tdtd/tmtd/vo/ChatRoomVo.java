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
public class ChatRoomVo {

	private String chroId;
	private int chroClasId;
	private String chroTitle;
	private String chroChatLog;
	private String chroDate;
	private int chroChatCount;
}
