package com.tdtd.tmtd.socket;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 웹소켓 핸들러
 * 클라이언트의 연결 요청 및 메시지 송수신, 연결 종료 등의 웹소켓 라이프사이클에 따른 동작들을 정의합니다.
 * 
 * @author 김다현
 *
 */
@Component(value = "wsChat.do")
public class SocketHandler extends TextWebSocketHandler {

	// 웹소켓 세션을 저장할 ArrayList
	private ArrayList<WebSocketSession> list;
	
	// 생성자에서 ArrayList를 초기화
	public SocketHandler() {
		list = new ArrayList<WebSocketSession>();
	}

	// 웹소켓 연결 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		list.add(session);
		
	}
	
	// 메시지가 오면 실행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		// 받은 메시지
		String msg = message.getPayload();
		// 보낼 메시지
		String txt = "";
		
		Map<String, Object> mySession = session.getAttributes();
		String crSession = (String)mySession.get("cr_id");//현재 세션의 채팅방 ID

		
		String sentUserId = msg.substring(0,msg.indexOf(":")).trim();//보낸 사용자의 ID
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
	
	
	//웹소켓 연결 종료 시 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		
		// 세션 정보를 삭제
		list.remove(session);
		
	}
}
