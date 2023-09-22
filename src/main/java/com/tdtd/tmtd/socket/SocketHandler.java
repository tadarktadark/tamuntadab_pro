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
		String memSession = (String)mySession.get("mem_id");
		System.out.println(crSession);
		System.out.println(memSession);
		
		if(msg.indexOf("#&nick_")!=-1) {
			for(WebSocketSession s : list) {
				Map<String, Object> sessionMap = s.getAttributes();
				String otherCrSession = (String)sessionMap.get("cr_id");
				
				if(crSession.equals(otherCrSession)) {// 같은 그룹의 세션에게 메시지 전달
					s.sendMessage(new TextMessage("<font color='pink' size='2px'>"+memSession+"님이 입장했습니다</font>"));
				}
			}
		}else {
			String msg2 = msg.substring(0,msg.indexOf(":")).trim();
			for(WebSocketSession s : list) {
				Map<String, Object> sessionMap = s.getAttributes();
				String otherCrSession = (String)sessionMap.get("cr_id");
				String otherMemSession = (String)sessionMap.get("mem_id");
				
				if(crSession.equals(otherCrSession)) {//같은 그룹
					if(msg2.equals(otherMemSession)) {
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년도 MM월 dd일 HH시 mm분");
		String now = sdf.format(new Date());
		// 같은 그룹에게 퇴장 메시지 전달
		for (WebSocketSession s : list) {
			Map<String, Object> sessionMap = s.getAttributes();
			String otherCrSession = (String) sessionMap.get("cr_id");
			if(myCrSession.equals(otherCrSession)) {
				s.sendMessage(new TextMessage("<font color='blue' size='2px'>"+myMemSession+"님이 퇴장하였습니다("+now+")</font>"));
			}
		}
		
	}
}
