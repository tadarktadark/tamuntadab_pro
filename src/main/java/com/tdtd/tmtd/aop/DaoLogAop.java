package com.tdtd.tmtd.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoLogAop {
	@Pointcut("execution(public * com.tdtd.tmtd.model.mapper.*Dao*.*(..))")
	public void daoLoggerPointCut() {
		
	}
	
	@Around("daoLoggerPointCut()")
	public Object around(ProceedingJoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.info("===============> daoAround");
		String methodName = j.getSignature().toShortString();
		Object[] objs = j.getArgs();
		
		if(objs!=null) {
			logger.info("=============== {} args ===============", methodName);
			
			for(int i=0; i<objs.length; i++) {
				logger.info((i+1)+"번째 arg :\t"+String.valueOf(objs[i]));
			}
			logger.info("=============== {} args ===============", methodName);
		}
		
		try {
			logger.info("=============== {} return ===============", methodName);
			Object result = j.proceed();
			logger.info(result.toString());
			logger.info("<=============== DaoLog 종료");			
			return result;
		} catch (Throwable e) {
			logger.error("===============> Around ERROR : {}",e.getMessage());
			logger.info("<=============== DaoLog 종료");			
			return null;
		}
		
	}
	
	@AfterThrowing(value="daoLoggerPointCut()", throwing = "exception")
	public void afterThrowing(JoinPoint j, Exception exception) {
		Logger logger = LoggerFactory.getLogger(j.getTarget()+"");
		logger.warn("===============> ERROR :\t{}",j.getSignature().getName());
		logger.warn("===============> MSG :\t{}",exception.getMessage());
	}
}
