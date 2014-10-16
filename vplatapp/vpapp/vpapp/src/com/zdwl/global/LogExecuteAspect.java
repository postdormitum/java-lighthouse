package com.zdwl.global;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 记录每个操作执行的时间
 * @author dbw
 *
 */
public class LogExecuteAspect {
	
	private static final Logger logger = Logger.getLogger(LogExecuteAspect.class);
	
	public void doAfter(JoinPoint jp){
		
	}
	
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable { 
		System.out.println(pjp.getTarget().getClass().getName()+"."+pjp.getSignature().getName()+"开始执行+++++++++++++++++++++");
		logger.info(pjp.getTarget().getClass().getName()+"."+pjp.getSignature().getName()+"开始执行+++++++++++++++++++++");
		
		long time = System.currentTimeMillis();  
        Object retVal = pjp.proceed();  
        time = System.currentTimeMillis() - time;  
        System.out.println(pjp.getTarget().getClass().getName()+"."+pjp.getSignature().getName()+"执行花费时间: " + time + " ms");  
        logger.info(pjp.getTarget().getClass().getName()+"."+pjp.getSignature().getName()+"执行花费时间: " + time + " ms");
        
        System.out.println(pjp.getTarget().getClass().getName()+"."+pjp.getSignature().getName()+"开始完毕+++++++++++++++++++++");
		logger.info(pjp.getTarget().getClass().getName()+"."+pjp.getSignature().getName()+"执行完毕+++++++++++++++++++++");
        return retVal;
	}
	
	public void doBefore(JoinPoint jp){
		
	}

}
