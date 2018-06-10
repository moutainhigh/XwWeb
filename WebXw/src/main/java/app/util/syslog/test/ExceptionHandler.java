package app.util.syslog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import app.util.syslog.bo.SysExceptionBo;
import app.util.syslog.log.SysLogger;

public class ExceptionHandler implements ThrowsAdvice {
	private SysExceptionBo sysExceptionBo;
	final Logger logger = LoggerFactory.getLogger(TestS4j.class);

	public void afterThrowing(Exception e) {
		logger.error(getTraceInfo(e));
		sysExceptionBo.insertNewExceptionLog(e);
	}
	
	private String getTraceInfo(Exception exception) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = exception.getStackTrace();
		sb.append("class: ").append(stacks[0].getClassName())
				.append("; method: ").append(stacks[0].getMethodName())
				.append("; number: ").append(stacks[0].getLineNumber());
		return sb.toString();
	}

	public void setSysExceptionBo(SysExceptionBo sysExceptionBo) {
		this.sysExceptionBo = sysExceptionBo;
	}
}
