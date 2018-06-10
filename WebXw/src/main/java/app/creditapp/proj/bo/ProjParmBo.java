package  app.creditapp.proj.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.proj.entity.ProjParm;
import app.util.toolkit.Ipage;

/**
* Title: ProjParmBo.java
* Description:
**/
public interface ProjParmBo {

	public ProjParm getById(ProjParm projParm) throws ServiceException;

	public void del(ProjParm projParm) throws ServiceException;

	public void insert(ProjParm projParm) throws ServiceException;

	public void update(ProjParm projParm) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ProjParm projParm) throws ServiceException;

	public Ipage findByPageQuotaForCorp(Ipage ipage, ProjParm projParm)throws ServiceException;
	
	public List<Object>  findListBySts(String sts) throws ServiceException;
	
	public int count(ProjParm projParm) throws ServiceException;

}