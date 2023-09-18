package com.tdtd.tmtd.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IChatDao;
import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;

@Service
public class ChatServiceImpl implements IChatService {
	
	@Autowired
	private IChatDao dao;
	

	@Override
	public List<ChatRoomVo> getChatRoomList(String accountId) {
		return dao.getChatRoomList(accountId);
	}

	@Override
	public ChatRoomVo getChatDetail(String chroId) {
		return dao.getChatDetail(chroId);
	}

	@Override
	public List<ChatUserVo> getChatUserList(String chusChroId) {
		return dao.getChatUserList(chusChroId);
	}

	@Override
	public int insertChatRoom(ChatRoomVo vo) {
		return dao.insertChatRoom(vo);
	}

	@Override
	public int insertChatUser(ChatUserVo vo) {
		return dao.insertChatUser(vo);
	}

	@Override
	public int updateChatLog(ChatRoomVo vo) {
		return dao.updateChatLog(vo);
	}

	@Override
	public int delChatRoom(String chroId) {
		return dao.delChatRoom(chroId);
	}

	@Override
	public int delAllChatUser(String chusChroId) {
		return dao.delAllChatUser(chusChroId);
	}

	@Override
	public int delChatUser(int chusId) {
		return dao.delChatUser(chusId);
	}

}
