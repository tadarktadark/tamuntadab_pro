package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.FileVo;

/**
 * FILE 테이블 관련 dao
 * @author 문희애
 *
 */
@Repository
public class FileDaoImpl implements IFileDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.FileDaoImpl."; 

	/**
	 * 파일 업로드시 DB 저장
	 */
	@Override
	public int insertFile(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertFile", map);
	}

	/**
	 * 파일 정보 조회
	 */
	@Override
	public FileVo getFile(String fileRekPk) {
		return sqlSession.selectOne(NS+"getFile", fileRekPk);
	}

}
