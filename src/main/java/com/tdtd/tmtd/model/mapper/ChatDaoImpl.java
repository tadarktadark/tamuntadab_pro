package com.tdtd.tmtd.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;

@Repository
public class ChatDaoImpl implements IChatDao {
	
	private final String NS = "com.tdtd.tmtd.model.mapper.ChatDaoImpl.";

	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public List<ChatRoomVo> getChatRoomList(String accountId) {
		return sqlSession.selectList(NS+"getChatRoomList",accountId);
	}

	@Override
	public ChatRoomVo getChatDetail(String chroId) {
		return sqlSession.selectOne(NS+"getChatDetail");
	}

	@Override
	public List<ChatUserVo> getChatUserList(String chusChroId) {
		return sqlSession.selectList(NS+"getChatUserList",chusChroId);
	}

	@Override
	public int insertChatRoom(ChatRoomVo vo) {
		return sqlSession.insert(NS+"insertChatRoom",vo);
	}

	@Override
	public int insertChatUser(ChatUserVo vo) {
		return sqlSession.insert(NS+"insertChatUser",vo);
	}

	@Override
	public int updateChatLog(ChatRoomVo vo) {
		return sqlSession.update(NS+"updateChatLog",vo);
	}

	@Override
	public int delChatRoom(String chroId) {
		return sqlSession.delete(NS+"delChatRoom",chroId);
	}

	@Override
	public int delAllChatUser(String chusChroId) {
		return sqlSession.delete(NS+"delAllChatUser",chusChroId);
	}

	@Override
	public int delChatUser(int chusId) {
		return sqlSession.delete(NS+"delChatUser",chusId);
	}



}
