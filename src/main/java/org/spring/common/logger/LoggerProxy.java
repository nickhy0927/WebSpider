package org.spring.common.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggerProxy {

	public void before(JoinPoint jp) {
		String name = jp.getSignature().getName();
		System.out.println("调用了" + name + "方法");
	}

	public Object around(ProceedingJoinPoint pjp) {
		Object object = null;
		System.out.println("程序调用" + pjp.getSignature().getName() + "方法执行开始...");
		try {
			object = pjp.proceed();// 程序继续执行
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("程序调用" + pjp.getSignature().getName() + "方法执行结束...");
		return object;
	}
}
