package  app.creditapp.acc.log.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.acc.log.entity.AcTraceLog;
import app.creditapp.inf.entity.WsOut3202_1;
/**
* Title: AcTraceLogDao.java
* Description:
**/
public interface AcTraceLogDao {

	public AcTraceLog getById(AcTraceLog acTraceLog) throws DAOException;

	public AcTraceLog getByLoanNoAndTxCde(AcTraceLog acTraceLog) throws DAOException;

	public AcTraceLog getRecentTraceLog(AcTraceLog acTraceLog) throws DAOException;

	public void del(AcTraceLog acTraceLog) throws DAOException;

	public void insert(AcTraceLog acTraceLog) throws DAOException;

	public void update(AcTraceLog acTraceLog) throws DAOException;
	
	public int getCount(AcTraceLog acTraceLog) throws DAOException;

	public int getCountForTask(AcTraceLog acTraceLog) throws DAOException;
	
	public int getCountForRead(AcTraceLog acTraceLog) throws DAOException;

	public List<AcTraceLog > findByPage(AcTraceLog acTraceLog) throws DAOException;
	
	public List<AcTraceLog > findByPage_Read(AcTraceLog acTraceLog) throws DAOException;
	
	public List<AcTraceLog > findByPageForTask(AcTraceLog acTraceLog) throws DAOException;

	public String getKey()throws DAOException;
	//接口ws3202查询返回列表
	public List<WsOut3202_1> getlistforws3202(AcTraceLog acTraceLog) throws DAOException;
	//接口ws3202查询返回符合条件的信息条数
	public int getCountforws3202(AcTraceLog acTraceLog) throws DAOException;


}