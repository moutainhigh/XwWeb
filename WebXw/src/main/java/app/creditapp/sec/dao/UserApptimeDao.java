package  app.creditapp.sec.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sec.entity.UserApptime;


/**
* Title: UserApptimeDao.java
* Description:
**/

public interface UserApptimeDao {

	public UserApptime getById(long id) throws DAOException;

	public void del(long id) throws DAOException;

	public void insert(UserApptime userApptime) throws DAOException;

	public void update(UserApptime userApptime) throws DAOException;
	
	public int getCount(UserApptime userApptime) throws DAOException;
	
	public int getAllCount(UserApptime userApptime) throws DAOException;

	public List<UserApptime > findByPage(Map map) throws DAOException;

	public List<UserApptime > allFindByPage(Map map) throws DAOException;
}
