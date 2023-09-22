package com.tdtd.tmtd.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import com.tdtd.tmtd.model.service.IAdminService;

import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 페이지 접속시 IP확인하는 Interceptor
 * @author 임정운
 *
 */
@Slf4j
public class AdminInterceptor implements AsyncHandlerInterceptor {
	@Autowired
	private IAdminService adminService;
	
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			int n = adminService.checkIP(request.getRemoteAddr().toString().trim());
			if(n==0) {
				response.sendRedirect("../");
				return false;
			}
			return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}
}