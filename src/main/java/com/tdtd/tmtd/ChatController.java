package com.tdtd.tmtd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;

import com.tdtd.tmtd.model.service.IChatService;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.UserProfileVo;

/**
 * 채팅 관련 컨트롤러
 * 
 * @author 김다현
 *
 */
@Controller
public class ChatController implements ServletConfigAware{
	
	private ServletContext servletContext;
	
	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		servletContext = servletConfig.getServletContext();
	}
	
	
	@Autowired
	private IChatService service;
	
	/**
	 * 채팅 페이지 이동
	 * 
	 * @param request 제목 설정
	 * @param session 세션
	 * @param model 채팅방 목록 전달
	 * @return
	 */
	@GetMapping("/chatPage.do")
	public String chatPage(HttpServletRequest request,HttpSession session,Model model) {
		request.setAttribute("title", "채팅");
		UserProfileVo userVo = (UserProfileVo) session.getAttribute("userInfo");
		//채팅이 이미 있다면 채팅 페이지로 이동
		if(userVo==null) {
			return "redirect:/loginForm.do";
		}
		String accountId = userVo.getUserAccountId();
		session.setAttribute("mem_id", accountId);
		List<ChatRoomVo> roomList = service.getChatRoomList(accountId);
		model.addAttribute("roomList", roomList);
		return "chat";
	}
	
	/**
	 * 클래스 채팅방 생성 
	 * 
	 * @param clasId 클래스 아이디
	 * @return 채팅 페이지 이동
	 */
	@GetMapping("/classChatRoom.do")
	public String classChatRoom(int clasId) {
		//클래스 정보 받아오고
		ClassVo classVo = service.getClassInfo(clasId);
		//방 유무 검사
		int n = service.countClassChatRoom(clasId);
		if(n>0) {
			return "redirect:/chatPage.do";
		}
		//클래스 이름으로 채팅방 만들고
		Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String dateString = formatter.format(now);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chroId", "CR"+dateString);
        map.put("chroClasId", clasId);
        map.put("chroTitle", classVo.getClasTitle());
        map.put("chroChatLog", "");
		service.insertChatRoom(map);
		
		//클래스 인원들 받아와서
		List<ChamyeoVo> classUserList = service.getClassUser(clasId);
		
		//참가자 추가
		String chroId = service.getChatDetailByClasId(clasId).getChroId();
		for (ChamyeoVo chamyeoVo : classUserList) {
			String clchAccountId = chamyeoVo.getClchAccountId();
			String chusType = chamyeoVo.getClchYeokal();
			ChatUserVo chatUser = new ChatUserVo();
			chatUser.setChusAccountId(clchAccountId);
			chatUser.setChusChroId(chroId);//채팅방ID
			if(chusType.equals("S")||chusType.equals("I")){
				chatUser.setChusType("O");
			}else {
				chatUser.setChusType("M");
			}
			service.insertChatUser(chatUser);
		}
		return "redirect:/chatPage.do";
	}
	
	
	
	/**
	 * 강사 채팅방 생성
	 * 
	 * @param studAccountId 학생 아이디
	 * @param instrAccountId 강사 아이디
	 * @return 채팅 페이지 이동
	 */
	@GetMapping("/intsrChatRoom.do")
	public String intsrChatRoom(String studAccountId,String instrAccountId) {
		UserProfileVo instrVo = service.getInstrInfo(instrAccountId);
		int instrClasId = Integer.parseInt(instrAccountId.substring(4));
		UserProfileVo studVo = service.getInstrInfo(studAccountId);
		int studClasId = Integer.parseInt(studAccountId.substring(4));
		Map<String, Object> checkMap = new HashMap<String, Object>();
		int clasId = Integer.parseInt(instrClasId+""+studClasId) ;
		checkMap.put("chroClasId", clasId);
		checkMap.put("chusAccountId", studAccountId);
		//강사 채팅방 유무 판단
		int n = service.countChatRoom(checkMap);
		if(n>0) {
			return "redirect:/chatPage.do";
		}
		Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String dateString = formatter.format(now);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chroId", "CR"+dateString);
		map.put("chroClasId", clasId);
		map.put("chroTitle", instrVo.getUserNickname()+"/"+studVo.getUserNickname());
		map.put("chroChatLog", "");
		service.insertChatRoom(map);
		String chroId = service.getChatDetailByClasId(clasId).getChroId();
		
		//학생 채팅 유저에 추가
		ChatUserVo chatUser = new ChatUserVo();
		chatUser.setChusAccountId(studAccountId);//사용자 id
		chatUser.setChusChroId(chroId);//채팅방ID
		chatUser.setChusType("M");//유저 구분
		service.insertChatUser(chatUser);
		
		//강사 채팅 유저에 추가
		ChatUserVo chatInstr = new ChatUserVo();
		chatInstr.setChusAccountId(instrAccountId);//사용자 id
		chatInstr.setChusChroId(chroId);//채팅방ID
		chatInstr.setChusType("O");//유저 구분
		service.insertChatUser(chatInstr);
		
		return "redirect:/chatPage.do";
	}
	
	
	/**
	 * 채팅방 정보 조회 ajax
	 * 
	 * @param session 세션
	 * @param chroId 채팅방 아이디
	 * @return 채팅방 VO
	 */
	@ResponseBody
	@GetMapping("/chatRoom.do")
	public ChatRoomVo chatRoom(HttpSession session, String chroId) {
		UserProfileVo userVo = (UserProfileVo)session.getAttribute("userInfo");
		String mem_id = userVo.getUserAccountId();
		session.setAttribute("cr_id", chroId);
		session.setAttribute("mem_id", mem_id);
		ChatRoomVo chatRoomVo = service.getChatDetail(chroId);
		return chatRoomVo;
	}


	/**
	 * 채팅 웹소켓
	 * 
	 * @param cr_id 채팅방아이디
	 * @param mem_id 참가자아이디
	 * @param session 세션
	 * @return 채팅 페이지 이동
	 */
	@GetMapping("/openChatRoom.do")
	public String openChatRoom(String cr_id, String mem_id, HttpSession session) {
		session.setAttribute("mem_id", mem_id);
		session.setAttribute("cr_id", cr_id);
		
		//서버 전체에서 계속해서 참여자의 정보를 담기 위해서 ServletContext 객체를 사용한다
		Map<String, String> chatList = (Map<String, String>) servletContext.getAttribute("chatList");
		if(chatList ==null) {
			chatList = new HashMap<String,String>();
			chatList.put(mem_id, cr_id);
			servletContext.setAttribute("chatList", chatList);
		}else {
			chatList.put(mem_id, cr_id);
			servletContext.setAttribute("chatList", chatList);
		}
		return "chat";
	}
	
	
	/**
	 * 채팅 내역 저장 ajax
	 * 
	 * @param chroId 채팅방 아이디
	 * @param chroChatLog 채팅 내역
	 */
	@PostMapping("/updateChatLog.do")
	@ResponseBody
	public void updateChatLog(String chroId,String chroChatLog) {
		ChatRoomVo chatRoomVo = new ChatRoomVo();
		chatRoomVo.setChroId(chroId);
		chatRoomVo.setChroChatLog(chroChatLog.replace("class=\"right\"", ""));
		service.updateChatLog(chatRoomVo);
		service.updateChatCount(chroId);
	}
	
	/**
	 * 참가자 읽은 채팅 수 업데이트 ajax
	 * 
	 * @param chroId 채팅방 아이디
	 * @param chusAccountId 참가자 아이디
	 */
	@GetMapping("/updateUserChatCount.do")
	@ResponseBody
	public void updateUserChatCount(String chroId, String chusAccountId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("chroId", chroId);
		map.put("chusAccountId", chusAccountId);
		service.updateUserChatCount(map);
	}
	
	/**
	 * 안 읽은 채팅 수 ajax 
	 * 
	 * @param chroId 채팅방 아이디
	 * @param chusAccountId 참가자 아이디
	 * @return
	 */
	@PostMapping("/countChatAlarm.do")
	@ResponseBody
	public int countChatAlarm(String chroId,String chusAccountId) {
		System.out.println(chroId);
		ChatRoomVo vo = service.getChatDetail(chroId);
		int totalCount = vo.getChroChatCount();
		System.out.println("cnt : "+totalCount);
		
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("chusChroId", chroId);
		insertMap.put("chusAccountId", chusAccountId);
		ChatUserVo chatUserVo = service.getChatUser(insertMap);
		int readedChat = chatUserVo.getChusCount();
		int result = totalCount - readedChat;
		return result;
	}
	

	/**
	 * 웹소켓 종료 ajax
	 * 
	 * @param session 세션
	 */
	@PostMapping(value = "/socketOut.do")
	@ResponseBody
	public void socketOut(HttpSession session) {
		String mem_id = (String) session.getAttribute("mem_id");
		Map<String, String> chatList = (Map<String, String>)servletContext.getAttribute("chatList");
		
		if(chatList != null) {
			chatList.remove(mem_id);
		}
		
		servletContext.setAttribute("chatList", chatList);
	}
	
}
