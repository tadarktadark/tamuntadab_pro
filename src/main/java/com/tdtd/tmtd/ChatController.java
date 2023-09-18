package com.tdtd.tmtd;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.IChatService;
import com.tdtd.tmtd.vo.ChatRoomVo;
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
			return "/loginForm.do";
		}
		String accountId = userVo.getUserAccountId();
		List<ChatRoomVo> roomList = service.getChatRoomList(accountId);
		model.addAttribute("roomList", roomList);
		return "chat";
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
