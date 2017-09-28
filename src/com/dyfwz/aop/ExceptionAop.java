package com.dyfwz.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by CoderQiang on 2017/9/28.
 */
@Aspect
@Component
public class ExceptionAop {

    @AfterThrowing(value="execution(* com.dyfwz.service.*.*(..)))",
            throwing="e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("aop捕获异常");
        System.out.println("The method " + methodName + " occurs excetion:" + e);
    }
}
