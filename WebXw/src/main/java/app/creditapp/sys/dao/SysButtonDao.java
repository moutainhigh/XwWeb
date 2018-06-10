package  app.creditapp.sys.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sys.entity.SysButton;
/**
* Title: SysButtonDao.java
* Description:
**/
public interface SysButtonDao {

	public SysButton getById(SysButton sysButton) throws DAOException;

	public void del(SysButton sysButton) throws DAOException;

	public void insert(SysButton sysButton) throws DAOException;

	public void update(SysButton sysButton) throws DAOException;
	
	public int getCount(SysButton sysButton) throws DAOException;

	public List<SysButton > findByPage(SysButton sysButton) throws DAOException;

	public List<SysButton> findAllByMenu(SysButton sysButton) throws DAOException;
}