package  app.util.syslog.bo.impl;
import app.base.BaseService;
import app.base.ServiceException;
import app.util.DateUtil;
import app.util.syslog.bo.SysExceptionBo;
import app.util.syslog.dao.SysExceptionDao;
import app.util.syslog.entity.SysException;
import app.util.toolkit.Ipage;
/**
* Title: SysExceptionBoImplImpl.java
* Description:
**/
public class SysExceptionBoImpl extends BaseService implements SysExceptionBo {

	private SysExceptionDao sysExceptionDao;

	public void del(SysException sysException) throws ServiceException {
		try {
			sysExceptionDao.del(sysException);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysException sysException)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysExceptionDao
					.getCount(sysException) });// 初始化分页类
			sysException.setStartNumAndEndNum (ipg);
			ipg.setResult(sysExceptionDao.findByPage(sysException));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysException getById(SysException sysException) throws ServiceException {
		try {
			sysException = sysExceptionDao.getById(sysException);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysException;
	}

	public void insert(SysException sysException) throws ServiceException {
		try {
			sysExceptionDao.insert(sysException);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysException sysException) throws ServiceException {
		try {
			sysExceptionDao.update(sysException);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	public void insertNewExceptionLog(Exception exception) {
		StackTraceElement[] stacks = exception.getStackTrace();
		int stacksLen = stacks.length;
		if(stacksLen > 0){
			SysException newException = new SysException();
			newException.setExpDate(DateUtil.getDate());
			newException.setExpTime(DateUtil.getTime());
			newException.setExpClass("class:"+stacks[0].getClassName()+";method:"+stacks[0].getMethodName()+";line:"+stacks[0].getLineNumber());
			newException.setExpInfo(exception.toString());
			sysExceptionDao.insert(newException);
		}
	}
	
	public void insertNewExceptionLog(Exception exception, String userId) {
		StackTraceElement[] stacks = exception.getStackTrace();
		int stacksLen = stacks.length;
		try {
			if (stacksLen > 0) {
				SysException newException = new SysException();
				newException.setUserId(userId);
				newException.setExpDate(DateUtil.getDate());
				newException.setExpTime(DateUtil.getTime());
				newException.setExpClass("class:" + stacks[0].getClassName() + ";method:" + stacks[0].getMethodName()
						+ ";line:" + stacks[0].getLineNumber());
				newException.setExpInfo(exception.toString());
				sysExceptionDao.insert(newException);
			}
		} catch (Exception we) {
			we.printStackTrace();
		}
	}
	
	public void insertNewWorkExceptionLog(Exception exception, String appId) {
		StackTraceElement[] stacks = exception.getStackTrace();
		int stacksLen = stacks.length;
		try {
			if (stacksLen > 0) {
				SysException newException = new SysException();
				newException.setExpDate(DateUtil.getDate());
				newException.setExpTime(DateUtil.getTime());
				newException.setExpClass("class:" + stacks[0].getClassName() + ";method:" + stacks[0].getMethodName()
						+ ";line:" + stacks[0].getLineNumber());
				newException.setExpInfo("appId="+appId+",异常信息为:"+exception.toString());
				sysExceptionDao.insert(newException);
			}
		} catch (Exception we) {
			we.printStackTrace();
		}
	}
	
	public void insertNewExceptionLog(String msg,Throwable throwException) {
		StackTraceElement[] stacks = throwException.getStackTrace();
		int stacksLen = stacks.length;
		if(stacksLen > 0){
			SysException newException = new SysException();
			newException.setExpDate(DateUtil.getDate());
			newException.setExpTime(DateUtil.getTime());
			newException.setExpClass("class:"+stacks[0].getClassName()+";method:"+stacks[0].getMethodName()+";line:"+stacks[0].getLineNumber());
			newException.setExpInfo(msg);
			sysExceptionDao.insert(newException);
		}
	}

	public SysExceptionDao getSysExceptionDao() {
		return sysExceptionDao;
	}

	public void setSysExceptionDao(SysExceptionDao sysExceptionDao) {
		this.sysExceptionDao = sysExceptionDao;
	}


}