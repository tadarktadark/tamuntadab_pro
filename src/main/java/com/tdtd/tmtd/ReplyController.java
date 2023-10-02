package com.tdtd.tmtd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.IAlarmService;
import com.tdtd.tmtd.model.service.IJayuService;
import com.tdtd.tmtd.model.service.IJilmunService;
import com.tdtd.tmtd.model.service.IPilgiService;
import com.tdtd.tmtd.model.service.IReplyService;
import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.ReplyVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	@Autowired
	private IAlarmService alarm;
	
	@Autowired
	private IPilgiService pilgi;
	
	@Autowired
	private IJilmunService jilmun;
	
	@Autowired
	private IJayuService jayu;
	
	@RequestMapping(value="/getReplyList.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReplyList(String page, String boardId) {
		log.info("@@@@@@@@@@@@@@@ 댓글 보기 : boardId {}", boardId);
		
		int pageCount = service.getReplyCount(boardId);
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 5, 5);
		
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("boardId", boardId);
		rMap.put("start", pMap.get("start"));
		rMap.put("end", pMap.get("end"));
		List<ReplyVo> list = service.getRootReplyList(rMap);
		
		List<Integer> rootSeq = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			rootSeq.add(list.get(i).getRootSeq());		
		}
		
		rMap.put("rootSeq", rootSeq);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", pMap.get("page"));
		result.put("rootReply", list);
		result.put("reReply", service.getReReplyList(rMap));
		return result;
	}
	
	@RequestMapping(value="/replyWrite.do", method=RequestMethod.POST)
	public String replyWrite(HttpSession session, ReplyVo vo) {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ ROOT 댓글 작성 : board {}, ReplyVo {}", board, vo);
		
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		vo.setWriterId(userInfo.getUserAccountId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("boardId", vo.getBoardId());
		service.insertRootReply(vo, map);
		
		BoardVo bVo = service.getBoardAlramInfo(vo.getBoardId());
		
		String url = "communityDetails.do?board="+board+"&id="+bVo.getId();
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formattedDate = currentDate.format(formatter);
		String alarId = "AT_R"+formattedDate;
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("alarId", alarId);
		insertMap.put("alarContent", "[댓글 등록] "+bVo.getTitle()+" 게시글에 댓글이 달렸습니다.");
		insertMap.put("alarAccountId", bVo.getAccountId());
		insertMap.put("alarReplySeq", url);
		alarm.insertAlarm(insertMap);
		
		return "redirect:/communityDetails.do?id="+vo.getBoardId();
	}
	
	@RequestMapping(value="/getUpdateContent.do", produces = "text/plain; charset=UTF-8", method=RequestMethod.POST)
	@ResponseBody
	public String getUpdateContent(String seq) {
		log.info("@@@@@@@@@@@@@@@ 댓글 수정 데이터 조회 : seq {}", seq);
		
		return service.getUpdateContent(seq);
	}
	
	@RequestMapping(value="/replyUpdate.do", method=RequestMethod.POST)
	public String replyUpdate(HttpSession session, ReplyVo vo) {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ ROOT 댓글 수정 : board {}, ReplyVo {}", board, vo);
		
		service.updateReply(vo);
		
		return "redirect:/communityDetails.do?id="+vo.getBoardId();
	}
	
	@RequestMapping(value="/replyDelete.do", method=RequestMethod.GET)
	public String replyDelete(HttpSession session, String boardId, int seq) {
		String board = (String)session.getAttribute("community");
		log.info("@@@@@@@@@@@@@@@ 댓글 삭제 : board {}, boardId {}, seq {}", board, boardId, seq);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("board", board);
		map.put("boardId", boardId);
		service.deleteReply(map);
		
		return "redirect:/communityDetails.do?id="+boardId;
	}
	
	@RequestMapping(value="/insertReReply.do", method=RequestMethod.POST)
	public String insertReReply(HttpSession session, ReplyVo vo) {
		log.info("@@@@@@@@@@@@@@@ 대댓글 입력 : vo {}", vo);
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		vo.setWriterId(userInfo.getUserAccountId());
		service.insertReReply(vo);
		
		ReplyVo alram = service.getReplyAlramInfo(""+vo.getRootSeq());
		String board = "";
		if(alram.getBoardId().substring(0,2).equals("PI")) {
			board = "pilgi";
		} else if(alram.getBoardId().substring(0,2).equals("JI")) {
			board = "jilmun";
		} else if(alram.getBoardId().substring(0,2).equals("JA")) {
			board = "jayu";
		}
		String url = "communityDetails.do?board="+board+"&id="+alram.getBoardId();
		String regex = "<[^>]+>";
		String content = alram.getContent().replaceAll(regex, "").length()>20?alram.getContent().replaceAll(regex, "").substring(0,20)+"...":alram.getContent().replaceAll(regex, "");
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formattedDate = currentDate.format(formatter);
		String alarId = "AT_R"+formattedDate;
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("alarId", alarId);
		insertMap.put("alarContent", "[대댓글 등록] "+content+" 댓글에 대댓글이 달렸습니다.");
		insertMap.put("alarAccountId", alram.getWriterId());
		insertMap.put("alarReplySeq", url);
		alarm.insertAlarm(insertMap);
		
		return "redirect:/communityDetails.do?id="+vo.getBoardId();
	}
	
	@RequestMapping(value="/updateReReply.do", method=RequestMethod.POST)
	public String updateReReply(HttpSession session, ReplyVo vo) {
		log.info("@@@@@@@@@@@@@@@ 대댓글 수정 : vo {}", vo);
		
		service.updateReply(vo);
		
		return "redirect:/communityDetails.do?id="+vo.getBoardId();
	}
	
	@RequestMapping(value="/replyChaetaek.do", method=RequestMethod.GET)
	public String replyChaetaek(int seq, String boardId) {
		log.info("@@@@@@@@@@@@@@@ 댓글 채택 : seq {}, boardId {}", seq, boardId);
		
		service.updateChaetaek(seq, boardId);
		
		ReplyVo alram = service.getReplyAlramInfo(""+seq);
		String board = "";
		if(alram.getBoardId().substring(0,2).equals("PI")) {
			board = "pilgi";
		} else if(alram.getBoardId().substring(0,2).equals("JI")) {
			board = "jilmun";
		} else if(alram.getBoardId().substring(0,2).equals("JA")) {
			board = "jayu";
		}
		
		String url = "communityDetails.do?board="+board+"&id="+alram.getBoardId();
		String regex = "<[^>]+>";
		String content = alram.getContent().replaceAll(regex, "").length()>20?alram.getContent().replaceAll(regex, "").substring(0,20)+"...":alram.getContent().replaceAll(regex, "");
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
		String formattedDate = currentDate.format(formatter);
		String alarId = "AT_R"+formattedDate;
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("alarId", alarId);
		insertMap.put("alarContent", "[댓글 채택] "+content+" 댓글이 채택되었습니다. 채택된 댓글은 삭제 및 수정이 불가합니다.");
		insertMap.put("alarAccountId", alram.getWriterId());
		insertMap.put("alarReplySeq", url);
		alarm.insertAlarm(insertMap);
		
		return "redirect:/communityDetails.do?board=jilmun&id="+boardId;
	}
	
	@RequestMapping(value="/myPilgi.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> myPilgi(HttpSession session, String page) {
		log.info("@@@@@@@@@@@@@@@ 내 작성 필기 목록 조회");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		int pageCount = service.getWritePilgiCount(userInfo.getUserAccountId());
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 10, 5);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		bMap.put("accountId", userInfo.getUserAccountId());
		
		List<BoardVo> list = service.getWritePilgiList(bMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bList", list);
		result.put("pList", pMap.get("page"));
		return result;
	}
	
	@RequestMapping(value="/myJilmun.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> myJilmun(HttpSession session, String page) {
		log.info("@@@@@@@@@@@@@@@ 내 작성 질문 목록 조회");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		int pageCount = service.getWriteJilmunCount(userInfo.getUserAccountId());
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 10, 5);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		bMap.put("accountId", userInfo.getUserAccountId());
		
		List<BoardVo> list = service.getWriteJilmunList(bMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bList", list);
		result.put("pList", pMap.get("page"));
		return result;
	}
	
	@RequestMapping(value="/myJayu.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> myJayu(HttpSession session, String page) {
		log.info("@@@@@@@@@@@@@@@ 내 작성 자유 목록 조회");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		int pageCount = service.getWriteJayuCount(userInfo.getUserAccountId());
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 10, 5);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		bMap.put("accountId", userInfo.getUserAccountId());
		
		List<BoardVo> list = service.getWriteJayuList(bMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bList", list);
		result.put("pList", pMap.get("page"));
		return result;
	}
	
	@RequestMapping(value="/myReply.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> myReply(HttpSession session, String page) {
		log.info("@@@@@@@@@@@@@@@ 내 작성 댓글 목록 조회");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		int pageCount = service.getWriteReplyCount(userInfo.getUserAccountId());
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 10, 5);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		bMap.put("accountId", userInfo.getUserAccountId());
		
		List<ReplyVo> list = service.getWriteReplyList(bMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bList", list);
		result.put("pList", pMap.get("page"));
		return result;
	}
	
	@RequestMapping(value="/myLike.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> myLike(HttpSession session, String page) {
		log.info("@@@@@@@@@@@@@@@ 내 좋아요 게시글 목록 조회");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		
		int pageCount = service.getLikeCommCount(userInfo.getUserAccountId());
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 10, 5);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		bMap.put("accountId", userInfo.getUserAccountId());
		
		List<BoardVo> list = service.getLikeCommList(bMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bList", list);
		result.put("pList", pMap.get("page"));
		return result;
	}
	
	@RequestMapping(value="/myReplyDelete.do", method=RequestMethod.POST)
	@ResponseBody
	public int myReplyDelete(HttpSession session, int seq, String board, String boardId){
		log.info("@@@@@@@@@@@@@@@ 마이페이지 댓글 삭제 : seq {}, board {}, boardId {}", seq, board, boardId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("board", board);
		map.put("boardId", boardId);
		
		return service.deleteReply(map);
	}
}
