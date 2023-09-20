package com.tdtd.tmtd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tdtd.tmtd.model.service.IReviewService;
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
	public String reviewWriteForm(Model model) {
		log.info("ReviewController reviewWriteForm.do 후기 작성 페이지 이동");
		
		model.addAttribute("title", "후기");
		model.addAttribute("pageTitle", "후기 작성");
		
		return "reviewWriteForm";
	}
	
	@GetMapping("/reviewButton.do")
	public String reviewButton() {
		return "reviewButton";
	}
	
}
