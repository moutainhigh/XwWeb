package  app.creditapp.sec.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sec.entity.UserMarkInfo;


/**
* Title: UserMarkInfoDao.java
* Description:
**/

public interface UserMarkInfoDao {

	public UserMarkInfo getById(String id) throws DAOException;

	public void del(String id) throws DAOException;

	public void insert(UserMarkInfo userMarkInfo) throws DAOException;

	public void update(UserMarkInfo userMarkInfo) throws DAOException;
	
	public void updateSts(UserMarkInfo userMarkInfo) throws DAOException;
	
	public int getCount(UserMarkInfo userMarkInfo) throws DAOException;

	public List<UserMarkInfo > findByPage(Map map) throws DAOException;
	
	public UserMarkInfo getAllLogin(String login) throws DAOException;
}
