package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.TupyoOptionVo;
import com.tdtd.tmtd.vo.TupyoUserVo;
import com.tdtd.tmtd.vo.TupyoVo;

@Repository
public class TupyoDaoImpl implements ITupyoDao {
	
	private final String NS = "com.tdtd.tmtd.model.mapper.TupyoDaoImpl.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertTupyo(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertTupyo",map);
	}

	@Override
	public TupyoVo getTupyo(int tupyClasId) {
		return sqlSession.selectOne(NS+"getTupyo",tupyClasId);
	}
	
	@Override
	public List<TupyoOptionVo> getAllTupyoOption(int tuopTupySeq) {
		return sqlSession.selectList(NS+"getAllTupyoOption",tuopTupySeq);
	}

	@Override
	public int insertTupyoOption(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertTupyoOption",map);
	}
	
	
	@Override
	public ChamyeoVo getClassMaster(int clchClasId) {
		return sqlSession.selectOne(NS+"getClassMaster",clchClasId);
	}

	@Override
	public List<ChamyeoVo> getClassMember(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getClassMember",map);
	}

	@Override
	public int countTotalClassMember(int clchClasId) {
		return sqlSession.selectOne(NS+"countTotalClassMember",clchClasId);
	}
	
	@Override
	public List<TupyoUserVo> tupyoUserChk(Map<String, Object> map) {
		return sqlSession.selectList(NS+"tupyoUserChk",map);
	}

	@Override
	public int insertTupyoUser(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertTupyoUser",map);
	}

	@Override
	public List<TupyoUserVo> getTupyoResult(int tuopTupySeq) {
		return sqlSession.selectList(NS+"getTupyoResult",tuopTupySeq);
	}

	@Override
	public int delTupyoUser(TupyoUserVo vo) {
		return sqlSession.delete(NS+"delTupyoUser",vo);
	}

	@Override
	public int endTupyo(int tuusSeq) {
		return sqlSession.update(NS+"endTupyo",tuusSeq);
	}

	@Override
	public int updateTupyo(int tuusSeq) {
		return sqlSession.update(NS+"updateTupyo",tuusSeq);
	}

	@Override
	public List<TupyoUserVo> getAgreeUser(Map<String, Object> map) {
		return sqlSession.selectList(NS+"getAgreeUser",map);
	}

	@Override
	public int updateAgreeTupyo(Map<String, Object> map) {
		return sqlSession.update(NS+"updateAgreeTupyo",map);
	}

	@Override
	public List<ChamyeoVo> getAllInstr(int clchClasId) {
		return sqlSession.selectList(NS+"getAllInstr",clchClasId);
	}

	@Override
	public List<ChamyeoVo> getAllClassMember(int clchClasId) {
		return sqlSession.selectList(NS+"getAllClassMember",clchClasId);
	}

	@Override
	public List<TupyoUserVo> getAllVotedStudents(int tuopTupySeq) {
		return sqlSession.selectList(NS+"getAllVotedStudents",tuopTupySeq);
	}

	@Override
	public TupyoOptionVo getTupyoOption(int tuopSeq) {
		return sqlSession.selectOne(NS+"getTupyoOption",tuopSeq);
	}

	@Override
	public int updateClasAccountId(Map<String, Object> map) {
		return sqlSession.update(NS+"updateClasAccountId",map);
	}

	@Override
	public int delTupyo(int tupySeq) {
		return sqlSession.delete(NS+"delTupyo",tupySeq);
	}

	@Override
	public int delTupyoOption(int tupySeq) {
		return sqlSession.delete(NS+"delTupyoOption",tupySeq);
	}

	@Override
	public int delAllTupyoUser(int tupySeq) {
		return sqlSession.delete(NS+"delTupyoUser",tupySeq);
	}
	
	
	

	



}
