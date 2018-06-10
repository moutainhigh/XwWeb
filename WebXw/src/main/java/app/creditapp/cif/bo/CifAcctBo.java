package  app.creditapp.cif.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cif.entity.CifAcct;

/**
* Title: CifAcctBo.java
* Description:
**/
public interface CifAcctBo {

	public CifAcct getById(CifAcct cifAcct) throws ServiceException;

	public void del(CifAcct cifAcct) throws ServiceException;

	public void insert(CifAcct cifAcct) throws ServiceException;

	public void update(CifAcct cifAcct) throws ServiceException;

	/**
	 * 客户的账户信息
	 * @param ipg
	 * @param cifAcct
	 * @return
	 * @throws ServiceException
	 */
	public Ipage findByPage(Ipage ipg, CifAcct cifAcct) throws ServiceException;

	public Ipage findByPageQuotaForCif(Ipage ipage, CifAcct cifAcct) throws ServiceException;

}