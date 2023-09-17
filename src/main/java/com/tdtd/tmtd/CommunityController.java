package com.tdtd.tmtd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tdtd.tmtd.comm.LikeViewUtils;
import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IJayuService;
import com.tdtd.tmtd.model.service.IJilmunService;
import com.tdtd.tmtd.model.service.IPilgiService;
import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.PagingVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommunityController {

	@Autowired
	private IPilgiService pService;
	
	@Autowired
	private IJilmunService jmService;
	
	@Autowired
	private IJayuService jyService;
	
	@RequestMapping(value="/community.do", method=RequestMethod.GET)
	public String community(Model model, HttpSession session, String board) {
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 이동 : board {}", board);
		model.addAttribute("title","커뮤니티");
		
		if(board.equals("pilgi")) {
			model.addAttribute("pageTitle", "필기");
			session.setAttribute("community","pilgi");
		}else if(board.equals("jilmun")) {
			model.addAttribute("pageTitle", "질문");
			session.setAttribute("community","jilmun");			
		}else if(board.equals("jayu")) {
			model.addAttribute("pageTitle", "자유");
			session.setAttribute("community","jayu");
		}	
		
		return "communityList";
	}
	
	@RequestMapping(value="/getPilgiList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getPilgiList(HttpSession session, String orderBy, String page){
		log.info("@@@@@@@@@@@@@@@ 필기 목록 조회 : {}, {}", orderBy, page);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		int pageCount = pService.getPilgiCount(userInfo.getUserAccountId());			
		Map<String, Object> pMap = PagingUtils.paging(page, pageCount, 10, 5);
		
		Map<String, Object> bMap = new HashMap<String, Object>(){{
			put("orderBy", orderBy);
			put("start", pMap.get("start"));
			put("end", pMap.get("end"));
		}};
		bMap.put("accountId", userInfo.getUserAccountId());
		
		Map<String, Object> result = new HashMap<String, Object>(){{
			put("b",pService.getPilgiList(bMap));
			put("p", pMap.get("page"));
		}};
		return result;
	}
	
	@RequestMapping(value="/pilgiLikeUser.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> pilgiLikeUser(HttpSession session, String type, String id){
		log.info("@@@@@@@@@@@@@@@ 필기 좋아요 : {}, {}", type, id);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		String list = pService.getPilgiLikeUser(id);		
		Map<String, Object> like = LikeViewUtils.like(type, userInfo.getUserAccountId(), list);
		if((int)like.get("update")==1) {
			Map<String, Object> data = new HashMap<String, Object>(){{
				put("likeUser", like.get("list"));
				put("likeCount", like.get("count"));
				put("id", id);
			}};
			pService.updatePilgiLikeUser(data);
		}
		Map<String, Object> result = new HashMap<String, Object>(){{
			put("type",like.get("type"));
			put("count",like.get("count"));
		}};
		return result;
	}
	
	@RequestMapping(value="/getPilgiDetail.do", method=RequestMethod.GET)
	public String getPilgiDetail(HttpSession session, Model model, String id){
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 상세조회 : {}", id);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		Map<String, Object> detail = new HashMap<String, Object>();
		detail.put("accountId",userInfo.getUserAccountId());
		detail.put("id",id );
		
		System.out.println(detail);
		
		BoardVo bVo = pService.getPilgiDetail(detail);
		
		String str = bVo.getSubjectCode().substring(1, bVo.getSubjectCode().length() - 1); // 대괄호 제거

		String[] array = str.split(","); // 쉼표를 기준으로 분리

		for (int i = 0; i < array.length; i++) {
		    array[i] = array[i].replaceAll("\"", "").trim(); // 큰따옴표 제거 및 공백 제거
		}
		
		model.addAttribute("title","커뮤니티");
		model.addAttribute("pageTitle", "필기");
		model.addAttribute("bVo", bVo);
		model.addAttribute("yList", pService.getYeongwanList(detail));
		model.addAttribute("subArr", array);
		return "pilgiDetails";
	}
	
	@RequestMapping(value="/commynityWriteForm.do", method=RequestMethod.GET)
	public String commynityWriteForm(Model model, HttpSession session, String id) {
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 새 글 작성 Form 이동 : board {}, boardId {}", session.getAttribute("community"), id);
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		String board = (String)session.getAttribute("community");
		if(board.equals("pilgi")) {
			model.addAttribute("pageTitle", "필기");
			model.addAttribute("classVo",pService.getPilgiClassDetail(id)); 
		} else if(board.equals("jilmun")) {
			model.addAttribute("pageTitle", "질문");
			model.addAttribute("classList",jmService.getJilmunClassList(userInfo.getUserAccountId()));
		} else if(board.equals("jayu")) {
			model.addAttribute("pageTitle", "자유");
		}
		
		return "commynityWriteForm";
	}
}
