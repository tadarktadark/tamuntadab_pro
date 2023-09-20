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
 * 로그인 후 해당 계정이 실시간 정지 상태 확인을 위한 Interceptor
 * @author 임정운
 *
 */
@Slf4j
public class mainInterceptor implements AsyncHandlerInterceptor {
	@Autowired
	private ICommUserService commUserService;
	
}