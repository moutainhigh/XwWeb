package  app.creditapp.sys.bo;

import java.util.List;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.PrdtBase;

/**
* Title: PrdtBaseBo.java
* Description:
**/
public interface PrdtBaseBo {

	public PrdtBase getById(PrdtBase prdtBase) throws ServiceException;
	
	public PrdtBase getByPrdtNo(PrdtBase prdtBase) throws ServiceException;

	public void del(PrdtBase prdtBase) throws ServiceException;

	public void insert(PrdtBase prdtBase) throws ServiceException;
	
	public void insertForCopy(PrdtBase prdtBase) throws ServiceException;

	public void update(PrdtBase prdtBase) throws ServiceException;

	public Ipage findByPage(Ipage ipg, PrdtBase prdtBase) throws ServiceException;
	
	public int getCountPrd(PrdtBase prdtBase)throws ServiceException;

	public List<PrdtBase> findWkfApprovalRole(PrdtBase prdtBase);

	public PrdtBase getMaxNoByBrNo(PrdtBase prdtBase) throws ServiceException;
}