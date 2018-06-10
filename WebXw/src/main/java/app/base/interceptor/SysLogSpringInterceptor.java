package app.base.interceptor;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.aop.AfterReturningAdvice;

import app.base.SourceTemplate;
import app.creditapp.sys.bo.SysLogBo;
import app.creditapp.sys.entity.SysLog;
import app.util.DateUtil;
import app.util.User;

import com.opensymphony.xwork2.ActionContext;

/**
 * 系统日志拦截器
 *
 * @see 
 * 修改记录:
 */
public class SysLogSpringInterceptor implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method,Object[] args, Object target) throws Throwable {
		//初始日志信息
		SysLog sysLog = constructSysLog(method,args);
		if(sysLog==null)return;
		//保存日志信息
		SysLogBo sysLogBo = SourceTemplate.getSpringContextInstance().getBean("sysLogBo",SysLogBo.class);
		if(sysLog.getOpId().length()>200)
			sysLog.setOpId(sysLog.getOpId().substring(0,200));
		sysLogBo.insertOrUpdate(sysLog);
	}
	
	/**
	 * 构造SysLog
	 * @param method
	 * @param args
	 * @return
	 * @throws Exception
	 */
	private SysLog constructSysLog(Method method,Object[] args) throws Exception{
		SysLog sysLog = new SysLog();
		//操作描述
		if(StringUtils.startsWithAny(method.getName(), new String[]{"save","Save","insert","Insert"})){
			sysLog.setOpDesc("新增记录");
		}else if(StringUtils.startsWithAny(method.getName(), new String[]{"update","Update"})){
			sysLog.setOpDesc("修改记录");
		}else if(StringUtils.startsWithAny(method.getName(), new String[]{"del","delete"})){
			sysLog.setOpDesc("删除记录");
		}else{
			return null;
		}
		if("SYS_LOG".equals(getTableName(method))){
			return null;
		}
		
		//机构柜员
		if(ActionContext.getContext()==null){
//			sysLog.setOpNo(User.getTlrno(WebContextFactory.get().getHttpServletRequest()));
//			sysLog.setBrNo(User.getBrno(WebContextFactory.get().getHttpServletRequest()));
		}else{
			sysLog.setOpNo(User.getLoginid(ServletActionContext.getRequest()));
		}
		//操作时间
		sysLog.setLogDate(DateUtil.getDate());
		sysLog.setLogTime(DateUtil.getTime());
		//操作表主键
		if(args[0] instanceof String || args[0] instanceof Integer){
			sysLog.setOpId(String.valueOf(args[0]));
		}else{
			if(args[0] instanceof java.util.List){
				//TODO: 若形参是List处理 Leopard
				return null;
			}else{
				String keyName1 = args[0].getClass().getDeclaredFields()[0].getName();	//唯一索引1
			//	String keyName2 = args[0].getClass().getDeclaredFields()[1].getName();  //联合索引2
				if(StringUtils.equalsIgnoreCase(keyName1, "serialVersionUID")){
					keyName1 = args[0].getClass().getDeclaredFields()[1].getName();
				//	keyName2 = args[0].getClass().getDeclaredFields()[2].getName();
				}
				sysLog.setOpId(String.valueOf(PropertyUtils.getProperty(args[0], keyName1)));
				//sysLog.setOp_seqn(String.valueOf(PropertyUtils.getProperty(args[0], keyName2)));
			}
		}
		return sysLog;
	}
	/**
	 * 根据方法签名获得带下划线的表名
	 * @param method 方法
	 * @return
	 */
	private String getTableName(Method method){
		String classSimpleName = method.getDeclaringClass().getSimpleName();
		String entityname_u = classSimpleName.substring(0, classSimpleName.length()-3);
		String entityname_l = StringUtils.uncapitalize(entityname_u);
		String tableName = "";
		for(int i=0;i<entityname_l.length();i++){
			if(entityname_l.charAt(i)<='Z' && entityname_l.charAt(i)>='A'){
				tableName = tableName+"_"+entityname_l.charAt(i);
			}else{
				tableName = tableName+entityname_l.charAt(i);
			}
		}
		return StringUtils.upperCase(tableName);
	}
	

}
