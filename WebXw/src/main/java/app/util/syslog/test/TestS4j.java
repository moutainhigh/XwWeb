package app.util.syslog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestS4j {
	final Logger logger = LoggerFactory.getLogger(TestS4j.class);

	Integer t;
	Integer oldT;
	
	public void setTemperature(Integer temperature) {
		oldT = t;
		t = temperature;
		logger.error(" Temperature set to {}. Old temperature was {}. ", t,
				oldT);
		if (temperature.intValue() > 50) {
			logger.info(" Temperature has risen above 50 degrees. ");
		}
	}
	
	/*
	 * eles[0] Thread.currentThread();
		eles[1] A.log();
		eles[2] 调用A.log()的方法，即我要在日志中打印出来的方法名。
	 */
	public void log(){
        StackTraceElement[] eles = Thread.currentThread().getStackTrace();
        String methodName = eles[2].getMethodName();//在这里获取 调用 A.log()方法的方法名
        String methodName1 = eles[1].getMethodName();
        System.out.println("line1: " + new Throwable().getStackTrace()[0].getLineNumber());
        System.out.println("line2: " + getLineInfo());
        System.out.println("line3: " + getTraceInfo());
        logger.info(methodName );
        logger.info(methodName1 );
    }
	
	public static String getLineInfo() {
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		return ste.getFileName() + ": Line " + ste.getLineNumber();
	}
	
	public static String getTraceInfo() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		int stacksLen = stacks.length;
		sb.append("class: ").append(stacks[1].getClassName())
				.append("; method: ").append(stacks[1].getMethodName())
				.append("; number: ").append(stacks[1].getLineNumber());
		return sb.toString();
	}
	
	public static String getTraceInfo(Throwable tab) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = tab.getStackTrace();
		int stacksLen = stacks.length;
		System.out.println(stacksLen);
		for(StackTraceElement se : stacks){
			System.out.println(se.getClassName());
			System.out.println(se.getMethodName());
			System.out.println(se.getLineNumber());
		}
		sb.append("class: ").append(stacks[1].getClassName())
				.append("; method: ").append(stacks[1].getMethodName())
				.append("; number: ").append(stacks[1].getLineNumber());
		return sb.toString();
	}
	
	public void testErrorMethod(){
		try {
			int a = 100/0;
			String b = null;
			System.out.println(b.length());
		} catch (Exception e) {
			System.out.println(getTraceInfo(e.fillInStackTrace()));
			System.out.println(e.getMessage());
			System.out.println(e.toString());
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			System.out.println(e.getCause());
//			System.out.println(e.getLocalizedMessage());
//			System.out.println(e.getClass().getName());
//			System.out.println(e.toString());
//			System.out.println(getTraceInfo());
//			System.out.println(e.fillInStackTrace().);
		}
	}

	public static void main(String[] args) {
		TestS4j wombat = new TestS4j();
//		wombat.setTemperature(1);
//		wombat.setTemperature(55);
		
//		wombat.log();
		wombat.testErrorMethod();
	}
}
