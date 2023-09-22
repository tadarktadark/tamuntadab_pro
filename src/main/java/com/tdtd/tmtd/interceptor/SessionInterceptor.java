package com.tdtd.tmtd.interceptor;

import java.io.PrintWriter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tdtd.tmtd.vo.UserProfileVo;

public class SessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			UserProfileVo userInfo = (UserProfileVo) request.getSession().getAttribute("userInfo");
			if(userInfo==null) {
				request.getSession().invalidate();
				response.getWriter().print("<script>alert('로그인 후 사용가능합니다.');</script>");
				response.getWriter().flush();
				response.sendRedirect("./main.do");
		        return false;
			}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
