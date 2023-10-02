package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IReplyDao;
import com.tdtd.tmtd.vo.BoardVo;
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
	public List<ReplyVo> getRootReplyList(Map<String, Object> map) {
		return dao.getRootReplyList(map);
	}
	
	@Override
	public List<ReplyVo> getReReplyList(Map<String, Object> map) {
		return dao.getReReplyList(map);
	}

	@Override
	public int insertRootReply(ReplyVo vo, Map<String, Object> map) {
		int count = dao.getRootReplyCount(map);
		int n = dao.insertRootReply(vo);
		map.put("count", count+n);
		int m = dao.updateBoardReplyCount(map);
		return (n+m>0)?1:0;
	}
	
	@Override
	public int insertReReply(ReplyVo vo) {
		return dao.insertReReply(vo);
	}
	
	@Override
	public String getUpdateContent(String seq) {
		return dao.getUpdateContent(seq);
	}

	@Override
	public int updateReply(ReplyVo vo) {
		return dao.updateReply(vo);
	}

	@Override
	public int deleteReply(Map<String, Object> map) {
		ReplyVo vo = dao.getReplyDetail((int)map.get("seq"));
		map.put("count", dao.getRootReplyCount(map)-1);
		int n = dao.deleteReply((int)map.get("seq"));
		n += dao.insertSakje(vo);
		n += dao.updateBoardReplyCount(map);
		return (n>1)?1:0;
	}

	@Override
	public int updateChaetaek(int seq, String boardId) {
		int n = dao.updateChaetaekY(seq);
		n += dao.updateChaetaekBoard(boardId);
		return (n>1)?1:0;
	}

	@Override
	public int getWritePilgiCount(String accountId) {
		return dao.getWritePilgiCount(accountId);
	}

	@Override
	public int getWriteJilmunCount(String accountId) {
		return dao.getWriteJilmunCount(accountId);
	}

	@Override
	public int getWriteJayuCount(String accountId) {
		return dao.getWriteJayuCount(accountId);
	}

	@Override
	public int getWriteReplyCount(String accountId) {
		return dao.getWriteReplyCount(accountId);
	}

	@Override
	public int getLikeCommCount(String accountId) {
		return dao.getLikeCommCount(accountId);
	}

	@Override
	public List<BoardVo> getWritePilgiList(Map<String, Object> map) {
		return dao.getWritePilgiList(map);
	}

	@Override
	public List<BoardVo> getWriteJilmunList(Map<String, Object> map) {
		return dao.getWriteJilmunList(map);
	}

	@Override
	public List<BoardVo> getWriteJayuList(Map<String, Object> map) {
		return dao.getWriteJayuList(map);
	}

	@Override
	public List<ReplyVo> getWriteReplyList(Map<String, Object> map) {
		return dao.getWriteReplyList(map);
	}

	@Override
	public List<BoardVo> getLikeCommList(Map<String, Object> map) {
		return dao.getLikeCommList(map);
	}

	@Override
	public BoardVo getBoardAlramInfo(String id) {
		return dao.getBoardAlramInfo(id);
	}
	
	@Override
	public ReplyVo getReplyAlramInfo(String seq) {
		return dao.getReplyAlramInfo(seq);
	}
}
