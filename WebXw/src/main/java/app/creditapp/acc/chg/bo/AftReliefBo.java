package  app.creditapp.acc.chg.bo;

import app.base.ServiceException;
import app.creditapp.acc.chg.entity.AftRelief;
import app.creditapp.wkf.entity.WkfParm;
import app.util.toolkit.Ipage;

/**
* Title: AftReliefBo.java
* Description:
**/
public interface AftReliefBo {

	public AftRelief getById(AftRelief aftRelief) throws ServiceException;
	public AftRelief getByIdForTrace(AftRelief aftRelief) throws ServiceException;

	public void del(AftRelief aftRelief) throws ServiceException;

	public void insert(AftRelief aftRelief) throws ServiceException;

	public void update(AftRelief aftRelief) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftRelief aftRelief) throws ServiceException;
	
	public Ipage findByPageForTask(Ipage ipg, AftRelief aftRelief) throws ServiceException;

	public void execAftRelief(AftRelief aftRelief) throws ServiceException;

	public String doSubmit(WkfParm parm ,AftRelief aftRelief,String opNo, String brNo) throws ServiceException;

	public void wsInsertAftRelief(AftRelief aftRelief) throws ServiceException;
	
	public String getRefId() throws ServiceException;


}