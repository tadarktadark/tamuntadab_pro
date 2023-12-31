package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.UserProfileVo;

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
		return sqlSession.selectOne(NS+"getChatDetail",chroId);
	}

	@Override
	public List<ChatUserVo> getChatUserList(String chusChroId) {
		return sqlSession.selectList(NS+"getChatUserList",chusChroId);
	}

	@Override
	public int insertChatRoom(Map<String, Object> map) {
		return sqlSession.insert(NS+"insertChatRoom",map);
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

	@Override
	public ClassVo getClassInfo(int clasId) {
		return sqlSession.selectOne(NS+"getClassInfo",clasId);
	}

	@Override
	public List<ChamyeoVo> getClassUser(int clchClasId) {
		return sqlSession.selectList(NS+"getClassUser",clchClasId);
	}

	@Override
	public ChatRoomVo getChatDetailByClasId(int chroClasId) {
		return sqlSession.selectOne(NS+"getChatDetailByClasId",chroClasId);
	}

	@Override
	public UserProfileVo getInstrInfo(String userAccountId) {
		return sqlSession.selectOne(NS+"getInstrInfo",userAccountId);
	}

	@Override
	public int countChatRoom(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"countChatRoom",map);
	}

	@Override
	public int countClassChatRoom(int chroClasId) {
		return sqlSession.selectOne(NS+"countClassChatRoom",chroClasId);
	}

	@Override
	public int updateChatCount(String chroId) {
		return sqlSession.update(NS+"updateChatCount",chroId);
	}

	@Override
	public int updateUserChatCount(Map<String, Object> map) {
		return sqlSession.update(NS+"updateUserChatCount",map);
	}

	@Override
	public ChatUserVo getChatUser(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"getChatUser",map);
	}



}
