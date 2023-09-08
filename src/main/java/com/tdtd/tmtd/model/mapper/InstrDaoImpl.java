package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.InstrVo;

@Repository
public class InstrDaoImpl implements IInstrDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.InstrDaoImpl.";

	@Override
	public InstrVo getMyInstrProfile(String userAccountId) {
		return sqlSession.selectOne(NS+"getMyInstrProfile", userAccountId);
	}

	@Override
	public int insertInstrProfile(InstrVo vo) {
		return sqlSession.insert(NS+"insertInstrProfile", vo);
	}

	@Override
	public int insertInstrEdulevel(InstrVo vo) {
		return sqlSession.insert(NS+"insertInstrEdulevel", vo);
	}

	@Override
	public int updateInstrProfile(InstrVo vo) {
		return sqlSession.update(NS+"updateInstrProfile", vo);
	}

	@Override
	public int updateInstrEdulevel(InstrVo vo) {
		return sqlSession.update(NS+"updateInstrEdulevel", vo);
	}

	@Override
	public List<InstrVo> getAllInstr(String order) {
		return sqlSession.selectList(NS+"getAllInstr", order);
	}

	@Override
	public int updateInstrLike(Map<String, Object> map) {
		return sqlSession.update(NS+"updateInstrLike", map);
	}

	@Override
	public int updateInstrView(Map<String, Object> map) {
		return sqlSession.update(NS+"updateInstrLike", map);
	}

	@Override
	public InstrVo getOneInstrSimple(String userAccountId) {
		return sqlSession.selectOne(NS+"getOneInstrSimple", userAccountId);
	}

	@Override
	public InstrVo getOneInstrProfile(String userAccountId) {
		return sqlSession.selectOne(NS+"getOneInstrProfile", userAccountId);
	}

	@Override
	public InstrVo getOneInstrClass(String userAccountId) {
		return sqlSession.selectOne(NS+"getOneInstrClass", userAccountId);
	}

	@Override
	public List<InstrVo> getOneIntrReview(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getOneIntrReview", map);
	}

}
