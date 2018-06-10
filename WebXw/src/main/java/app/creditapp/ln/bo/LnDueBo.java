package  app.creditapp.ln.bo;

import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.ln.entity.LnDue;
import app.util.toolkit.Ipage;

/**
* Title: LnDueBo.java
* Description:
**/
public interface LnDueBo {

	public LnDue getById(LnDue lnDue) throws ServiceException;
	public LnDue getByIdForPactNo(LnDue lnDue) throws ServiceException;
	
	public LnDue getByPactId(String pactId) throws ServiceException;
	
	public LnDue getByPactNoAndBrNo(LnDue lnDue) throws DAOException;

	public void del(LnDue lnDue) throws ServiceException;

	public void insert(LnDue lnDue) throws ServiceException;

	public void update(LnDue lnDue) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnDue lnDue) throws ServiceException;
	public Ipage findByPageForPop(Ipage ipg, LnDue lnDue) throws ServiceException;
	
	public Ipage findByPageForRead(Ipage ipg, LnDue lnDue) throws ServiceException;
	
	public Ipage findAllFail(Ipage ipg, LnDue lnDue) throws ServiceException;
	
	public List<LnDue> findList() throws ServiceException;

	public List<LnDue> findListToWorkF() throws ServiceException;
	
	public int dueStsUpdate(LnDue lnDue) throws ServiceException;
	
	public List<LnDue> findListByFundNo(LnDue lnDue) throws ServiceException;
	public void getLnDueList() throws ServiceException;
	//抽查回访获取列表
	public Ipage getCheckList(Ipage ipg, LnDue lnDue) throws ServiceException;
	//导出台账excel
}