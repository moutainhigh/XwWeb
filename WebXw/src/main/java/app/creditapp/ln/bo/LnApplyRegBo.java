package  app.creditapp.ln.bo;

import app.base.ServiceException;
import app.creditapp.ln.entity.LnApplyReg;
import app.util.toolkit.Ipage;

/**
* Title: LnApplyRegBo.java
* Description:
**/
public interface LnApplyRegBo {

	public LnApplyReg getById(LnApplyReg lnApplyReg) throws ServiceException;

	public void del(LnApplyReg lnApplyReg) throws ServiceException;

	public void insert(LnApplyReg lnApplyReg) throws ServiceException;

	public void update(LnApplyReg lnApplyReg) throws ServiceException;
	//插入自动审批returnId
	public int resultIdUpdate(LnApplyReg lnApplyReg) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnApplyReg lnApplyReg) throws ServiceException;
	
	public String getByNo (LnApplyReg lnApplyReg) throws ServiceException;
	
	public LnApplyReg getBypactNo(LnApplyReg lnApplyReg) throws ServiceException;

	public void updateBypactNo(LnApplyReg lnApplyReg);

}