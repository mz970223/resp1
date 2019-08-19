package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Scope("prototype")
@Aspect
public class SysLogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;
    private Date startTime=new Date();//开始的时间

    //前置通知 主要获取开始时间
    public void doBefore(){
        startTime= new Date();
    }

    //后置通知
    public void doAfter(JoinPoint jp){
        //获取执行结束的时间
        Date endTime=new Date();
        //获取当前访问用户
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //获取用户的远程地址
        String addr = request.getRemoteAddr();
        //获取请求资源的url
        String uri = request.getRequestURI();
        //获取方法的执行时间
        long mills = endTime.getTime() - startTime.getTime();
        String methodName = jp.getSignature().getName();

        //将搜集到的信息封装到对象中
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(startTime);
        sysLog.setUsername(username);
        sysLog.setIp(addr);
        sysLog.setUrl(uri);
        sysLog.setExecutionTime(mills);
        sysLog.setMethod(methodName);

        sysLogService.add(sysLog);
    }
}
