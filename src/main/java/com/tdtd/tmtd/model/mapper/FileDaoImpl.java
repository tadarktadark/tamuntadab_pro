package com.tdtd.tmtd.model.mapper;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.FileVo;

@Repository
public class FileDaoImpl implements IFileDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.FileDaoImpl."; 

	@Override
	public int insertFile(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertFile", map);
	}

	@Override
	public FileVo getFile(String fileRekPk) {
		return sqlSession.selectOne(NS+"getFile", fileRekPk);
	}

}
