package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.SysUser;
/**
* Title: SysUserDao.java
* Description:
**/
public interface SysUserDao {

	public SysUser getById(SysUser SysUser) throws DAOException;

	public void del(SysUser SysUser) throws DAOException;

	public void insert(SysUser SysUser) throws DAOException;

	public void update(SysUser SysUser) throws DAOException;
	
	public int getCount(SysUser SysUser) throws DAOException;
	public int getCountForPop(SysUser SysUser) throws DAOException;

	public List<SysUser > findByPage(SysUser SysUser) throws DAOException;
	public List<SysUser > findByPageForPop(SysUser SysUser) throws DAOException;
	
	public void updateUserSts(SysUser SysUser) throws DAOException;

	//Í¨Ñ¶Â¼
	public int getCountForAddressBook(SysUser tou) throws DAOException;
	public List<SysUser> findPageForAddressBook(SysUser tou) throws DAOException;
	public SysUser getByLoginId(SysUser SysUser) throws DAOException;
	
	public void updateAddressBookInfo(SysUser SysUser) throws DAOException;
	public void updateSkin(SysUser op) throws DAOException;//»»·ô

	public int getMangNoPopCount(SysUser SysUser)throws DAOException;

	public List<SysUser> findMangNoPop(SysUser SysUser)throws DAOException;
	
	public int getCountZt(SysUser SysUser) throws DAOException;

	public List<SysUser > findZt(SysUser SysUser) throws DAOException;
	
}