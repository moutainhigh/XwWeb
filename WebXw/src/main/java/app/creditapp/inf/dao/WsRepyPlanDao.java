package  app.creditapp.inf.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.inf.entity.WsIn2201_1;
import app.creditapp.inf.entity.WsIn2202;
import app.creditapp.inf.entity.WsIn2602_1;
import app.creditapp.inf.entity.WsIn2604_2;
import app.creditapp.inf.entity.WsIn2802_1;
import app.creditapp.inf.entity.WsIn2804_2;
import app.creditapp.inf.entity.WsOut2202;
import app.creditapp.inf.entity.WsRepyPlan;
/**
* Title: WsRepyPlanDao.java
* Description:
**/
public interface WsRepyPlanDao {

	public WsRepyPlan getById(WsRepyPlan wsRepyPlan) throws DAOException;

	public void insertBatch(final List<WsRepyPlan> wsRepyPlanlist) throws DAOException;
	
	public void del(WsRepyPlan wsRepyPlan) throws DAOException;

	public void insert(WsRepyPlan wsRepyPlan) throws DAOException;

	public void update(WsRepyPlan wsRepyPlan) throws DAOException;
	
	public int getCount(WsRepyPlan wsRepyPlan) throws DAOException;

	public List<WsRepyPlan > findByPage(WsRepyPlan wsRepyPlan) throws DAOException;
	
	public void insertBatchForList(final List<WsIn2201_1> wsIn2201_1list) throws DAOException;
	
	public List<WsOut2202> findByWsIn(WsIn2202 wsIn2202) throws DAOException;

	public List<WsRepyPlan> getByPactNo(WsRepyPlan wsRepyPlan) throws DAOException;

	public void delByPactNo(WsRepyPlan wsRepyPlan) throws DAOException;

	
	public void insertForList(final List<WsIn2602_1> wsIn2602_1list) throws DAOException;
	
	public void insertforList(final List<WsIn2604_2> wsIn2604_2list) throws DAOException;

	public void insertList(final List<WsIn2802_1> wsIn2802_1list) throws DAOException;

	public void insertlist(final List<WsIn2804_2> wsIn2804_2list) throws DAOException;
	
	//同一个合同中的每期应还本金之和 PRCP_AMT
	public double getPrcpAmtCount(WsRepyPlan wsRepyPlan) throws DAOException;
	
	//同一个合同的最大到期日
	public String getMaxEndDate(WsRepyPlan wsRepyPlan) throws DAOException;
	
	//取期次号（判断期次号连续）
	public List<WsRepyPlan> getByPactNoList(WsRepyPlan wsRepyPlan) throws DAOException;
	
	//取开始日和结束日（判断本期次起始日应该等于上一期的到期日）
//	public WsRepyPlan getBybegendDate(WsRepyPlan wsRepyPlan) throws DAOException;
	
	//取每一期的利息(必须大于等于0)  取开始日和结束日（判断本期次起始日应该等于上一期的到期日）
	public WsRepyPlan getByPactNoCnt(WsRepyPlan wsRepyPlan) throws DAOException;
}