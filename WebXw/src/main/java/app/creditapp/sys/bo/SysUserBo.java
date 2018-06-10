package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.SysUser;

/**
* Title: SysUserBo.java
* Description:
**/
public interface SysUserBo {

	public SysUser getById(SysUser SysUser) throws ServiceException;

	public void del(SysUser SysUser) throws ServiceException;

	public void insert(SysUser SysUser) throws ServiceException;

	public void update(SysUser SysUser) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysUser SysUser) throws ServiceException;
	public Ipage findByPageForPop(Ipage ipg, SysUser SysUser) throws ServiceException;
	
	public void updateUserSts(SysUser SysUser) throws ServiceException;

	public String changePassWord(String pwInfo) throws ServiceException;		
	//ͨѶ¼
	public Ipage findPageForAddressBook(Ipage ipg, SysUser tou) throws ServiceException;
	public SysUser getByLoginId(SysUser SysUser) throws ServiceException;
	
	public void updateAddressBookInfo(SysUser SysUser) throws ServiceException;
	public void updateSkin(SysUser op) throws ServiceException;

	public Ipage findMangNoPop(Ipage ipage, SysUser SysUser)throws ServiceException;

	public String getBatchSuccessFlag()throws ServiceException;
	
	public Ipage findZt(Ipage ipage, SysUser SysUser)throws ServiceException;

}