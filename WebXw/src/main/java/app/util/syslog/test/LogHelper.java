package app.util.syslog.test;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class LogHelper implements MethodBeforeAdvice, AfterReturningAdvice{

	@Override
	public void afterReturning(Object obj, Method method, Object[] objs, Object obj2) throws Throwable {
		System.out.println("进入了切面编程：AfterReturningAdvice");
	}

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		System.out.println("在执行BO以前，这里先进入Before方法");
		
	}

}
