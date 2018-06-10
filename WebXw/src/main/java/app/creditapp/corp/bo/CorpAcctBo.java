package  app.creditapp.corp.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.entity.CorpAcct;
import app.creditapp.corp.entity.CorpBase;

/**
* Title: CorpAcctBo.java
* Description:
**/
public interface CorpAcctBo {

	public CorpAcct getById(CorpAcct corpAcct) throws ServiceException;

	public void del(CorpAcct corpAcct) throws ServiceException;
	
	public CorpAcct getByIdFor5202(String acctNo) throws ServiceException;
	
	public void delForBase(CorpAcct corpAcct) throws ServiceException;

	public void insert(CorpAcct corpAcct) throws ServiceException;

	public void update(CorpAcct corpAcct) throws ServiceException;
	public void updateAcctJS(CorpAcct corpAcct) throws ServiceException;
	public Ipage findByPage(Ipage ipg, CorpAcct corpAcct) throws ServiceException;
	
	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpAcct corpAcct)throws ServiceException;
	
	public int  getCount(CorpAcct corpAcct) throws ServiceException;
}