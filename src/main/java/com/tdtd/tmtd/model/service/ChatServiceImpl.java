package com.tdtd.tmtd.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdtd.tmtd.model.mapper.IChatDao;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.UserProfileVo;

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
	public int insertChatRoom(Map<String, Object> map) {
		return dao.insertChatRoom(map);
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

	@Override
	public ClassVo getClassInfo(int clasId) {
		return dao.getClassInfo(clasId);
	}

	@Override
	public List<ChamyeoVo> getClassUser(int clchClasId) {
		return dao.getClassUser(clchClasId);
	}

	@Override
	public ChatRoomVo getChatDetailByClasId(int chroClasId) {
		return dao.getChatDetailByClasId(chroClasId);
	}

	@Override
	public UserProfileVo getInstrInfo(String userAccountId) {
		return dao.getInstrInfo(userAccountId);
	}

	@Override
	public int countChatRoom(Map<String, Object> map) {
		return dao.countChatRoom(map);
	}

	@Override
	public int countClassChatRoom(int chroClasId) {
		return dao.countClassChatRoom(chroClasId);
	}

}
