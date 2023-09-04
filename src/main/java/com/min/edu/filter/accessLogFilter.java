package com.min.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class accessLogFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("===============> Client 요청 정보 시작");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
	    String ip = req.getHeader("X-Forwarded-For");
	    log.info("===============> X-FORWARDED-FOR : " + StringUtils.defaultIfEmpty(ip, "-"));

	    if (ip == null) {
	        ip = req.getHeader("Proxy-Client-IP");
	        log.info("===============> Proxy-Client-IP : " + StringUtils.defaultIfEmpty(ip, "-"));
	    }
	    if (ip == null) {
	        ip = req.getHeader("WL-Proxy-Client-IP");
	        log.info("===============> WL-Proxy-Client-IP : " + StringUtils.defaultIfEmpty(ip, "-"));
	    }
	    if (ip == null) {
	        ip = req.getHeader("HTTP_CLIENT_IP");
	        log.info("===============> HTTP_CLIENT_IP : " + StringUtils.defaultIfEmpty(ip, "-"));
	    }
	    if (ip == null) {
	        ip = req.getHeader("HTTP_X_FORWARDED_FOR");
	        log.info("===============> HTTP_X_FORWARDED_FOR : " + StringUtils.defaultIfEmpty(ip, "-"));
	    }
	    if (ip == null) {
	        ip = req.getRemoteAddr();
	        log.info("===============> getRemoteAddr : " + StringUtils.defaultIfEmpty(ip, "-"));
	    }
	    
	    chain.doFilter(request, response);

	}
	
	@Override
	public void destroy() {
		log.info("<=============== Client 요청 정보 종료");
	}

}
