package com.tdtd.tmtd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import com.tdtd.tmtd.vo.AdminVo;

import lombok.extern.slf4j.Slf4j;

public class AdminAfterLoginInterceptor implements AsyncHandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AdminVo adminInfo = (AdminVo) request.getSession().getAttribute("adminInfo");
		if(adminInfo == null) {
			request.getSession().invalidate();
			response.sendRedirect("./admin.do");
			return false;
		}
		return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}
}
