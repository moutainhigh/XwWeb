package  app.creditapp.cif.bo;

import java.util.List;

import javax.jws.WebParam;

import app.base.ServiceException;
import app.creditapp.cif.entity.CifPersInf;
import app.creditapp.cif.entity.CifPersRelCore;
import app.creditapp.cif.entity.CifPersRelLine;
import app.creditapp.cred.entity.PcrpQueryInfo;
import app.util.toolkit.Ipage;

/**
* Title: CifPersBo.java
* Description:
**/
public interface CifPersInfBo {

	public CifPersInf getById(CifPersInf cifPers) throws ServiceException;

	public void del(CifPersInf cifPers) throws ServiceException;

	public void insert(CifPersInf cifPers) throws ServiceException;

	public void update(CifPersInf cifPers) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CifPersInf cifPers) throws ServiceException;
	
	/**
	 * 单笔查询征信结果调用 孙明义
	 */
	public String getByCrp(@WebParam(name="pcrpQueryInfoJson") PcrpQueryInfo pcrpQueryInfo);
	public List<CifPersRelCore> getCifPersRelCores(CifPersInf cifPers) throws ServiceException;
	public List<CifPersRelLine> getCifPersRelLines(CifPersInf cifPers) throws ServiceException;
}