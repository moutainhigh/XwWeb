package  app.creditapp.acc.option.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcFeeRate;

/**
* Title: AcFeeRateBo.java
* Description:
**/
public interface AcFeeRateBo {

	public AcFeeRate getById(AcFeeRate acFeeRate) throws ServiceException;

	public void del(AcFeeRate acFeeRate) throws ServiceException;

	public void insert(AcFeeRate acFeeRate) throws ServiceException;

	public void update(AcFeeRate acFeeRate) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcFeeRate acFeeRate) throws ServiceException;

}