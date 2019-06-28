package com.example.studyspringmvc2.aspect;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ch.qos.logback.classic.Logger;

import java.lang.reflect.Method;

/**
 * 切面类
 */
@Aspect
@Component
public class RequestAspect {

    private Logger logger= (Logger) LoggerFactory.getLogger(RequestAspect.class);

    /**
     * Pointcut定义切点
     * public修饰符的   返回值任意  com.cy.controller包下面的任意类的任意方法任意参数
     */
    @Pointcut("execution(public * com.example.studyspringmvc2.web.*.*(..))")
    public void log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("方法执行前...");
        ServletRequestAttributes sra =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.info("url: " + request.getRequestURI());//url
        logger.info("ip: " + request.getRemoteHost());//ip
        logger.info("header: ak :" + request.getHeader("ak"));
        logger.info("method: "+request.getMethod());      //post or get? or ?
        logger.info("class.method: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("args: "+joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("方法执行后...");
    }

    @AfterReturning(returning="result", pointcut="log()")
    public void doAfterReturnint(Object result){
        logger.info("方法返回值：" + result);
    }

    @Around("log()")
    public Object signVerification(ProceedingJoinPoint pjp) throws Throwable{

        //正在被通知的方法相关信息
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获取被拦截的方法
        Method method = signature.getMethod();
        //获取被拦截的方法名
        String methodName = method.getName();

        // ---------
        ServletRequestAttributes sra =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        logger.info("method: "+request.getMethod());      //post or get? or ?
        logger.info("---header: ak :" + request.getHeader("ak"));


        if (request.getHeader("ak").equals("123"))//已经登录
        {
            return pjp.proceed();//继续执行被拦截的方法
        }
        else {//未登录
            //构建JSON
            String response = "{\"signin\":0}";
            return response;
        }
    }
}
