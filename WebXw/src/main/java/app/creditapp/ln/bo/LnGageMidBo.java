package  app.creditapp.ln.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.ln.entity.LnGageMid;

/**
* Title: LnGageMidBo.java
* Description:
**/
public interface LnGageMidBo {

	public LnGageMid getById(LnGageMid lnGageMid) throws ServiceException;

	public void del(LnGageMid lnGageMid) throws ServiceException;

	public void insert(LnGageMid lnGageMid) throws ServiceException;

	public void update(LnGageMid lnGageMid) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnGageMid lnGageMid) throws ServiceException;

	public Ipage findByPageQuotaForLn(Ipage ipage, LnGageMid lnGageMid)throws ServiceException;

	public List<LnGageMid> getListByLnGageMid(LnGageMid lnGageMid)throws ServiceException;

}