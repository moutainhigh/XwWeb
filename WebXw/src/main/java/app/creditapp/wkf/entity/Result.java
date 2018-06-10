package app.creditapp.wkf.entity;

import app.creditapp.wkf.AppConstant;

import com.dhcc.workflow.pvm.internal.util.StringUtil;

public class Result {
	
	private boolean isProcessEnd;
	
	private String errorCode;
	private String errorMsg;
	private String isPass;
	private String isBaobei;
	
	private Exception exception;
	
	public boolean isProcessEnd() {
		return isProcessEnd;
	}
	
	public void setProcessEnd(String state) {
		this.isProcessEnd = AppConstant.END_STATE.equals(state);
	}

	public boolean isSuccess() {
		return StringUtil.isEmpty(errorCode) && StringUtil.isEmpty(errorMsg) && exception == null;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public String getIsBaobei() {
		return isBaobei;
	}

	public void setIsBaobei(String isBaobei) {
		this.isBaobei = isBaobei;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	
	
	public Result(){
		
	}

	public String getIsPass() {
		return isPass;
	}

	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	
}
