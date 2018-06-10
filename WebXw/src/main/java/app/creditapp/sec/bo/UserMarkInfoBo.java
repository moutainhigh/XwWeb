package  app.creditapp.sec.bo;

import app.base.ServiceException;
import app.creditapp.sec.entity.UserMarkInfo;
import app.util.toolkit.Ipage;


/**
* Title: UserMarkInfoBo.java
* Description:
**/

public interface UserMarkInfoBo {

	public UserMarkInfo getById(String id) throws ServiceException;

	public void del(String id) throws ServiceException;

	public void insertOrUpdate(UserMarkInfo userMarkInfo) throws ServiceException;
	
	public void insert(UserMarkInfo userMarkInfo) throws ServiceException;
//
	public void update(UserMarkInfo userMarkInfo) throws ServiceException;

	public void updateSts(UserMarkInfo userMarkInfo) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, UserMarkInfo userMarkInfo) throws ServiceException;

	public UserMarkInfo getAllLogin(String login) throws ServiceException;
	
	public void replay(String userId) throws ServiceException;
}
