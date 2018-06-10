package app.creditapp.securityinterface;

import app.base.ServiceException;
import app.creditapp.sec.entity.LoginPaswordStatus;
import app.creditapp.sec.entity.LoginStatus;
import app.creditapp.sec.entity.SecurityAudit;

//import com.deep.wf.bean.UserBean;

public interface SecurityInterface {
	public String SecurityPwd(String userId)throws ServiceException;
	public void insertOrUpdate(String userId,String Message)throws ServiceException;
	public void update(String userId)throws ServiceException;
	//public void insertAudit(UserBean userbean,String userId,String userName)throws ServiceException;
	public String SecurityChangePwd(String userId,String pwd)throws ServiceException;
	public SecurityAudit getById(String secId) throws ServiceException;
	public void insertUserApptime(String urlStr,String tlrno,String opname,String param,String startTime,String endTime,double ce) throws ServiceException;

	void insertNewLoginInfo(String loginId,LoginStatus status);
	
	LoginPaswordStatus newSecurityPwd(String userId,boolean isLogin)throws ServiceException;
	public void saveLogout(String userId,String sysdate) throws ServiceException;
}
