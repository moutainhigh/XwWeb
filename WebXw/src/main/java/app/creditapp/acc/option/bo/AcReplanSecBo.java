package  app.creditapp.acc.option.bo;

import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcReplanSec;

/**
* Title: AcReplanSecBo.java
* Description:
**/
public interface AcReplanSecBo {

	public AcReplanSec getById(AcReplanSec acReplanSec) throws ServiceException;

	public AcReplanSec getByIdAndOrderNo(AcReplanSec acReplanSec) throws ServiceException;

	public void del(AcReplanSec acReplanSec) throws ServiceException;
	
	public void delByReplanId(AcReplanSec acReplanSec) throws ServiceException;


	public void insert(AcReplanSec acReplanSec) throws ServiceException;

	public void update(AcReplanSec acReplanSec) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcReplanSec acReplanSec) throws ServiceException;
	
	public List<AcReplanSec > getListByReplanId(AcReplanSec acReplanSec) throws DAOException;


}