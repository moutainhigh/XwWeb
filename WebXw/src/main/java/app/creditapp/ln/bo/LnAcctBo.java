package  app.creditapp.ln.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.ln.entity.LnAcct;
import app.util.toolkit.Ipage;

/**
* Title: LnAcctBo.java
* Description:
**/
public interface LnAcctBo {
	
	public List<LnAcct> getByIdAndSts(LnAcct lnAcct)throws ServiceException;

	public LnAcct getById(LnAcct lnAcct) throws ServiceException;

	public void del(LnAcct lnAcct) throws ServiceException;

	public void insert(LnAcct lnAcct) throws ServiceException;

	public void update(LnAcct lnAcct) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnAcct lnAcct) throws ServiceException;

	public Ipage findByPageQuotaForLn(Ipage ipage, LnAcct lnAcct)throws ServiceException;
	//2501接口获取账号是否存在
	public int  getCountFor2501(LnAcct lnAcct) throws ServiceException;
}