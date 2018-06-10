package  app.creditapp.ln.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.ln.entity.LnAcctMid;
import app.creditapp.ln.entity.LnApplyMid;

/**
* Title: LnAcctMidBo.java
* Description:
**/
public interface LnAcctMidBo {

	public LnAcctMid getById(LnAcctMid lnAcctMid) throws ServiceException;

	public void del(LnAcctMid lnAcctMid) throws ServiceException;

	public void insert(LnAcctMid lnAcctMid) throws ServiceException;

	public void update(LnAcctMid lnAcctMid) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnAcctMid lnAcctMid) throws ServiceException;

	public Ipage findByPageQuotaForLn(Ipage ipage, LnAcctMid lnAcctMid) throws ServiceException;

	public List<LnAcctMid> getListByLnAcctMid(LnAcctMid lnAcctMid) throws ServiceException;

}