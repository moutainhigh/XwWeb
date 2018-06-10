package  app.creditapp.proj.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.inf.entity.AllocateRegWsIn;
import app.creditapp.proj.entity.ProjAcct;
import app.util.toolkit.Ipage;

/**
* Title: ProjAcctBo.java
* Description:
**/
public interface ProjAcctBo {

	public ProjAcct getById(ProjAcct projAcct) throws ServiceException;

	public void del(ProjAcct projAcct) throws ServiceException;

	public void insert(ProjAcct projAcct) throws ServiceException;

	public void update(ProjAcct projAcct) throws ServiceException;
	
	public void updateAcctBal(ProjAcct projAcct)throws ServiceException;
	
	public void updateSts(ProjAcct projAcct) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ProjAcct projAcct) throws ServiceException;
	
	public Ipage findByPageForPop(Ipage ipg, ProjAcct projAcct) throws ServiceException;
	
	public Ipage findByPageQuotaForCorp(Ipage ipage, ProjAcct projAcct)throws ServiceException;
	
	public Ipage findByProjNoAndAcctType(Ipage ipg, ProjAcct projAcct) throws ServiceException;
	
	public ProjAcct getByProjNoAndAcctType(ProjAcct projAcct) throws ServiceException;
	
	public ProjAcct virtual_AllocateReg(AllocateRegWsIn allocateRegWsIn) throws ServiceException;
    //定时任务自动调用
	public void auto_allocateRegWsIn(String projNo, double txAmt, String acctId, String opNo, String tradeCode) throws ServiceException;
	
	public AllocateRegWsIn insert_allocateRegWsIn(String projNo, double txAmt, String acctId, String opNo,String tradeCode) throws ServiceException;

	public int getCount(ProjAcct projAcct) throws ServiceException;
	//获取 该项目下的 专户和 虚拟账号信息
	public int getCountForProj(ProjAcct projAcct) throws ServiceException;//专户总数
	
	public String getProjId(ProjAcct projAcct) throws ServiceException;
	
	public void getInfForAcct(ProjAcct projAcct) throws ServiceException;
	
	public int getCountForX(ProjAcct projAcct) throws ServiceException;//虚拟户 总数
	
	public List<ProjAcct> findListByProjNo(ProjAcct projAcct) throws ServiceException;

	public int getByProjNo(ProjAcct acct)throws ServiceException;
}