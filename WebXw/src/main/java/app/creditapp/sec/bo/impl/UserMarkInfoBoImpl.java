package  app.creditapp.sec.bo.impl;

import java.util.HashMap;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sec.bo.SecurityAuditBo;
import app.creditapp.sec.bo.UserMarkInfoBo;
import app.creditapp.sec.dao.UserMarkInfoDao;
import app.creditapp.sec.entity.SecurityAudit;
import app.creditapp.sec.entity.UserMarkInfo;
import app.creditapp.securityinterface.SecurityInterface;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;


/**
* Title: UserMarkInfoBoImpl.java
* Description:
**/

public class UserMarkInfoBoImpl extends BaseService implements UserMarkInfoBo {

	private UserMarkInfoDao userMarkInfoDao;
	private SecurityAuditBo securityAuditBo ;
	
	public void setSecurityAuditBo(SecurityAuditBo securityAuditBo) {
		this.securityAuditBo = securityAuditBo;
	}

	public void del(String id) throws ServiceException {
		try {
			userMarkInfoDao.del(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, UserMarkInfo userMarkInfo)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) userMarkInfoDao
					.getCount(userMarkInfo) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, userMarkInfo);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(userMarkInfoDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public UserMarkInfo getById(String id) throws ServiceException {
		UserMarkInfo userMarkInfo = null;
		try {
			userMarkInfo = userMarkInfoDao.getById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return userMarkInfo;
	}
	public void insertOrUpdate(UserMarkInfo userMarkInfo) throws ServiceException {
		try {
			UserMarkInfo uMI=userMarkInfoDao.getById(userMarkInfo.getUserId());
			SecurityAudit securityAudit =securityAuditBo.getById("8");//这句 只为获取密码校验容忍次数
			int itemValues = Integer.parseInt(securityAudit.getItemValues());
			if(userMarkInfo.getPasswordMessege().equals("main")){//正常登录
				if(uMI==null){
					uMI = new UserMarkInfo();
					uMI.setUserId(userMarkInfo.getUserId());//操作员号
					uMI.setPasswordMessege("正常登录");//登录信息
					uMI.setPasswordState("0");//密码状态
					uMI.setVisitTimes(1);//登录次数
					uMI.setCurrentSignInTime(userMarkInfo.getCurrentSignInTime());//本次登录日期
					this.insert(uMI);
				}else{
					uMI.setLoginErrorTimes(0);//如果正常登录后，历史记录的错误次数清零
					uMI.setPasswordMessege("正常登录");
					uMI.setPasswordState("0");
					uMI.setLastSignInTime(uMI.getCurrentSignInTime());
					uMI.setCurrentSignInTime(userMarkInfo.getCurrentSignInTime());
					uMI.setVisitTimes(uMI.getVisitTimes()+1);
					this.update(uMI);
				}
			}else if(userMarkInfo.getPasswordMessege().equals("logout")){//退出
				if(uMI!=null){
				uMI.setPasswordState("0");
				uMI.setLastSignOutTime(userMarkInfo.getLastSignOutTime());
				this.update(uMI);
				}
			}else{//密码校验报错
				if(uMI==null){
					uMI = new UserMarkInfo();
					uMI.setUserId(userMarkInfo.getUserId());//操作员号
					uMI.setPasswordState("0");//密码状态
					uMI.setVisitTimes(1);//登录次数
					uMI.setCurrentSignInTime(userMarkInfo.getCurrentSignInTime());//本次登录日期
					uMI.setLoginErrorTimes(1);
					uMI.setPasswordMessege(userMarkInfo.getPasswordMessege());
					this.insert(uMI);
				}else{
					uMI.setLastSignInTime(uMI.getCurrentSignInTime());
					uMI.setVisitTimes(uMI.getVisitTimes()+1);
					uMI.setCurrentSignInTime(userMarkInfo.getCurrentSignInTime());//本次登录日期
					uMI.setPasswordMessege(userMarkInfo.getPasswordMessege());
					//如果密码锁定状态为“1”，则不修改数据，证明当前密码已锁定无法更改状态
						if(!uMI.getPasswordState().equals("1")){
							if((uMI.getLoginErrorTimes())+1-itemValues==0){
								uMI.setLoginErrorTimes(uMI.getLoginErrorTimes()+1);
								uMI.setPasswordState("1");//密码错误X次，登录人员锁定不可再登录
							}else{
								uMI.setLoginErrorTimes(uMI.getLoginErrorTimes()+1);
							}
							this.update(uMI);
						}
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	public void insert(UserMarkInfo userMarkInfo) throws ServiceException {
		try {
			userMarkInfoDao.insert(userMarkInfo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(UserMarkInfo userMarkInfo) throws ServiceException {
		try {
			userMarkInfoDao.update(userMarkInfo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public UserMarkInfo getAllLogin(String login) throws ServiceException {
		UserMarkInfo userMarkInfo = null;
		try {
			userMarkInfo = userMarkInfoDao.getAllLogin(login);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return userMarkInfo;
	}
	public void replay(String id) throws ServiceException {
		UserMarkInfo userMarkInfo = null;
		try {
			userMarkInfo = userMarkInfoDao.getById(id);
			userMarkInfo.setLoginErrorTimes(0);
			userMarkInfo.setPasswordState("0");
			this.update(userMarkInfo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public UserMarkInfoDao getUserMarkInfoDao() {
		return userMarkInfoDao;
	}

	public void setUserMarkInfoDao(UserMarkInfoDao userMarkInfoDao) {
		this.userMarkInfoDao = userMarkInfoDao;
	}

	@Override
	public void updateSts(UserMarkInfo userMarkInfo) throws ServiceException {
		// TODO Auto-generated method stub
		userMarkInfoDao.updateSts(userMarkInfo);
	}

}
