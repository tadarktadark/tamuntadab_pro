package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

@Repository
public class PilgiDaoImpl implements IPilgiDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.PilgiDaoImpl.";
	
	@Override
	public int getPilgiCount(Map<String, Object> map) {
		return session.selectOne(NS+"getPilgiCount",map);
	}

	@Override
	public List<BoardVo> getPilgiList(Map<String, Object> map) {
		return session.selectList(NS+"getPilgiList",map);
	}

	@Override
	public BoardVo getPilgiDetail(Map<String, Object> map) {
		return session.selectOne(NS+"getPilgiDetail",map);
	}

	@Override
	public List<BoardVo> getYeongwanList(Map<String, Object> map) {
		return session.selectList(NS+"getYeongwanList",map);
	}

	@Override
	public String getPilgiLikeUser(String id) {
		return session.selectOne(NS+"getPilgiLikeUser",id);
	}

	@Override
	public int updatePilgiLikeUser(Map<String, Object> map) {
		return session.update(NS+"updatePilgiLikeUser",map);
	}

	@Override
	public String getPilgiViewUser(String id) {
		return session.selectOne(NS+"getPilgiViewUser",id);
	}

	@Override
	public int updatePilgiViewUser(Map<String, Object> map) {
		return session.update(NS+"updatePilgiViewUser",map);
	}

	@Override
	public ClassVo getPilgiClassDetail(String clasId) {
		return session.selectOne(NS+"getPilgiClassDetail",clasId);
	}

	@Override
	public int insertPilgi(BoardVo vo) {
		return session.insert(NS+"insertPilgi",vo);
	}

	@Override
	public int updateClchPilgiState(Map<String, Object> map) {
		return session.update(NS+"updateClchPilgiState",map);
	}

	@Override
	public BoardVo getPilgiUpdateData(String id) {
		return session.selectOne(NS+"getPilgiUpdateData", id);
	}
	
	@Override
	public int updatePilgi(BoardVo vo) {
		return session.update(NS+"updatePilgi",vo);
	}
	
	@Override
	public int deletePilgiFile(String save) {
		return session.delete(NS+"deletePilgiFile",save);
	}

	@Override
	public int updatePilgiState(Map<String, Object> map) {
		return session.update(NS+"updatePilgiState",map);
	}

	@Override
	public int deletePilgi(String id) {
		return session.delete(NS+"deletePilgi",id);
	}

}
