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
	public List<ReplyVo> getReplyList(Map<String, Object> map) {
		return session.selectList(NS+"getReplyList",map);
	}

	@Override
	public int updateReplyStep(int rootSeq) {
		return session.update(NS+"updateReplyStep",rootSeq);
	}

	@Override
	public int updateSakjeStep(int rootSeq) {
		return session.update(NS+"updateSakjeStep",rootSeq);
	}

	@Override
	public int insertReply(ReplyVo vo) {
		return session.insert(NS+"insertReply",vo);
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
