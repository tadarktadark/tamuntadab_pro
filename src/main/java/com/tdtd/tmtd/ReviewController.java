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

@Controller
@Slf4j
public class ReviewController {

	@Autowired
	private IReviewService service;
	
	//내 후기 리스트 조회 페이지 이동
	@GetMapping("/myReviewList.do")
	public String myReviewList(Model model, HttpSession session) {
		log.info("ReviewController myReviewList.do 내 후기 리스트 조회 페이지 이동");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String userAccountId = userInfo.getUserAccountId();
		
		List<ReviewVo> lists = service.getMyReview(new HashMap<String, Object>(){{
								put("userAccountId", userAccountId); 
								put("start", 1); 
								put("end", 5);
							}});
		
		model.addAttribute("title", "후기");
		model.addAttribute("pageTitle", "내 작성 후기");
		model.addAttribute("lists", lists);
		return "myReviewList";
	}
	
	//후기 작성 페이지 이동
	@GetMapping("/reviewWriteForm.do")
	public String reviewWriteForm(Model model, HttpSession session, String classId) {
		log.info("ReviewController reviewWriteForm.do 후기 작성 페이지 이동");
		
		UserProfileVo userInfo = (UserProfileVo)session.getAttribute("userInfo");
		String userNickname = userInfo.getUserNickname();
		
		model.addAttribute("title", "후기");
		model.addAttribute("pageTitle", "후기 작성");
		model.addAttribute("reviStudName", userNickname);
		model.addAttribute("reviClasId", classId);
		
		return "reviewWriteForm";
	}
	
	@GetMapping("/reviewButton.do")
	public String reviewButton(Model model, String classId) {
		model.addAttribute("classId", classId);
		return "reviewButton";
	}
	
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
	
	//리뷰 스크롤 api 실행
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
	
	//리뷰 삭제 및 참여자 status update
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
