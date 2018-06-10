package  app.creditapp.sys.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.InterfaceBackMeg;

/**
* Title: InterfaceBackMegBo.java
* Description:
**/
public interface InterfaceBackMegBo {

	public InterfaceBackMeg getById(InterfaceBackMeg interfaceBackMeg) throws ServiceException;

	public void del(InterfaceBackMeg interfaceBackMeg) throws ServiceException;

	public void insert(InterfaceBackMeg interfaceBackMeg) throws ServiceException;

	public void update(InterfaceBackMeg interfaceBackMeg) throws ServiceException;

	public Ipage findByPage(Ipage ipg, InterfaceBackMeg interfaceBackMeg) throws ServiceException;

}