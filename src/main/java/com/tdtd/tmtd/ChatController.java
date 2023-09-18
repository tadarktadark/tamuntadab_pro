package com.tdtd.tmtd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.IChatService;
import com.tdtd.tmtd.vo.ChamyeoVo;
import com.tdtd.tmtd.vo.ChatRoomVo;
import com.tdtd.tmtd.vo.ChatUserVo;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.UserProfileVo;

@Controller
public class ChatController {
	
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
		List<ChatRoomVo> roomList = service.getChatRoomList(accountId);
		model.addAttribute("roomList", roomList);
		return "chat";
	}
	
	
	@PostMapping("/classChatRoom.do")
	public String classChatRoom(int clasId) {
		ClassVo classVo = service.getClassInfo(clasId);
		
		Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String dateString = formatter.format(now);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("chroId", "CR"+dateString);
        map.put("chroClasId", clasId);
        map.put("chroTitle", classVo.getClasTitle());
        map.put("chroChatLog", "");
		service.insertChatRoom(map);
		String chroId = service.getChatDetailByClasId(clasId).getChroId();
		//참가자 추가
		List<ChamyeoVo> classUserList = service.getClassUser(clasId);
		for (ChamyeoVo chamyeoVo : classUserList) {
			String clchAccountId = chamyeoVo.getClchAccountId();
			String chusType = chamyeoVo.getClchYeokal();
			ChatUserVo chatUser = new ChatUserVo();
			chatUser.setChusAccountId(clchAccountId);
			chatUser.setChusChroId(chroId);//채팅방ID
			chatUser.setChusType(chusType);
			service.insertChatUser(chatUser);//방장인지 아닌지 구분이라서 바꿔야함
			//그리고 방장을 따로 추가하는 코드 추가해야함
		}
		return "redirect:/chatPage.do";
	}
	
	
	
	
	@GetMapping("/intsrChatRoom.do")
	public String intsrChatRoom(String studAccountId,String instrAccountId) {
		UserProfileVo instrVo = service.getInstrInfo(instrAccountId);
		int instrClasId = Integer.parseInt(instrAccountId.substring(4));
		
		Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap.put("chroClasId", instrClasId);
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
		map.put("chroClasId", instrClasId);
		map.put("chroTitle", instrVo.getUserNickname());
		map.put("chroChatLog", "");
		service.insertChatRoom(map);
		String chroId = service.getChatDetailByClasId(instrClasId).getChroId();
		
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
	public ChatRoomVo chatRoom(String chroId) {
		System.out.println(chroId);
		ChatRoomVo chatRoomVo = service.getChatDetail(chroId);
		System.out.println("채팅방 vo : "+chatRoomVo);
		return chatRoomVo;
	}

}
