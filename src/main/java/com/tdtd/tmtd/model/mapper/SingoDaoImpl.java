package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.SingoDaesangVo;
import com.tdtd.tmtd.vo.SingoSayuVo;

@Repository
public class SingoDaoImpl implements ISingoDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.SingoDaoImpl.";
	
	@Override
	public List<SingoSayuVo> getSingoCategory() {
		return session.selectList(NS+"getSingoCategory");
	}

	@Override
	public SingoDaesangVo getSingoDaesangId(String daesangId) {
		return session.selectOne(NS+"getSingoDaesangId",daesangId);
	}

	@Override
	public int insertSingoDaesang(SingoDaesangVo vo) {
		return session.insert(NS+"insertSingoDaesang",vo);
	}

	@Override
	public int updateSingo(SingoDaesangVo vo) {
		return session.update(NS+"updateSingo",vo);
	}

	@Override
	public int updateBoardState(Map<String, Object> map) {
		return session.update(NS+"updateBoardState",map);
	}

	@Override
	public int insertSingoSayu(SingoSayuVo vo) {
		return session.insert(NS+"insertSingoSayu",vo);
	}
	
	@Override
	public int getMaxCount() {
		return session.selectOne(NS+"getMaxCount");
	}

	@Override
	public List<SingoDaesangVo> getMaxSingo(Map<String, Object> map) {
		return session.selectList(NS+"getMaxSingo", map);
	}

	@Override
	public int updateSingoDaesangState(SingoDaesangVo vo) {
		return session.update(NS+"updateSingoDaesangState",vo);
	}
	
	@Override
	public String getSingoWriter(String id) {
		return session.selectOne(NS+"getSingoWriter",id);
	}

	@Override
	public SingoDaesangVo getSingoUser(String accountId) {
		return session.selectOne(NS+"getSingoUser",accountId);
	}

	@Override
	public int insertSingoUser(String accountId) {
		return session.insert(NS+"insertSingoUser",accountId);
	}

	@Override
	public int updateSingoUserCount(String id) {
		return session.update(NS+"updateSingoUserCount",id);
	}

}
