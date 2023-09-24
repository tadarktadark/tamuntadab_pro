package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ReplyVo;

@Repository
public class ReplyDaoImpl implements IReplyDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.tdtd.tmtd.model.mapper.ReplyDaoImpl.";
	
	@Override
	public int getReplyCount(String boardId) {
		return session.selectOne(NS+"getReplyCount",boardId);
	}

	@Override
	public List<ReplyVo> getRootReplyList(Map<String, Object> map) {
		return session.selectList(NS+"getRootReplyList",map);
	}
	
	@Override
	public List<ReplyVo> getReReplyList(Map<String, Object> map) {
		return session.selectList(NS+"getReReplyList",map);
	}

	@Override
	public int insertRootReply(ReplyVo vo) {
		return session.insert(NS+"insertRootReply",vo);
	}
	
	@Override
	public int insertReReply(ReplyVo vo) {
		return session.insert(NS+"insertReReply",vo);
	}
	
	@Override
	public int getRootReplyCount(Map<String, Object> map) {
		return session.selectOne(NS+"getRootReplyCount",map);
	}
	
	@Override
	public int updateBoardReplyCount(Map<String, Object> map) {
		return session.update(NS+"updateBoardReplyCount",map);
	}
	
	@Override
	public String getUpdateContent(String seq) {
		return session.selectOne(NS+"getUpdateContent",seq);
	}

	@Override
	public int updateReply(ReplyVo vo) {
		return session.update(NS+"updateReply",vo);
	}

	@Override
	public int deleteReply(int seq) {
		return session.delete(NS+"deleteReply",seq);
	}

	@Override
	public ReplyVo getReplyDetail(int seq) {
		return session.selectOne(NS+"getReplyDetail",seq);
	}

	@Override
	public int insertSakje(ReplyVo vo) {
		return session.insert(NS+"insertSakje",vo);
	}

	@Override
	public int updateChaetaekY(int seq) {
		return session.update(NS+"updateChaetaekY",seq);
	}

	@Override
	public int updateChaetaekBoard(String boardId) {
		return session.update(NS+"updateChaetaekBoard",boardId);
	}

	
}
