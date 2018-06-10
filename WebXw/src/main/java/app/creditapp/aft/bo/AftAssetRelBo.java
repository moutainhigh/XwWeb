package  app.creditapp.aft.bo;

import java.util.Map;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.aft.entity.AftAssetRel;

/**
* Title: AftAssetRelBo.java
* Description:
**/
public interface AftAssetRelBo {

	public AftAssetRel getById(AftAssetRel aftAssetRel) throws ServiceException;

	public void del(AftAssetRel aftAssetRel) throws ServiceException;

	public void insert(AftAssetRel aftAssetRel) throws ServiceException;

	public void update(AftAssetRel aftAssetRel) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftAssetRel aftAssetRel) throws ServiceException;
	
	public void updateValue(Map<?, ?> map)throws ServiceException;

	public double getTotalAmt(String pactNo,String recId)throws ServiceException;

}