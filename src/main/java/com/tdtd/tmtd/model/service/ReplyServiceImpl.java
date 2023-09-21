package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IReplyDao;
import com.tdtd.tmtd.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements IReplyService {

	@Autowired
	IReplyDao dao;
	
	@Override
	public int getReplyCount(String boardId) {
		return dao.getReplyCount(boardId);
	}

	@Override
	public List<ReplyVo> getReplyList(Map<String, Object> map) {
		return dao.getReplyList(map);
	}

	@Override
	public int insertReply(ReplyVo vo) {
		int n = dao.updateReplyStep(vo.getRootSeq());
		n += dao.updateSakjeStep(vo.getRootSeq());
		n += dao.insertReply(vo);
		return (n>0)?1:0;
	}

	@Override
	public int updateReply(ReplyVo vo) {
		return dao.updateReply(vo);
	}

	@Override
	public int deleteReply(int seq) {
		int n = dao.deleteReply(seq);
		ReplyVo vo = dao.getReplyDetail(seq);
		n += dao.insertSakje(vo);
		return (n>1)?1:0;
	}

	@Override
	public int updateChaetaek(int seq, String boardId) {
		int n = dao.updateChaetaekY(seq);
		n += dao.updateChaetaekBoard(boardId);
		return (n>1)?1:0;
	}

}
