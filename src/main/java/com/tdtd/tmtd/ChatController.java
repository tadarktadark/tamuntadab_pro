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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;

import com.tdtd.tmtd.model.service.IChatService;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.UserProfileVo;

@Controller
public class ChatController implements ServletConfigAware{
	
	private ServletContext servletContext;
	
	@Override
	public void setServletConfig(ServletConfig servletConfig) {
		servletContext = servletConfig.getServletContext();
	}
	
	
	@Autowired
	private IChatService service;
	
	
	@GetMapping("/chatPage.do")
	public String chatPage(HttpServletRequest request,HttpSession session,Model model) {
		request.setAttribute("title", "채팅");
		UserProfileVo userVo = (UserProfileVo) session.getAttribute("userInfo");
		if(userVo==null) {
			return "redirect:/loginForm.do";
		}
		String accountId = userVo.getUserAccountId();
		session.setAttribute("mem_id", accountId);
		List<ChatRoomVo> roomList = service.getChatRoomList(accountId);
		model.addAttribute("roomList", roomList);
		return "chat";
	}
	
	
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
	
	
	
	@ResponseBody
	@GetMapping("/chatRoom.do")
	public ChatRoomVo chatRoom(HttpSession session, String chroId) {
		System.out.println(chroId);
		UserProfileVo userVo = (UserProfileVo)session.getAttribute("userInfo");
		String mem_id = userVo.getUserAccountId();
		System.out.println("mem_id ============"+mem_id);
		session.setAttribute("cr_id", chroId);
		session.setAttribute("mem_id", mem_id);
		ChatRoomVo chatRoomVo = service.getChatDetail(chroId);
		System.out.println("채팅방 vo : "+chatRoomVo);
		return chatRoomVo;
	}


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
	
	
	@PostMapping("/updateChatLog.do")
	@ResponseBody
	public void updateChatLog(String chroId,String chroChatLog) {
		ChatRoomVo chatRoomVo = new ChatRoomVo();
		chatRoomVo.setChroId(chroId);
		chatRoomVo.setChroChatLog(chroChatLog.replace("class=\"right\"", ""));
		service.updateChatLog(chatRoomVo);
	}
	
	
	
	
	

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
