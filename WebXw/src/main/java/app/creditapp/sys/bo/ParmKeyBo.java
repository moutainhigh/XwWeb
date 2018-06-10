package  app.creditapp.sys.bo;

import java.util.List;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.ParmKey;

/**
* Title: ParmKeyBo.java
* Description:
**/
public interface ParmKeyBo {

	public ParmKey getById(ParmKey ParmKey) throws ServiceException;

	public void del(ParmKey ParmKey) throws ServiceException;

	public void insert(ParmKey ParmKey) throws ServiceException;
	
	public void update(ParmKey ParmKey) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ParmKey ParmKey) throws ServiceException;


}