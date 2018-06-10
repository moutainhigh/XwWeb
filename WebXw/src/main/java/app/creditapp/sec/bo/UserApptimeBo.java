package app.creditapp.sec.bo;

import app.base.ServiceException;
import app.creditapp.sec.entity.UserApptime;
import app.util.toolkit.Ipage;


/**
* Title: UserApptimeBo.java
* Description:
**/

public interface UserApptimeBo {

	public UserApptime getById(long id) throws ServiceException;

	public void del(long id) throws ServiceException;

	public void insert(UserApptime userApptime) throws ServiceException;

	public void update(UserApptime userApptime) throws ServiceException;

	public Ipage findByPage(Ipage ipg, UserApptime userApptime) throws ServiceException;

	public Ipage allFindByPage(Ipage ipg, UserApptime userApptime) throws ServiceException;
}
