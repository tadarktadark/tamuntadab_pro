package com.tdtd.tmtd.socket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component(value = "wsChat.do")
public class SocketHandler extends TextWebSocketHandler {

	
	private ArrayList<WebSocketSession> list;
	
	public SocketHandler() {
		list = new ArrayList<WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		list.add(session);
		
		Map<String, Object> sessionMap = session.getAttributes();
		String crSession = (String)sessionMap.get("cr_id");
		String memSession = (String)sessionMap.get("mem_id");
		System.out.println(crSession+memSession);
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		String msg = message.getPayload();
		String txt = "";
		
		Map<String, Object> mySession = session.getAttributes();
		String crSession = (String)mySession.get("cr_id");

		
		String sentUserId = msg.substring(0,msg.indexOf(":")).trim();
		String sentNickName = msg.substring(msg.indexOf("#&nickName_")+11,msg.indexOf("#&profileImg_")).trim();
		String sentImg = msg.substring(msg.indexOf("#&profileImg_")+13,msg.length()).trim();
		System.out.println(sentUserId+sentNickName+sentImg);
		for(WebSocketSession s : list) {
			Map<String, Object> sessionMap = s.getAttributes();
			String otherCrSession = (String)sessionMap.get("cr_id");
			String otherMemSession = (String)sessionMap.get("mem_id");
			
			System.out.println("crSession : "+crSession);
			System.out.println("otherCrSession : "+otherCrSession);
			System.out.println("otherMemSession : "+otherMemSession);
			System.out.println(sentUserId);
			
			if(crSession.equals(otherCrSession)) {//같은 그룹
				if(sentUserId.equals(otherMemSession)) {
					String newMsg = "[나]"+msg.replace(msg.substring(0,msg.trim().indexOf(":")+1), "");
					txt=newMsg;
				}else {
					String part1 = msg.substring(0,msg.indexOf(":")).trim();
					String part2 = "["+part1+"]\n"+msg.substring(msg.trim().indexOf(":")+1);
					txt=part2;
				}
				s.sendMessage(new TextMessage(txt));
			}
		}
		
		super.handleTextMessage(session, message);
	}
	
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		// 접속을 종료하고자 하는 WebSocketSession의 그룹 확인
		Map<String, Object> mySession = session.getAttributes();
		String myCrSession = (String) mySession.get("cr_id");
		String myMemSession = (String)mySession.get("mem_id");
		
		// 세션 정보를 삭제
		list.remove(session);
		
	}
}
