package com.tdtd.tmtd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		//채팅방 생성ㅌ CR+230906+SEQ
		 Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String dateString = formatter.format(now);
        
		ChatRoomVo newChatRoomVo = new ChatRoomVo();
		newChatRoomVo.setChroId("CR"+dateString);
		newChatRoomVo.setChroClasId(clasId);
		newChatRoomVo.setChroTitle(classVo.getClasTitle());
		service.insertChatRoom(newChatRoomVo);
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
			service.insertChatUser(chatUser);
		}
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
