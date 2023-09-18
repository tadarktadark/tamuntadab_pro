package com.tdtd.tmtd.model.service;

import java.util.List;

import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;

public interface IChatService {
	
//	채팅방 목록 조회
	public List<ChatRoomVo> getChatRoomList(String accountId);
//	채팅방 조회
	public ChatRoomVo getChatDetail(String chroId);
//	채팅방 참가자 정보 조회
	public List<ChatUserVo> getChatUserList(String chusChroId);
//	채팅방 생성
	public int insertChatRoom(ChatRoomVo vo);
//	채팅방 참가자 추가
	public int insertChatUser(ChatUserVo vo);
//	채팅 저장하기
	public int updateChatLog(ChatRoomVo vo);
//	채팅방 삭제
	public int delChatRoom(String chroId);
//	채팅방에 있는 모든 참가자 삭제
	public int delAllChatUser(String chusChroId);
//	채팅 참가자 퇴장
	public int delChatUser(int chusId);

}
