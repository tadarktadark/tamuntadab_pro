package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.FileVo;

public interface IFileDao {
	
	public int insertFile(Map<String, Object> map);
	
	public FileVo getFile(String fileRekPk);

}
