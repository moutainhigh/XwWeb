package  app.creditapp.pact.bo;

import java.util.List;
import java.util.Map;

import app.base.ServiceException;
import app.creditapp.pact.entity.LnPact;
import app.creditapp.wkf.entity.WkfParm;
import app.util.toolkit.Ipage;

/**
* Title: LnPactBo.java
* Description:
**/
public interface LnPactBo {

	public LnPact getById(LnPact lnPact) throws ServiceException;
	
	public LnPact getByIdForAppId(LnPact lnPact) throws ServiceException;
	
	public List<LnPact> getByIdForBatchNo(LnPact lnPact) throws ServiceException;
	
	public List<LnPact> getWfTaskList(LnPact lnPact) throws ServiceException;

	public List<LnPact> getByIdForApp(LnPact lnPact,Ipage ipage,String userId,String branchId) throws ServiceException;
	
	public LnPact getByIdForAppr(LnPact lnPact) throws ServiceException;
	
	public LnPact getByIdForAppAndBatch(LnPact lnPact) throws ServiceException;
	
	public LnPact getByIdForList(LnPact lnPact) throws ServiceException;
	
	public LnPact getByAppId(LnPact lnPact) throws ServiceException;
	
	public void del(LnPact lnPact) throws ServiceException;

	public void insert(LnPact lnPact) throws ServiceException;

	public void update(LnPact lnPact) throws ServiceException;

	/**
	 * 客户的合同信息
	 * @param ipg
	 * @param lnPact
	 * @return
	 * @throws ServiceException
	 */
	public Ipage findByPage(Ipage ipg, LnPact lnPact) throws ServiceException;
	
	public Ipage findByPageForTask(Ipage ipg, LnPact lnPact) throws ServiceException;
	
	public Ipage findByPageForList(Ipage ipg, LnPact lnPact) throws ServiceException;
	
	public List<LnPact> findByPageForAppr(LnPact lnPact) throws ServiceException;
	
	public List<LnPact> findByPageForBatchNo(LnPact lnPact) throws ServiceException;
	
	public List<LnPact> findByPageAppr(LnPact lnPact) throws ServiceException;
	
	public String doSubmitUpdate(WkfParm parm ,LnPact lnPact,String opNo, String brNo) throws ServiceException;
	
	public Ipage findByPageForRel(Ipage ipg, LnPact lnPact) throws ServiceException;

	public Ipage findByPageQuotaForCif(Ipage ipage, LnPact lnPact) throws ServiceException;
	
	public void doReplace(Map<String, String> map) throws ServiceException;

	public LnPact getPactNoAndBrNo(LnPact lnPact) throws ServiceException;
	
	public List<LnPact> findListToWorkE() throws ServiceException;
		
}