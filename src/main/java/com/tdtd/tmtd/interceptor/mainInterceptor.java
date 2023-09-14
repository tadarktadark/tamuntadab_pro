package com.tdtd.tmtd.interceptor;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tdtd.tmtd.model.service.ICommUserService;
import com.tdtd.tmtd.vo.UserProfileVo;

import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 후 해당 계정이 정지 상태 / 휴면 상태를 체크하여 해당하면 로그아웃 시키는 기능
 * @author 임정운
 *
 */
public class mainInterceptor implements AsyncHandlerInterceptor {
	@Autowired
	private ICommUserService commUserService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(request.getSession().getAttribute("userInfo") != null) {
			UserProfileVo userInfo = (UserProfileVo)request.getSession().getAttribute("userInfo");
			if(!((userInfo.getUserDelflag()).trim().equals("N"))) {
				modelAndView.setViewName("redirect:/human.do");
				return;
			}else if(commUserService.searchJeongJi(userInfo)!=0) {
				modelAndView.setViewName("redirect:/jeongji.do");
				return;
			}
		}
		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}