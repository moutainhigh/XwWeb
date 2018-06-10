package  app.creditapp.ln.bo;

import app.base.ServiceException;
import app.creditapp.ln.entity.LnApply;
import app.util.toolkit.Ipage;

/**
* Title: LnApplyBo.java
* Description:
**/
public interface LnApplyBo {

	public LnApply getById(LnApply lnApply) throws ServiceException;

	public void del(LnApply lnApply) throws ServiceException;

	public void insert(LnApply lnApply) throws ServiceException;

	public void update(LnApply lnApply) throws ServiceException;
	
	//插入自动审批returnId
	public int resultIdUpdate(LnApply lnApply) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnApply lnApply) throws ServiceException;

}