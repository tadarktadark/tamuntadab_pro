package com.tdtd.tmtd.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

import com.tdtd.tmtd.vo.AdminVo;

public class AdminAuthMInterceptor implements AsyncHandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		AdminVo adminInfo = (AdminVo) request.getSession().getAttribute("adminInfo");
		if(adminInfo != null) {
			if((!adminInfo.getAdprAuth().equals("M")) && (!adminInfo.getAdprAuth().equals("T"))) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한이 없습니다');location.href='./adminMain.do';</script>");
				return false;
			}else {
				return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
			}
		}else {
			response.sendRedirect("../main.do");
			return false;
		}
	}
}
