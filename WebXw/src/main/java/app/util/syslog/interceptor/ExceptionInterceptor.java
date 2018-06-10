package app.util.syslog.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import app.util.syslog.bo.SysExceptionBo;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

public class ExceptionInterceptor extends ExceptionMappingInterceptor {
	
	private SysExceptionBo sysExceptionBo;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2677486487723419385L;

	@Override
	public String intercept(ActionInvocation actioninvocation) throws Exception {
		ActionSupport  as = (ActionSupport)actioninvocation.getAction();
		String result = null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		try {
			result = actioninvocation.invoke();
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				RuntimeException re = (RuntimeException) e;
				String userId = (String)session.getAttribute("userId");
				sysExceptionBo.insertNewExceptionLog(re,userId);
			}else{
				sysExceptionBo.insertNewExceptionLog(e);
			}
			result = getErrReturnAction(e,as);
		}
		return result;
	}
	
	private String getErrReturnAction(Exception e,ActionSupport  as){
//		e.printStackTrace();
		if(e instanceof java.lang.NullPointerException){
			as.addActionMessage("空指针异常");
		}else if(e instanceof org.springframework.transaction.CannotCreateTransactionException){
			as.addActionMessage("网络异常，请稍后在试");
		}else{
			as.addActionMessage(e.getMessage());
		}
		// // 把自定义错误信息
		// HttpServletRequest request = (HttpServletRequest)
		// actioninvocation
		// .getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		// request.setAttribute("errorMsg", errorMsg);
		// final Logger logger = LoggerFactory.getLogger(TestS4j.class);
		// logger.error(errorMsg, e);
		// // return "errorMsg";
		return "commonException";
	}

	public void setSysExceptionBo(SysExceptionBo sysExceptionBo) {
		this.sysExceptionBo = sysExceptionBo;
	}


}
