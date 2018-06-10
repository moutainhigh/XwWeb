package app.creditapp.securityinterface.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.base.ServiceException;
import app.creditapp.sec.bo.AuditInfoLogBo;
import app.creditapp.sec.bo.LoginInfoSummaryBO;
import app.creditapp.sec.bo.SecurityAuditBo;
import app.creditapp.sec.bo.UserApptimeBo;
import app.creditapp.sec.bo.UserMarkInfoBo;
import app.creditapp.sec.entity.LoginPaswordStatus;
import app.creditapp.sec.entity.LoginStatus;
import app.creditapp.sec.entity.PasswordRegexp;
import app.creditapp.sec.entity.SecurityAudit;
import app.creditapp.sec.entity.UserApptime;
import app.creditapp.sec.entity.UserMarkInfo;
import app.creditapp.securityinterface.SecurityInterface;
import app.util.DateUtil;

//import com.deep.wf.bean.UserBean;



public class SecurityInterfaceImpl implements SecurityInterface {
	
	private SecurityAuditBo securityAuditBo ;
	private UserMarkInfoBo userMarkInfoBo;
	private AuditInfoLogBo auditInfoLogBo;
	private UserApptimeBo userApptimeBo;
	private LoginInfoSummaryBO logininfosummarybo;
	
	
	public SecurityAudit getById(String secId) throws ServiceException{
		return securityAuditBo.getById(secId);
	}
	public String SecurityPwd(String userId) throws ServiceException{
		try{
			String blsec="main";
			List<SecurityAudit> list= new ArrayList<SecurityAudit>();
			SecurityAudit securityAudit =new SecurityAudit();
			securityAudit.setIsUse("1");
			list =  securityAuditBo.getListForSec(securityAudit);
			for(int i=0;i<list.size();i++){
				String itemNo = list.get(i).getItemNo();
//				密码最长使用期限；
				if(itemNo.equals("7")){
					UserMarkInfo userMarkInfo = null;
					userMarkInfo = userMarkInfoBo.getById(userId);
					if(userMarkInfo!=null){
						int ItemValues = Integer.parseInt(list.get(i).getItemValues());
						if(userMarkInfo.getPasswordUpdateTime()!=null&&!userMarkInfo.getPasswordUpdateTime().equals("")){
							String update = userMarkInfo.getPasswordUpdateTime();
							update = update.substring(0,4)+"-"+update.substring(4,6)+"-"+update.substring(6,8);
							Date date = new Date();
							DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");// 设置日期显示格式
							String sysDate = df1.format(date);
							if(ItemValues< DateUtil.getBetweenDays(update,sysDate)){
								blsec="toup当前密码已经超过使用期限("+ItemValues+"天)，请更换密码：";
								return blsec;
							}
						}
					}else{//如果首次登录直接记录修改密码时间
						this.insertOrUpdate(userId,"");
						this.update(userId);
					}
				}
//				密码输入错误后的容忍次数
				if(itemNo.equals("8")){
					UserMarkInfo userMarkInfo = null; 
					userMarkInfo = userMarkInfoBo.getById(userId);
					if(userMarkInfo!=null){
						int ItemValues = Integer.parseInt(list.get(i).getItemValues());
						if(userMarkInfo.getPasswordState().equals("1")){
							blsec="toln已超过系统容错次数("+ItemValues+"次)，请与管理员联系：";
							return blsec;
						}
					}
				}
			}
			return blsec;
		}catch(Exception e){
			throw new ServiceException(e.getMessage());
		}
	}
	/** 收录用户登录退出信息 从SysLoginLog类 装入 UserMarkInfo类
	 * return
	 */
	public void insertOrUpdate(String userId,String Message) throws ServiceException{
		UserMarkInfo userMarkInfo = new UserMarkInfo();
		//装入
		userMarkInfo.setUserId(userId);//操作员号
		userMarkInfo.setPasswordMessege(Message);//main-正常登录，其他-密码校验没过
		if(Message.equals("logout")){
			userMarkInfo.setLastSignOutTime(DateUtil.getDate());//out-退出 本次退出日期
		}else{
			userMarkInfo.setLastSignInTime(DateUtil.getDate());//上次登录日期
			userMarkInfo.setCurrentSignInTime(DateUtil.getDate());//本次登录日期
		}
		userMarkInfoBo.insertOrUpdate(userMarkInfo);
	}
	public void update(String userId) throws ServiceException{
		 UserMarkInfo userMarkInfo=userMarkInfoBo.getById(userId);
		userMarkInfo.setPasswordState("0");
		userMarkInfo.setLoginErrorTimes(0);
		userMarkInfo.setPasswordUpdateTime(DateUtil.getSysDate());
		userMarkInfoBo.update(userMarkInfo);
	}
	
	public void saveLogout(String userId,String sysdate) throws ServiceException{
		UserMarkInfo userMarkInfo=userMarkInfoBo.getById(userId);
		userMarkInfo.setLastSignOutTime(sysdate);
		userMarkInfoBo.update(userMarkInfo);
	}
//	public void insertAudit(UserBean userbean,String userId,String userName) throws ServiceException {
//		try {
//			SecurityAudit securityAudit =securityAuditBo.getById("9");
//			if(securityAudit.getIsUse().equals("1")){
//				Date date = new Date();
//				DateFormat df1 = new SimpleDateFormat("yyyyMMdd");// 设置日期显示格式
//				String logoutdate = df1.format(date);
//				AuditInfoLog auditInfoLog = new AuditInfoLog();
//				int count = auditInfoLogBo.getCount(auditInfoLog);
//				auditInfoLog.setAuditTime(logoutdate);
//				auditInfoLog.setChangeUserId(userId);
//				auditInfoLog.setChangeUserName(userName);
//				auditInfoLog.setLogId(count+1);
//				auditInfoLog.setUserId(userbean.getKeyName());
//				auditInfoLog.setUserName(userbean.getDisplayName());
//				auditInfoLogBo.insert(auditInfoLog);
//			}
//		} catch (Exception e) {
//			throw new ServiceException(e.getMessage());
//		}
//	}
	/*
	public String SecurityChangePwd(String userId,String pwd) throws ServiceException{
		try{
			String blsec="";
			List<SecurityAudit> list= new ArrayList<SecurityAudit>();
			SecurityAudit securityAudit =new SecurityAudit();
			securityAudit.setIsUse("1");
			list =  securityAuditBo.getListForSec(securityAudit);
			for(int i=0;i<list.size();i++){
				String itemNo = list.get(i).getItemNo();
			//密码长度最小值，值选择：0-20位
				if(itemNo.equals("1")){
					if(pwd.length()<Integer.valueOf((list.get(i).getItemValues()))){
						blsec+="密码长度必须大于等于" +list.get(i).getItemValues()+"位：";
					}
				}
			//	必须包含英文大写字母(A 到 Z)
				if(itemNo.equals("2")){
					Pattern pattern = Pattern.compile(".*[A-Z]{1,}.*");
					Matcher m = pattern.matcher(pwd);
					if(!m.matches()){
						blsec+="密码必须包含英文“大写字母”：";
					}
				}
			//	必须包含英文小写字母(a 到 z)
				if(itemNo.equals("3")){
					Pattern pattern = Pattern.compile(".*[a-z]{1,}.*");
					Matcher m = pattern.matcher(pwd);
					if(!m.matches()){
						blsec+="密码必须包含英文“小写字母”：";
					}
				}
			//	必须包含10 个基本数字(0 到 9)
				if(itemNo.equals("4")){
					Pattern pattern = Pattern.compile(".*[0-9]{1,}.*");
					Matcher m = pattern.matcher(pwd);
					if(!m.matches()){
						blsec+="密码必须包含“数字”：";
					}
				}
//				必须包含特殊字符(!、@、$、*、.)
				if(itemNo.equals("5")){
					Pattern pattern = Pattern.compile(".*[!@$*.]{1,}.*");
					Matcher m = pattern.matcher(pwd);
					if(!m.matches()){
						blsec+="密码必须包含“特殊字符”：";
					}
				}
			//	不能包含用户的帐户名
				if(itemNo.equals("6")){
					if(!(pwd.indexOf(userId) == -1)){
						blsec+="密码不能包含用户的帐户名：";
					}
				}
			}
			return blsec;
		}catch(Exception e){
			throw new ServiceException(e.getMessage());
		}
	}
	*/
	public String SecurityChangePwd(String userId,String pwd) throws ServiceException{
		SecurityAudit audit= securityAuditBo.getById("1");
		String[] checkPasswordRegIndex = audit.getItemValues().split("@");
		for(PasswordRegexp reg:PasswordRegexp.values()){
			for(String index:checkPasswordRegIndex){
				if(Integer.valueOf(index) == reg.index ){
					if(!reg.matcherRegexp(pwd))return reg.matcherRegexpStr(pwd);
				}
			}
		}
		return Boolean.TRUE.toString();
	}
	public void insertUserApptime(String urlStr,String tlrno,String opname,String param,String startTime,String endTime,double ce) throws ServiceException{
		String sysdate = startTime.substring(0,4)+startTime.substring(4,6)+startTime.substring(6,10);
		UserApptime userApptime = new UserApptime();
		userApptime.setActionName(urlStr);
		userApptime.setActionPara(param);
		userApptime.setBelongDate(sysdate.replace("-", ""));
		userApptime.setEndTime(endTime.substring(8,10)+":"+endTime.substring(10,12)+":"+endTime.substring(12,16));
		userApptime.setStartTime(startTime.substring(8,10)+":"+startTime.substring(10,12)+":"+startTime.substring(12,16));
		userApptime.setTimeConsuming(ce/1000);
		userApptime.setUserId(tlrno);
		userApptime.setUserName(opname);
		userApptimeBo.insert(userApptime);
	}
	
	public void insertNewLoginInfo(String loginId, LoginStatus status) {
		logininfosummarybo.insertNewInfo(loginId, status);
	}
	
	public LoginPaswordStatus newSecurityPwd(String userId,boolean isLogin)throws ServiceException{
		List<SecurityAudit> auditList = securityAuditBo.findAuditListByCode("PASSWORD");
		UserMarkInfo userMarkInfo = userMarkInfoBo.getById(userId);
		String sysdate = DateUtil.getSysDate();
		if(userMarkInfo==null){//首次登录，插入新的数据即可
			userMarkInfo = insertNewUserMarkInfo(userId,sysdate,isLogin);
		}
		for(SecurityAudit audit:auditList){
			if(audit.getItemNo().equals("1") || "0".equals(audit.getIsUse()))continue;
			else if(audit.getItemNo().equals("2")){//查看密码有效时间
				int validateDays = Integer.valueOf(audit.getItemValues());
				if(userMarkInfo.getPasswordUpdateTime()!=null&&!userMarkInfo.getPasswordUpdateTime().equals("")){
					int betweenday = DateUtil.getBetweenDays(changeDateFormat(userMarkInfo.getPasswordUpdateTime()),changeDateFormat(sysdate));
					if(betweenday > validateDays)
						return LoginPaswordStatus.OVER_TIME.changeTime(betweenday - validateDays);//超过有效时间
				}else{//若没修改过密码，直接进入密码修改界面
					return LoginPaswordStatus.NEED_CHANGE_PASSWORD;
				}
			}
			else if(audit.getItemNo().equals("3") ){//查看错误容忍次数
				int validateTime = Integer.valueOf(audit.getItemValues());
				LoginPaswordStatus status = null;
				if(validateTime < (userMarkInfo.getLoginErrorTimes()+1)){
					userMarkInfo.setPasswordState("1");
					if(isLogin)userMarkInfo.setPasswordState("2");//如果此次密码正确，则不对错误次数+1
					status = LoginPaswordStatus.OVER_FAULT_TOLE.changeTime(validateTime);//超过容忍次数
				}else{
					if(!isLogin){
						status = LoginPaswordStatus.VALIDATE_FAIL;
					}
				}
				userMarkInfo = updateUserMarkInfo(userMarkInfo, sysdate, isLogin);
				if(status!=null)return status;
			}
		}
		return LoginPaswordStatus.SUCCESS;
	}
	/**
	 * 返回YYYY-MM-dd的格式的日期
	 * @param date
	 * @return
	 */
	private String changeDateFormat(String date){
		return date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
	}
	
	private UserMarkInfo insertNewUserMarkInfo(String userId,String sysdate,boolean isLogin){
		UserMarkInfo uMI = new UserMarkInfo();
		uMI.setUserId(userId);//操作员号
		uMI.setPasswordState("0");//密码状态
		uMI.setVisitTimes(1);//登录次数
		if(isLogin){
			uMI.setLoginErrorTimes(1);
			uMI.setPasswordMessege("正常登录");//登录信息
		}else{
			uMI.setLoginErrorTimes(0);
			uMI.setPasswordMessege("登录失败");//登录信息
		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		String nowtime = sdf.format(new Date());
//		Date nowSysTime = sdf.parse(sysdate+nowtime.substring(8));
//		uMI.setCurrentSignInTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nowSysTime));//本次登录日期
		uMI.setCurrentSignInTime(sysdate);
		userMarkInfoBo.insert(uMI);
		return uMI;
	}
	
	private UserMarkInfo updateUserMarkInfo(UserMarkInfo info,String sysdate,boolean isLogin){
		if(isLogin){
			info.setLoginErrorTimes(0);//如果正常登录后，历史记录的错误次数清零
			info.setPasswordMessege("正常登录");
			info.setPasswordState("0");
		}else{
			if(!"2".equals(info.getPasswordState()))
					info.setLoginErrorTimes(info.getLoginErrorTimes()+1);//如果登录失败，错误次数继续增加
			info.setPasswordMessege("登录失败");
		}
		info.setLastSignInTime(info.getCurrentSignInTime());
		info.setCurrentSignInTime(sysdate);
		info.setVisitTimes(info.getVisitTimes()+1);
		userMarkInfoBo.update(info);
		return info;
	}
	
	public UserMarkInfoBo getUserMarkInfoBo() {
		return userMarkInfoBo;
	}
	public void setUserMarkInfoBo(UserMarkInfoBo userMarkInfoBo) {
		this.userMarkInfoBo = userMarkInfoBo;
	}
	public SecurityAuditBo getSecurityAuditBo() {
		return securityAuditBo;
	}

	public void setSecurityAuditBo(SecurityAuditBo securityAuditBo) {
		this.securityAuditBo = securityAuditBo;
	}
	public AuditInfoLogBo getAuditInfoLogBo() {
		return auditInfoLogBo;
	}
	public void setAuditInfoLogBo(AuditInfoLogBo auditInfoLogBo) {
		this.auditInfoLogBo = auditInfoLogBo;
	}
	public UserApptimeBo getUserApptimeBo() {
		return userApptimeBo;
	}
	public void setUserApptimeBo(UserApptimeBo userApptimeBo) {
		this.userApptimeBo = userApptimeBo;
	}
	public void setLogininfosummarybo(LoginInfoSummaryBO logininfosummarybo) {
		this.logininfosummarybo = logininfosummarybo;
	}
}
