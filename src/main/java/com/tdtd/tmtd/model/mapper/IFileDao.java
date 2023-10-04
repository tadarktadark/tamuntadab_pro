package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.FileVo;

/**
 * FILE 테이블 관련 dao
 * @author 문희애
 *
 */
public interface IFileDao {
	
	/**
	 * 파일 업로드시 DB 저장
	 */
	public int insertFile(Map<String, Object> map);
	
	/**
	 * 파일 정보 조회
	 */
	public FileVo getFile(String fileRekPk);

}
