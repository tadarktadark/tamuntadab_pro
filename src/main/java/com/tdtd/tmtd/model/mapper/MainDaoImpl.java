package com.tdtd.tmtd.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.SubjectTagVo;

/**
 * 메인 페이지 관련 DAO
 * @author 문희애
 *
 */
@Repository
public class MainDaoImpl implements IMainDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.MainDaoImpl.";

	/**
	 * 과목 전체 리스트 조회
	 */
	@Override
	public List<SubjectTagVo> getAllSubjectTag() {
		return sqlSession.selectList(NS+"getAllSubjectTag");
	}

	/**
	 * 모집중인 클래스 전체 조회
	 */
	@Override
	public List<ClassVo> getAllClass() {
		return sqlSession.selectList(NS+"getAllClass");
	}

	/**
	 * 인기강사 전체 조회
	 */
	@Override
	public List<InstrVo> getIngiInstr() {
		return sqlSession.selectList(NS+"getIngiInstr");
	}

}
