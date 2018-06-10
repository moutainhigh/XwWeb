package app.util.syslog.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import app.util.syslog.bo.SysExceptionBo;

public class ExceptionHandler implements ThrowsAdvice {
	private SysExceptionBo sysExceptionBo;
	final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public void afterThrowing(Exception e) {
		System.out.println("=========================================");
		System.out.println("=========spring²¶×½µ½Òì³£ÁË£¡£¡£¡£¡============");
		System.out.println("=========================================");
		logger.error(getTraceInfo(e));
		sysExceptionBo.insertNewExceptionLog(e);
	}
	
	private String getTraceInfo(Exception exception) {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = exception.getStackTrace();
		sb.append("class: ").append(stacks[1].getClassName())
				.append("; method: ").append(stacks[1].getMethodName())
				.append("; number: ").append(stacks[1].getLineNumber());
		return sb.toString();
	}

	public void setSysExceptionBo(SysExceptionBo sysExceptionBo) {
		this.sysExceptionBo = sysExceptionBo;
	}
}
