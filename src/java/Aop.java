/**
 * 
 */
package com.aspect;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 功能说明:
 * 
 * @title 
 *
 * @author aaaa
 * 
 *
 *
 */
@Component    //首先初始化切面类
@Aspect      //声明为切面类，底层使用动态代理实现AOP
public class AppAspectAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AppAspectAdvice.class);
	
	@Pointcut("execution(* com.front.control.*.*(..)) || execution(* com.fullview.front.control.*.*(..))")
    public void anyMethod() {
    }
	
	// 环绕通知（##环绕通知的方法中一定要有ProceedingJoinPoint 参数,与
    //Filter中的  doFilter方法类似）
	@Around("execution(* com.front.control.*.*(..)) || execution(* com.fullview.front.control.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long begin = new Date().getTime();
        // 调用方法的参数
        Object[] args = pjp.getArgs();
        // 调用的方法名
        String method = pjp.getSignature().getName();
        // 获取目标对象(形如：com.action.admin.LoginAction@1a2467a)
        Object target = pjp.getTarget();
       //获取目标对象的类名(形如：com.action.admin.LoginAction)
        String targetName = pjp.getTarget().getClass().getName();
        logger.info("\r\n开始执行类("+targetName+")的方法("+method+"),开始时间为"+DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        Object result = pjp.proceed();//result的值就是被拦截方法的返回值
        logger.info("\r\n用时："+(new Date().getTime() - begin)+" ms,"+"结束执行类("+targetName+")的方法("+method+"),结束时间为"+DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //System.out.println("输出：" + args[0] + ";" + method + ";" + target + ";" + result + "\n");
      return result;
    }

    // 异常通知
   
   @AfterThrowing(value = "execution(* aop.annotation.*.*(..))", throwing = "e")
    public void doThrow(JoinPoint jp, Throwable e) {
        System.out.println("出错啦");
    }
}
