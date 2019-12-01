package aop;

import domain.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class LogAop {
    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletRequest request;

    private Date visitTime;

    @Before("execution(* controller.*.*(..))")
    public void before(){
        visitTime = new Date();
    }

    @AfterReturning("execution(* controller.*.*(..))")
    public void afterReturning(JoinPoint joinPoint){
        // 访问人，从spring security中取
        SecurityContext context = SecurityContextHolder.getContext(); // 获取到Security容器
        User user = (User) context.getAuthentication().getPrincipal();// 获取Security存的User对象
        String username = user.getUsername(); // 用户名
        
        String ip = request.getRemoteAddr(); // 获取IP
        String url = request.getRequestURI();// 访问路径
        Long time = new Date().getTime()-visitTime.getTime();// 访问时长，单位：毫秒
        String methodName = joinPoint.getSignature().getName();// 访问方法

        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setVisitTime(visitTime);
        log.setUsername(username);
        log.setIp(ip);
        log.setUrl(url);
        log.setExecutionTime(time);
        log.setMethod(methodName);

        logService.add(log);
    }

}
