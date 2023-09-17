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
	
	@RequestMapping(value="/getCommunityList.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCommunityList(HttpSession session, String orderBy, String page){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 목록 조회 : board {}, orderBy {}, page {}", board, orderBy, page);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		int pageCount; // 전체 게시글 수
		Map<String, Object> pMap = new HashMap<String, Object>(); // page 객체 및 start, end
		Map<String, Object> bMap = new HashMap<String, Object>(); // board 관련 accountId, orderBy, start, end
		bMap.put("accountId", userInfo.getUserAccountId());
		bMap.put("orderBy", orderBy);
		
		if(board.equals("pilgi")) {
			pageCount = pService.getPilgiCount(userInfo.getUserAccountId());
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		} else if(board.equals("jilmun")) {
			pageCount = jmService.getJilmunCount();
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		} else if(board.equals("jayu")) {
			pageCount = jyService.getJayuCount();
			pMap = PagingUtils.paging(page, pageCount, 10, 5);
		}
		
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pMap.get("page"));
		
		if(board.equals("pilgi")) {
			result.put("board", pService.getPilgiList(bMap));
		} else if(board.equals("jilmun")) {
			result.put("board", jmService.getJilmunList(bMap));
		} else if(board.equals("jayu")) {
			result.put("board", jyService.getJayuList(bMap));
		}
		
		return result;
	}
	
	@RequestMapping(value="/commynityLike.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> commynityLike(HttpSession session, String type, String id){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 좋아요 : board {}, type {}, id {}", board, type, id);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		String list = "";
		
		if(board.equals("pilgi")) {
			list = pService.getPilgiLikeUser(id);
		} else if(board.equals("jilmun")) {
			list = jmService.getJilmunLikeUser(id);
		} else if(board.equals("jayu")) {
			list = jyService.getJayuLikeUser(id);
		}
		
		Map<String, Object> like = LikeViewUtils.like(type, userInfo.getUserAccountId(), list);
		if((int)like.get("update")==1) {
			Map<String, Object> data = new HashMap<String, Object>(){{
				put("likeUser", like.get("list"));
				put("likeCount", like.get("count"));
				put("id", id);
			}};
			if(board.equals("pilgi")) {
				pService.updatePilgiLikeUser(data);
			} else if(board.equals("jilmun")) {
				jmService.updateJilmunLikeUser(data);
			} else if(board.equals("jayu")) {
				jyService.updateJayuLikeUser(data);
			}
		}
		
		Map<String, Object> result = new HashMap<String, Object>(){{
			put("type",like.get("type"));
			put("count",like.get("count"));
		}};
		return result;
	}
	
	@RequestMapping(value="/communityDetails.do", method=RequestMethod.GET)
	public String communityDetails(HttpSession session, Model model, String id){
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 상세조회 : board {}, id {}", board, id);
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
		Map<String, Object> detail = new HashMap<String, Object>();
		detail.put("accountId",userInfo.getUserAccountId());
		detail.put("id",id );
		
		BoardVo bVo = new BoardVo();
		
		if(board.equals("pilgi")) {
			bVo = pService.getPilgiDetail(detail);
			model.addAttribute("pageTitle", "필기");
			model.addAttribute("yList", pService.getYeongwanList(detail));
		} else if(board.equals("jilmun")) {
			bVo = jmService.getJilmunDetail(detail);
			model.addAttribute("pageTitle", "질문");
		} else if(board.equals("jayu")) {
			bVo = jyService.getJayuDetail(detail);
			model.addAttribute("pageTitle", "자유");
		}
		
		String str = "";
		String[] array = null;
		
		if(bVo.getSubjectCode()!=null) {
			str = bVo.getSubjectCode().substring(1, bVo.getSubjectCode().length() - 1); // 대괄호 제거
			array = str.split(","); // 쉼표를 기준으로 분리

			for (int i = 0; i < array.length; i++) {
			    array[i] = array[i].replaceAll("\"", "").trim(); // 큰따옴표 제거 및 공백 제거
			}
		}

		model.addAttribute("bVo", bVo);
		model.addAttribute("subArr", array);
		return "communityDetails";
	}
	
	@RequestMapping(value="/commynityWriteForm.do", method=RequestMethod.GET)
	public String commynityWriteForm(Model model, HttpSession session, String id) {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 커뮤니티 새 글 작성 Form 이동 : board {}, boardId {}", board, id);
		model.addAttribute("title","커뮤니티");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		if(userInfo == null) {
			userInfo = new UserProfileVo();
			userInfo.setUserAccountId("TMTD0");
		}
		
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
