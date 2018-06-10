package  app.creditapp.corp.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.corp.entity.CorpParm;
import app.util.toolkit.Ipage;

/**
* Title: CorpParmBo.java
* Description:
**/
public interface CorpParmBo {

	public CorpParm getById(CorpParm corpParm) throws ServiceException;

	public void del(CorpParm corpParm) throws ServiceException;

	public void insert(CorpParm corpParm) throws ServiceException;

	public void update(CorpParm corpParm) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpParm corpParm) throws ServiceException;

	public Ipage findByPageQuotaForCorp(Ipage ipage, CorpParm corpParm)throws ServiceException;
	
	public List<Object> findListBySts(CorpParm corpParm) throws ServiceException;
	
	public void delForBase(CorpParm corpParm) throws ServiceException ;

}