package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.comm.PagingUtils;
import com.tdtd.tmtd.model.service.ISingoService;
import com.tdtd.tmtd.vo.BoardVo;
import com.tdtd.tmtd.vo.CareerVo;
import com.tdtd.tmtd.vo.SingoDaesangVo;
import com.tdtd.tmtd.vo.SingoSayuVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 신고 관련 컨트롤러
 * @author SoHyeon
 * @since 2023.09.21
 */
@Controller
@Slf4j
public class SingoController {
	
	@Autowired
	private ISingoService service;
	
	/**
	 * 신고 버튼 클릭시 모달창 열기
	 * @return 신고 카테고리 list
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
	@RequestMapping(value="/communitySingo.do", method=RequestMethod.GET)
	@ResponseBody
	public List<SingoSayuVo> communitySingo() {
		log.info("@@@@@@@@@@@@@@@ 게시글 신고 모달창");
		return service.getSingoCategory();
	}
	
	/**
	 * 유저 신고시 -> 신고 대상 추가(또는 엡더이트) 및 대상 보도 상태 변경, 신고 사유 입력
	 * @param session
	 * @param daesangId 대상보드id
	 * @param sVo 신고Vo
	 * @return 성공1, 실패0
	 * @author SoHyeon
	 * @since 2023.09.21
	 */
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
	
	/**
	 * 신고 관리 페이지 이동
	 * @param model
	 * @return 신고 관리 페이지 jsp
	 * @author SoHyeon
	 * @since 2023.09.26
	 */
	@RequestMapping(value="/admin/adminSingo.do", method=RequestMethod.GET)
	public String adminSingo(Model model) {
		log.info("@@@@@@@@@@@@@@@ 어드민 신고 페이지 이동");
		
		model.addAttribute("title", "회원관리");
		model.addAttribute("pageTitle", "신고관리");
		return "/admin/adminSingo";
	}
	
	/**
	 * 관리자 신고 목록 조회
	 * @param session
	 * @param page 현재 페이지
	 * @return 신고list, 페이지list
	 * @author SoHyeon
	 * @since 2023.09.26
	 */
	@RequestMapping(value="/admin/getMaxSingoList.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMaxSingoList(HttpSession session, String page) {
		log.info("@@@@@@@@@@@@@@@ 5회 이상 신고 목록 조회");
		
		int pageCount = service.getMaxCount();
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap = PagingUtils.paging(page, pageCount, 10, 5);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("start", pMap.get("start"));
		bMap.put("end", pMap.get("end"));
		
		List<SingoDaesangVo> list = service.getMaxSingo(bMap);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bList", list);
		result.put("pList", pMap.get("page"));
		return result;
	}
	
	/**
	 * 관리자 신고 처리
	 * @param vo 신고대상vo
	 * @return 성공1, 실패0
	 * @author SoHyeon
	 * @since 2023.09.26
	 */
	@RequestMapping(value="/admin/adminAction.do", method=RequestMethod.POST)
	@ResponseBody
	public int adminAction(SingoDaesangVo vo) {
		log.info("@@@@@@@@@@@@@@@ 관리자 신고 게시글 처리 : vo {}", vo);
		
		return service.adminAction(vo);
	}
}
