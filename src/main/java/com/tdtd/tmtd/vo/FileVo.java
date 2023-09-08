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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileVo {
	
	private String fileRefPk;
	private String fileOriginName;
	private String fileSaveName;
	private String fileRegdate;

}
