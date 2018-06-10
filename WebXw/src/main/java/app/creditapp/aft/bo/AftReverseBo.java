package  app.creditapp.aft.bo;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.aft.entity.AftReverse;

/**
* Title: AftReverseBo.java
* Description:
**/
public interface AftReverseBo {

	public AftReverse getById(AftReverse aftReverse) throws ServiceException;
	public AftReverse getByIdForRead(AftReverse aftReverse) throws ServiceException;

	public void del(AftReverse aftReverse) throws ServiceException;

	public void insert(AftReverse aftReverse) throws ServiceException;

	public void update(AftReverse aftReverse) throws ServiceException;
	
	public void updateReveSts(AftReverse aftReverse) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftReverse aftReverse) throws ServiceException;

}