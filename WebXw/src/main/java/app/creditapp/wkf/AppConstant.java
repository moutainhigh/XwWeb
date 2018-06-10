package app.creditapp.wkf;

public class AppConstant {
	
	public static final String PARAM_PROCESSDEF_NAME = "processDefName";
	public static final String PARAM_PROCESSDEF_KEY = "processDefKey";
	
	public static final String END_STATE = "ended";
	public static final String PARAM_MSG = "AppMsg";
	/**意见类型 START**/
	public static final String OPINION_TYPE_ARREE="01";//同意
	public static final String OPINION_TYPE_REFUSE="02";//否决
	public static final String OPINION_TYPE_ROLLBACK="3";//退回
	public static final String OPINION_TYPE_RESTART="4";//发回重审
	public static final String OPINION_TYPE_DISARREE="5";//不同意
	/**意见类型 END**/
	
	/**默认审批意见 START**/
	public static final String DEFAULT_APPROVAL_OPINOIN = "同意";
	/**默认审批意见 END**/
	
}
