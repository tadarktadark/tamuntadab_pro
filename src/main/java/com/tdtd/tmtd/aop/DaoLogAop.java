package com.tdtd.tmtd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoLogAop {
	@Pointcut("execution(public * com.tdtd.tmtd.mapper.*Dao*.*(..))")
	public void daoLoggerPointCut() {
		
	}
	
	@Before("daoLoggerPointCut()")
	public void before(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.info("===============> DaoLog 실행");
		Object[] objs = j.getArgs();
		
		if(objs!=null) {
			logger.info("=============== {} ===============", j.getSignature().getName());
			
			for(int i=0; i<objs.length; i++) {
				logger.info(i+"번째 arg :\t"+String.valueOf(objs[i]));
			}
			logger.info("=============== {} ===============", j.getSignature().getName());
		}
	}
	
	@AfterReturning("daoLoggerPointCut()")
	public void afterReturning(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.info("<=============== DaoLog 종료");
	}
	
	@AfterThrowing(value="daoLoggerPointCut()", throwing = "exception")
	public void afterThrowing(JoinPoint j, Exception exception) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.warn("===============> ERROR :\t{}",j.getSignature().getName());
		logger.warn("===============> MSG :\t{}",exception.getMessage());
	}
}
