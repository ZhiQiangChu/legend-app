package cn.com.dplus.legend.aop;

import cn.com.dplus.project.entity.ResponseEntity;
import cn.com.dplus.project.utils.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午2:22 17-7-4
 * @Modified By:
 */
@Component
@Aspect
public class WebLogAspect {

    @Pointcut("execution(public * cn.com.dplus.legend.controller..*.*(..))")
    public void webLog() {
    }

    @Pointcut("@annotation(cn.com.dplus.legend.aop.WebLog)")
    public void webLogAnnotaion() {
    }

//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        LogUtil.Info("URL : " + request.getRequestURL().toString());
//        LogUtil.Info("HTTP METHOD : " + request.getMethod());
//        LogUtil.Info("IP : " + request.getRemoteAddr());
//        LogUtil.Info("CLASS METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        LogUtil.Info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        LogUtil.Info("RESPONSE : " + ret);
//    }

    @Around("webLogAnnotaion()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LogUtil.Info("URL : " + request.getRequestURL().toString());
        LogUtil.Info("HTTP METHOD : " + request.getMethod());
        LogUtil.Info("IP : " + request.getRemoteAddr());
        Object ret = proceedingJoinPoint.proceed();
        LogUtil.Info("RESPONSE : " + ret);
    }
}
