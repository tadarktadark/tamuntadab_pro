package com.tdtd.tmtd;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.ISingoService;
import com.tdtd.tmtd.vo.SingoSayuVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SingoController {
	@Autowired
	private ISingoService service;
	
	@RequestMapping(value="/communitySingo.do", method=RequestMethod.GET)
	@ResponseBody
	public List<SingoSayuVo> communitySingo() {
		log.info("@@@@@@@@@@@@@@@ 게시글 신고 모달창");
		return service.getSingoCategory();
	}
	
	@RequestMapping(value="/userSingo.do", method=RequestMethod.POST)
	@ResponseBody
	public int userSingo(HttpSession session, String daesangId, SingoSayuVo sVo) {
		String board = (String)session.getAttribute("community");
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		sVo.setSayuAccountId(userInfo.getUserAccountId());
		
		if(sVo.getContent().equals("")) {
			sVo.setContent(null);
		}
		
		log.info("@@@@@@@@@@@@@@@ 게시글 신고 : board {}, daesangId {}, sVo {}", board, daesangId, sVo);
			
		
		return service.userSingo(daesangId, board, sVo);
	}
}
