package com.tdtd.tmtd.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;


@Repository
public class MainDaoImpl implements IMainDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.MainDaoImpl.";

	
	@Override
	public List<SubjectTagVo> getAllSubjectTag() {
		return sqlSession.selectList(NS+"getAllSubjectTag");
	}

	
	@Override
	public List<ClassVo> getAllClass() {
		return sqlSession.selectList(NS+"getAllClass");
	}

	
	@Override
	public List<InstrVo> getIngiInstr() {
		return sqlSession.selectList(NS+"getIngiInstr");
	}

}
