package app.util.ruleFiter.entity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import accounting.plat.dao.JdbcDao;
import app.creditapp.ln.entity.LnApplyMidParm;
import app.util.DBUtils;
import app.util.ruleFiter.type.ResultType;
import app.util.ruleFiter.type.ValidateParmType;

public class ValidateLog {
	private ResultType resultType;
	private String resultLog;
	private List<ValidateLog> resultlogList;
	
	private List<ValidateLog> warnningList;
	private List<ValidateLog> errorList;
	private String errorMessage;
	
	public ValidateLog() {
		super();
	}
	
	public ValidateLog(ResultType resultType, String resultLog) {
		super();
		this.resultType = resultType;
		this.resultLog = resultLog;
	}

	public ResultType getResultType() {
		return resultType;
	}
	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}
	public String getResultLog() {
		return resultLog;
	}
	public void setResultLog(String resultLog) {
		this.resultLog = resultLog;
	}
	public List<ValidateLog> getResultlogList() {
		return resultlogList;
	}
	public void setResultlogList(List<ValidateLog> resultlogList) {
		this.resultlogList = resultlogList;
	}
	
	public List<ValidateLog> getWarnningList() {
		return warnningList;
	}

	public List<ValidateLog> getErrorList() {
		return errorList;
	}

	/**
	 * 显示日志中存在的校验日志信息。
	 * @param isShow 如果为真，则会在控制台将校验错误和警告信息分别显示。如果为假，则不会显示错误和警告信息列表
	 */
	public void showValidateResult(boolean isShow){
		warnningList= new ArrayList<ValidateLog>();
		errorList = new ArrayList<ValidateLog>();
		for(ValidateLog vl:resultlogList){
			switch (vl.resultType) {
			case SUCCESS:
				break;
			case WARNING:
				if(isShow)System.out.println("WARNING: "+vl.getResultLog());
				warnningList.add(vl);
				break;
			case ERROR:
				if(isShow)System.out.println("ERROR: "+vl.getResultLog());
				errorList.add(vl);
				errorMessage= errorMessage == null?"":errorMessage;
				errorMessage += " "+ vl.getResultLog();
				break;
			}
		}
//		if(errorList.size() == 0){
//			System.out.println("数据校验成功");
//		}
	}
	
	/**
	 * 校验的详细过程，除校验引擎方法外可以不必关心或调用此方法。校验将会针对设定的校验规则ValidateParm类进行判断，并返回错误或警告信息
	 * @param paramName 要校验的字段名
	 * @param value 校验的实际值
	 * @param vParm 校验规则的详细内容
	 * @return 校验结果日志列表
	 * @throws  
	 * @throws AccountingException 
	 */
	public static List<ValidateLog> validateService(String paramName,String value,ValidateParm vParm){
		LnApplyMidParm lnApplyMidParm = null;
		Connection conn = DBUtils.getConn();
		try {
			lnApplyMidParm = (LnApplyMidParm)JdbcDao.query(new LnApplyMidParm(), "field_name='"+paramName+"'", "ln_apply_mid_parm", conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(conn);
		}
		if (lnApplyMidParm != null)paramName = lnApplyMidParm.getNoteName();
		List<ValidateLog> logList = new ArrayList<ValidateLog>();
		if(!vParm.isCanNull()){
			if(value==null || value.isEmpty()){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.isNull),paramName + "字段不得为空"));
			}
		}
		
		if(vParm.isCanNull() && (value == null || "".equals(value)))return logList;
		
		if(vParm.getMaxLength()!=null){
			if(value.length()>vParm.getMaxLength()){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.maxLength),paramName + "字段值长度不应大于"+vParm.getMaxLength()));
			}
		}
		
		if(vParm.getMinLength()!=null){
			if(value.length()<vParm.getMinLength()){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.minLength),paramName + "字段值长度不应小于"+vParm.getMinLength()));
			}
		}
		
		if(vParm.getMaxValue()!=null){
			if(isNumber(value) && Double.valueOf(value)>vParm.getMaxValue()){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.maxValue),paramName + "字段值不应大于"+vParm.getMaxValue()));
			}
		}
		
		if(vParm.getMinValue()!=null){
			if(isNumber(value) && Double.valueOf(value) < vParm.getMinValue()){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.minValue),paramName + "字段值不应小于"+vParm.getMinValue()));
			}
		}
		if(vParm.getComplexNumber()!=null){
			boolean isValidate = true;
			isValidate = isNumber(value);
			if(isValidate){
				String[] formartPart = vParm.getComplexNumber().split(",");
				if(value.indexOf(".") == -1){
					if(value.length() > Integer.valueOf(formartPart[0]) )isValidate = false;
				}else{
					String[] valuePart = value.split("\\.");
					//所有数的长度不得超过第一个长度 || 小数部分超过第二个长度
					if(valuePart.length != 2)isValidate = false;
					else if((valuePart[0].length() + valuePart[1].length()) > Integer.valueOf(formartPart[0]) || valuePart[1].length() > Integer.valueOf(formartPart[1]))isValidate = false;
//					if(valuePart.length == 2 &&  valuePart[0].length()> Integer.valueOf(formartPart[0]) || valuePart[1].length() > Integer.valueOf(formartPart[1]) )
				}
			}
			if(!isValidate)logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.minValue),paramName + "字段值精度不符合规定格式："+vParm.getComplexNumber()));
		}
		
		if(vParm.getStartStr()!=null){
			if(!value.startsWith(vParm.getStartStr())){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.startStr),paramName + "字段应以"+vParm.getStartStr()+"作为起始字符"));
			}
		}
		
		if(vParm.getEndStr()!=null){
			if(!value.endsWith(vParm.getEndStr())){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.endStr),paramName + "字段应以"+vParm.getEndStr()+"作为结束字符"));
			}
		}
		
		if(vParm.getContainStr()!=null){
			if(value.indexOf(vParm.getContainStr())==-1){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.containStr),paramName + "字段应包含'"+vParm.getContainStr()+"'字符"));
			}
		}
		
		if(vParm.isOnlyNumber()){
			if(!isNumber(value)){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.onlyNumber),paramName + "字段应只能是数字，不能出现其他字符"));
			}
		}
		
		if(vParm.getRegexpType()!=null){
			if(!vParm.getRegexpType().validate(value)){
				logList.add(new ValidateLog(getResultLevel(vParm,ValidateParmType.regexpType),paramName + "为"+vParm.getRegexpType().getZnName()+",格式不正确"));
			}
		}
//		System.out.println("本条记录存在"+logList.size()+"条错误信息");
		return logList;
	}
	
	private static boolean isNumber(String value){
		if(value == null || value.isEmpty())return false;
		return value.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	private static ResultType getResultLevel(ValidateParm vParm,ValidateParmType type){
		if(vParm.getWarningTypeList()==null || vParm.getWarningTypeList().size()==0)return ResultType.ERROR;
		if(vParm.getWarningTypeList().contains(type))return ResultType.WARNING;
		return ResultType.ERROR;	
	}
	
	/**
	 * 执行完成校验后返回是否成功
	 * @return
	 */
	public boolean isSuccess() {
		if(null == this.getErrorList() || this.getErrorList().size()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 执行完成校验后返回校验统计结果
	 * @return
	 */
	public String getResult() {
		String rlt = "";
		rlt += "错误数："+this.getErrorList().size();
		rlt += " 警告数："+this.getWarnningList().size();
		return rlt;
	}
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
