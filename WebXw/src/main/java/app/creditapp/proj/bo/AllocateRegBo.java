package  app.creditapp.proj.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.proj.entity.AllocateReg;

/**
* Title: AllocateRegBo.java
* Description:
**/
public interface AllocateRegBo {

	public AllocateReg getById(AllocateReg allocateReg) throws ServiceException;

	public void del(AllocateReg allocateReg) throws ServiceException;

	public void insert(AllocateReg allocateReg) throws ServiceException;

	public void update(AllocateReg allocateReg) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AllocateReg allocateReg) throws ServiceException;

}