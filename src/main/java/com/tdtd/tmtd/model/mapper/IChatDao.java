package com.tdtd.tmtd.model.mapper;

import java.util.List;
import java.util.Map;

import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.UserProfileVo;

public interface IChatDao {

//	채팅방 목록 조회
	public List<ChatRoomVo> getChatRoomList(String accountId);
//	채팅방 조회
	public ChatRoomVo getChatDetail(String chroId);
	//채팅방 클래스아이디로 조회
	public ChatRoomVo getChatDetailByClasId(int chroClasId);
//	채팅방 참가자 정보 조회
	public List<ChatUserVo> getChatUserList(String chusChroId);
//	채팅방 생성
	public int insertChatRoom(Map<String,Object> map);
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
	//클래스 정보 조회
	public ClassVo getClassInfo(int clasId);
	//클래스 참가자 목록 조회
	public List<ChamyeoVo> getClassUser(int clchClasId);
	//유저 정보 조회
	public UserProfileVo getInstrInfo(String userAccountId);
	//채팅방 세기
	public int countChatRoom(Map<String,Object> map);
	//클래스채팅방 세기
	public int countClassChatRoom(int chroClasId);
}
