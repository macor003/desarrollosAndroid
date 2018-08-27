package com.grid.ventas.cr.refundrestserver.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by epauser on 14/04/16.
 */
// @Component
// @Aspect
public class BenchmarkingAspect {

    private static Logger logger = LoggerFactory.getLogger(BenchmarkingAspect.class);

    // @Around("execution(* com.grid.ventas.cr.refundrestserver.service..*.*(..))")
    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long time = System.currentTimeMillis();
        Object value = proceedingJoinPoint.proceed();

        logger.debug(proceedingJoinPoint.toShortString() + " completed in " + (System.currentTimeMillis() - time)
                + " ms.");
        return value;
    }

    /*
     * @Pointcut("execution(* com.grid.ventas.cr.refundrestserver.service..*.*(..))")
     * public void whatIWantToMatch(){}
     * @Pointcut("execution(* com.grid.ventas.cr.refundrestserver.service..*.*(..))")
     * public void whatIDontWantToMatch(){}
     */
}
