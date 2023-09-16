package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.BoardVo;

@Repository
public class JayuDaoImpl implements IJayuDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.JayuDaoImpl.";

	@Override
	public int getJayuCount() {
		return session.selectOne(NS+"getJayuCount");
	}

	@Override
	public List<BoardVo> getJayuList(Map<String, Object> map) {
		return session.selectList(NS+"getJayuList",map);
	}

	@Override
	public BoardVo getJayuDetail(Map<String, Object> map) {
		return session.selectOne(NS+"getJayuDetail",map);
	}

	@Override
	public String getJayuLikeUser(String id) {
		return session.selectOne(NS+"getJayuLikeUser",id);
	}

	@Override
	public int updateJayuLikeUser(Map<String, Object> map) {
		return session.update(NS+"updateJayuLikeUser",map);
	}

	@Override
	public String getJayuViewUser(String id) {
		return session.selectOne(NS+"getJayuViewUser",id);
	}

	@Override
	public int updateJayuViewUser(Map<String, Object> map) {
		return session.update(NS+"updateJayuViewUser",map);
	}

	@Override
	public int getMyJayuCount(String accountId) {
		return session.selectOne(NS+"getMyJayuCount",accountId);
	}

	@Override
	public List<BoardVo> getMyJayuList(Map<String, Object> map) {
		return session.selectList(NS+"getMyJayuList",map);
	}

	@Override
	public int getLikeJayuCount(String accountId) {
		return session.selectOne(NS+"getLikeJayuCount",accountId);
	}

	@Override
	public List<BoardVo> getLikeJayuList(Map<String, Object> map) {
		return session.selectList(NS+"getLikeJayuList",map);
	}

	@Override
	public int insertJayu(BoardVo vo) {
		return session.insert(NS+"insertJayu",vo);
	}

	@Override
	public int updateJayu(BoardVo vo) {
		return session.update(NS+"updateJayu",vo);
	}

	@Override
	public int deleteJayu(String id) {
		return session.delete(NS+"deleteJayu",id);
	}
	
	
}
