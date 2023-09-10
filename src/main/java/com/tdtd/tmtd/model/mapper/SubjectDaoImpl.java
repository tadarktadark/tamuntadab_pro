package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.SubjectVo;

@Repository
public class SubjectDaoImpl implements ISubjectDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS = "com.tdtd.tmtd.model.mapper.SubjectDaoImpl.";

	
	/**
	 * @return 페이징 처리된 과목 리스트 select
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public List<SubjectVo> getSubjectList() {
		return sqlSession.selectList(NS+"getSubjectList");
	}

	/**
	 * @return 페이징 처리할 전체 과목 갯수
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int getSubjectTagListCount() {
		return sqlSession.selectOne(NS+"getSubjectTagListCount");
	}

	/**
	 * @return 업데이트에 성공한 과목 갯수 int
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int updateSubject(SubjectVo vo) {
		return sqlSession.selectOne(NS+"updateSubject", vo);
	}

	/**
	 * @return 업데이트에 성공한 클래스 갯수 int
	 * @author 김기훈
	 * @since 2023-09-10
	 */
	@Override
	public int updateClass(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"updateClass", map);
	}

}
