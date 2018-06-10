package  app.creditapp.aft.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.aft.entity.AftReliefDtl;

/**
* Title: AftReliefDtlBo.java
* Description:
**/
public interface AftReliefDtlBo {

	public AftReliefDtl getById(AftReliefDtl aftReliefDtl) throws ServiceException;

	public void del(AftReliefDtl aftReliefDtl) throws ServiceException;

	public void insert(AftReliefDtl aftReliefDtl) throws ServiceException;

	public void update(AftReliefDtl aftReliefDtl) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftReliefDtl aftReliefDtl) throws ServiceException;

}