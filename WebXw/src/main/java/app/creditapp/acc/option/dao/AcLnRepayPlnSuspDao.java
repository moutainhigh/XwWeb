package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcLnRepayPlnSusp;
import app.creditapp.inf.entity.WsRepyPlan;
/**
* Title: AcLnRepayPlnSuspDao.java
* Description:
**/
public interface AcLnRepayPlnSuspDao {

	public AcLnRepayPlnSusp getById(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;

	public void del(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;

	public void delByPactnoAndBrno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;

	public void insert(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;

	public void update(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;
	
	public int getCount(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;

	public List<AcLnRepayPlnSusp > findByPage(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;
	
	//根据批次取出list进行还款计划上传的逻辑判断：
	public List<WsRepyPlan> getByWsRepyPlanList(String wsRepyPlanBatch) throws DAOException;
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-8
	 * @描述	根据批次号查询所有合同（去重）
	 */
	public List<String> getByBatchDisPact(String wsRepyPlanBatch) throws DAOException;
	
	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-8
	 * @描述	根据批次号与合同号  查询还款计划
	 */
	public List<WsRepyPlan> getListByBatchAndPactno(AcLnRepayPlnSusp acLnRepayPlnSusp) throws DAOException;


}