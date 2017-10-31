package cn.com.dplus.legend.aop;

import cn.com.dplus.project.utils.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:48 17-8-25
 * @Modified By:
 */
@Component
@Aspect
public class EventAspect {


    @Pointcut("execution(* cn.com.dplus.legend..*.*(..)) && @annotation(event)")
    public void pointcut(Event event) {

    }

    //    @Around("execution(* cn.com.dplus.legend..*.*(..)) && @annotation(event)")
    @Around("pointcut(event)")
    public Object process(ProceedingJoinPoint proceedingJoinPoint, Event event) throws Throwable {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
////        LogUtil.Info("URL : " + request.getRequestURL().toString());
////        LogUtil.Info("HTTP METHOD : " + request.getMethod());
////        LogUtil.Info("IP : " + request.getRemoteAddr());
        Object ret = null;
        try {
            ret = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            LogUtil.Console("hahah");
        }
        LogUtil.Console("开始处理事件...");
        String service = event.service();
        String resource = event.resource();
        String action = event.action();
        LogUtil.Console("service:" + service + ",resource" + resource + ",action" + action);
        LogUtil.Console("处理事件成功!");
        return ret;
    }
}
