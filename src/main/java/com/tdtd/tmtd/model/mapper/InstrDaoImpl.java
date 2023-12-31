package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.InstrEduVo;
import com.tdtd.tmtd.vo.InstrVo;
import com.tdtd.tmtd.vo.ReviewVo;


@Repository
public class InstrDaoImpl implements IInstrDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.InstrDaoImpl.";

	
	@Override
	public boolean hasInprProfile(Map<String, Object> userAccountId) {
		return sqlSession.selectOne(NS+"hasInprProfile", userAccountId) != null;
	}
	
	
	@Override
	public int updateInprRegdate(Map<String, Object> userAccountId) {
		return sqlSession.update(NS+"updateInprRegdate",userAccountId);
	}
	
	
	@Override
	public InstrVo getMyInstrProfile(String userAccountId) {
		return sqlSession.selectOne(NS+"getMyInstrProfile", userAccountId);
	}

	
	@Override
	public int insertInstrProfile(InstrVo vo) {
		return sqlSession.insert(NS+"insertInstrProfile", vo);
	}

	
	@Override
	public int insertInstrEdulevel(InstrEduVo vo) {
		return sqlSession.insert(NS+"insertInstrEdulevel", vo);
	}

	
	@Override
	public int updateInstrProfile(InstrVo vo) {
		return sqlSession.update(NS+"updateInstrProfile", vo);
	}

	
	@Override
	public int updateInstrEdulevel(InstrEduVo vo) {
		return sqlSession.update(NS+"updateInstrEdulevel", vo);
	}
	
	
	@Override
	public int deleteInstrEdulevel(String inedSeq) {
		return sqlSession.delete(NS+"deleteInstrEdulevel", inedSeq);
	}

	
	@Override
	public List<InstrVo> getAllInstr(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getAllInstr", map);
	}
	
	
	@Override
	public int getAllInstrCount() {
		return sqlSession.selectOne(NS+"getAllInstrCount");
	}

	
	@Override
	public int updateInstrLike(Map<String, Object> map) {
		return sqlSession.update(NS+"updateInstrLike", map);
	}

	
	@Override
	public int updateInstrView(Map<String, Object> map) {
		return sqlSession.update(NS+"updateInstrView", map);
	}
	
	
	@Override
	public InstrVo getlikeViewUser(String inprAccountId) {
		return sqlSession.selectOne(NS+"getlikeViewUser", inprAccountId);
	}

	
	@Override
	public InstrVo getOneInstrSimple(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"getOneInstrSimple", map);
	}

	
	@Override
	public InstrVo getOneInstrProfile(String userAccountId) {
		return sqlSession.selectOne(NS+"getOneInstrProfile", userAccountId);
	}

	
	@Override
	public List<InstrVo> getOneInstrClass(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getOneInstrClass", map);
	}
	
	
	@Override
	public int getCountClassCancel(String userAccountId) {
		return sqlSession.selectOne(NS+"getCountClassCancel", userAccountId);
	}

	
	@Override
	public List<ReviewVo> getOneIntrReview(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getOneIntrReview", map);
	}
	
	
	@Override
	public int classTotalCount(String userAccountId) {
		return sqlSession.selectOne(NS+"classTotalCount", userAccountId);
	}
	
	
	@Override
	public int reviewTotalCount(String userAccountId) {
		return sqlSession.selectOne(NS+"reviewTotalCount", userAccountId);
	}

}
