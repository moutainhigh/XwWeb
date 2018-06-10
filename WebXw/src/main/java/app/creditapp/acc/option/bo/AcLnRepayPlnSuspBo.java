package  app.creditapp.acc.option.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.option.entity.AcLnRepayPlnSusp;
import app.creditapp.inf.entity.WsRepyPlan;

/**
* Title: AcLnRepayPlnSuspBo.java
* Description:
**/
public interface AcLnRepayPlnSuspBo {

	public AcLnRepayPlnSusp getById(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException;

	public void del(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException;

	public void insert(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException;

	public void update(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException;

	//还款计划 业务逻辑的合法性判断
	/**
	 * 对获取到的AcLnRepayPln集合进业务逻辑的合法性判断，仅将符合要求的集合进行返回，不符合要求的数据将会被拦截
	1、同一个合同中的每期应还本金之和=合同表（ac_ln_mst）的贷款主表放款金额loan_amt。
	2、同一个合同的最大到期日=合同表（ac_ln_mst）的到期日end_date。
	3、同一个合同的期次号必须是连续的。
	5、每一期的利息必须大于等于0。
	 * @param acrpList
	 * @return
	 * @throws Exception
	 */
	public boolean validAcLnRepayPlnSusp(String acLnRepayPlnSuspBatch , String brNo) throws ServiceException;
	
	public List<String> getByBatchDisPact(String wsRepyPlanBatch) throws ServiceException;
	
	public void delByPactnoAndBrno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException;
	
	 public List<WsRepyPlan> getListByBatchAndPactno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws ServiceException;
}