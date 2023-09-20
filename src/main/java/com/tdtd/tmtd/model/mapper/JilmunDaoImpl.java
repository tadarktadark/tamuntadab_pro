package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ClassVo;

@Repository
public class JilmunDaoImpl implements IJilmunDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.JilmunDaoImpl.";

	@Override
	public int getJilmunCount() {
		return session.selectOne(NS+"getJilmunCount");
	}

	@Override
	public List<BoardVo> getJilmunList(Map<String, Object> map) {
		return session.selectList(NS+"getJilmunList",map);
	}

	@Override
	public BoardVo getJilmunDetail(Map<String, Object> map) {
		return session.selectOne(NS+"getJilmunDetail",map);
	}

	@Override
	public String getJilmunLikeUser(String id) {
		return session.selectOne(NS+"getJilmunLikeUser",id);
	}

	@Override
	public int updateJilmunLikeUser(Map<String, Object> map) {
		return session.update(NS+"updateJilmunLikeUser",map);
	}

	@Override
	public String getJilmunViewUser(String id) {
		return session.selectOne(NS+"getJilmunViewUser",id);
	}

	@Override
	public int updateJilmunViewUser(Map<String, Object> map) {
		return session.update(NS+"updateJilmunViewUser",map);
	}

	@Override
	public int getMyJilmunCount(String accountId) {
		return session.selectOne(NS+"getMyJilmunCount",accountId);
	}

	@Override
	public List<BoardVo> getMyJilmunList(Map<String, Object> map) {
		return session.selectList(NS+"getMyJilmunList",map);
	}

	@Override
	public int getLikeJilmunCount(String accountId) {
		return session.selectOne(NS+"getLikeJilmunCount",accountId);
	}

	@Override
	public List<BoardVo> getLikeJilmunList(Map<String, Object> map) {
		return session.selectList(NS+"getLikeJilmunList",map);
	}

	@Override
	public List<ClassVo> getJilmunClassList(String accountId) {
		return session.selectList(NS+"getJilmunClassList",accountId);
	}
	
	@Override
	public ClassVo getJilmunSubject(String clasId) {
		return session.selectOne(NS+"getJilmunSubject",clasId);
	}

	@Override
	public int insertJilmun(BoardVo vo) {
		return session.insert(NS+"insertJilmun",vo);
	}
	
	@Override
	public BoardVo getJilmunUpdateData(String id) {
		return session.selectOne(NS+"getJilmunUpdateData",id);
	}

	@Override
	public int updateJilmun(BoardVo vo) {
		return session.update(NS+"updateJilmun",vo);
	}

	@Override
	public int deleteJilmun(String id) {
		return session.update(NS+"deleteJilmun",id);
	}

}
