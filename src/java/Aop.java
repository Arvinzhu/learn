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
 * ����˵��:
 * 
 * @title 
 *
 * @author aaaa
 * 
 *
 *
 */
@Component    //���ȳ�ʼ��������
@Aspect      //����Ϊ�����࣬�ײ�ʹ�ö�̬����ʵ��AOP
public class AppAspectAdvice {
	private static final Logger logger = LoggerFactory.getLogger(AppAspectAdvice.class);
	
	@Pointcut("execution(* com.front.control.*.*(..)) || execution(* com.fullview.front.control.*.*(..))")
    public void anyMethod() {
    }
	
	// ����֪ͨ��##����֪ͨ�ķ�����һ��Ҫ��ProceedingJoinPoint ����,��
    //Filter�е�  doFilter�������ƣ�
	@Around("execution(* com.front.control.*.*(..)) || execution(* com.fullview.front.control.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long begin = new Date().getTime();
        // ���÷����Ĳ���
        Object[] args = pjp.getArgs();
        // ���õķ�����
        String method = pjp.getSignature().getName();
        // ��ȡĿ�����(���磺com.action.admin.LoginAction@1a2467a)
        Object target = pjp.getTarget();
       //��ȡĿ����������(���磺com.action.admin.LoginAction)
        String targetName = pjp.getTarget().getClass().getName();
        logger.info("\r\n��ʼִ����("+targetName+")�ķ���("+method+"),��ʼʱ��Ϊ"+DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        // ִ���귽���ķ���ֵ������proceed()�������ͻᴥ������㷽��ִ��
        Object result = pjp.proceed();//result��ֵ���Ǳ����ط����ķ���ֵ
        logger.info("\r\n��ʱ��"+(new Date().getTime() - begin)+" ms,"+"����ִ����("+targetName+")�ķ���("+method+"),����ʱ��Ϊ"+DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //System.out.println("�����" + args[0] + ";" + method + ";" + target + ";" + result + "\n");
      return result;
    }

    // �쳣֪ͨ
   
   @AfterThrowing(value = "execution(* aop.annotation.*.*(..))", throwing = "e")
    public void doThrow(JoinPoint jp, Throwable e) {
        System.out.println("������");
    }
}
