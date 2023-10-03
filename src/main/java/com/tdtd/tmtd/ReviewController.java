package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdtd.tmtd.model.service.IReviewService;
import com.tdtd.tmtd.vo.ClassVo;
import com.tdtd.tmtd.vo.ReviewVo;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 후기 관련 컨트롤러
 * @author 문희애
 *
 */
@Controller
@Slf4j
public class ReviewController {

	@Autowired
	private IReviewService service;
	
	/**
	 * 후기 작성 페이지 이동
	 * @param session 로그인 정보
	 * @param classId 클래스 아이디
	 * @return title, pageTitle 페이지 제목 / reviStudName 후기 작성 닉네임 / reviAccountId 후기 작성 아이디 /reviClasId 후기 ref(클래스) 아이디
	 */
	@GetMapping("/reviewWriteForm.do")
	public String reviewWriteForm(Model model, HttpSession session, String classId) {
		log.info("ReviewController reviewWriteForm.do 후기 작성 페이지 이동");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String userNickname = userInfo.getUserNickname();
		String userAccountId = userInfo.getUserAccountId();
		
		model.addAttribute("title", "후기");
		model.addAttribute("pageTitle", "후기 작성");
		model.addAttribute("reviStudName", userNickname);
		model.addAttribute("reviAccountId", userAccountId);
		model.addAttribute("reviClasId", classId);
		
		return "reviewWriteForm";
	}
	
	/**
	 * 후기 등록 및 참여자 status 업데이트
	 * @param rVo 후기 VO
	 * @param session 로그인 정보
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@PostMapping(value = "/insertReview.do")
	@ResponseBody
	public Map<String, Object> insertReview(@ModelAttribute ReviewVo rVo, HttpSession session){
		log.info("insertReview 받아온 값 : {}", rVo);
		
		Map<String, Object> response = new HashMap<>();
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String userAccountId = userInfo.getUserAccountId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAccountId", userAccountId);
		map.put("reviClasId", rVo.getReviClasId());
		
		int n = service.insertReview(rVo, map);
		
		if(n>0) {
			response.put("successMessage", "후기가 등록되었습니다");
		} else {
			response.put("errorMessage", "정상 처리가 되지 않았습니다");
		}
		return response;
	}
	
	/**
	 * 마이페이지 - 후기 리스트 스크롤 api 실행
	 * @param map userAccountId 유저 아이디 / start 시작 행 번호 / end 끝 행 번호
	 * @return lists 후기 리스트 / hasMore 마지막 페이지 여부
	 */
	@GetMapping("/getMoreReview.do")
	@ResponseBody
	public Map<String, Object> instrReview(@RequestParam Map<String, Object> map){
		log.info("instrReviewDetail.do 받아온 map : {}",map);
		
		List<ReviewVo> lists = service.getMyReview(map);
		
		int end = Integer.parseInt(map.get("end").toString());
		int totalCount = service.myReviewTotalCount(map.get("userAccountId").toString());
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("lists", lists);
	    response.put("hasMore", end < totalCount);
	    
		return response;
	}
	
	/**
	 * 후기 삭제 및 참여자 status update
	 * 
	 * @param map seq 후기 seq / clchId 참여자 아이디
	 * @return successMessage 성공시 반환 메세지 / errorMessage 에러시 반환 메세지
	 */
	@GetMapping("/deleteReview.do")
	@ResponseBody
	public Map<String, Object> deleteReview(@RequestParam Map<String, Object> map){
		log.info("deleteReview 받아온 값 : {}", map);
		
		Map<String, Object> response = new HashMap<>();
		
		String seq = (String) map.get("seq");
		String clchId = (String) map.get("clchId");
		
		int n = service.deleteReview(seq, clchId);
		
		if(n>0) {
			response.put("successMessage", "삭제가 완료 되었습니다");
		} else {
			response.put("errorMessage", "정상 처리가 되지 않았습니다");
		}
		return response;
	}
}
