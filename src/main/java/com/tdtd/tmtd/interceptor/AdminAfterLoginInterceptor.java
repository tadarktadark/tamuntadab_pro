package com.tdtd.tmtd.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import com.tdtd.tmtd.vo.AdminVo;

public class AdminAfterLoginInterceptor implements AsyncHandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AdminVo adminInfo = (AdminVo) request.getSession().getAttribute("adminInfo");
		if(adminInfo == null) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('다시 로그인 해주세요.');location.href='./admin.do';</script>");
			return false;
		}
		return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}
}
