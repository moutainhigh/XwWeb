package  app.creditapp.acc.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.entity.WsRepyFee;
import app.creditapp.inf.entity.WsIn2301_1_1;
import app.creditapp.inf.entity.WsIn2301_1_2;
/**
* Title: WsRepyFeeDao.java
* Description:
**/
public interface WsRepyFeeDao {

	public List<WsRepyFee> getFeeType(WsRepyFee wsRepyFee) throws DAOException;

	public void del(WsRepyFee wsRepyFee) throws DAOException;

	public void insert(WsRepyFee wsRepyFee) throws DAOException;

	public void update(WsRepyFee wsRepyFee) throws DAOException;
	
	public int getCount(WsRepyFee wsRepyFee) throws DAOException;
	
	public double getFeeAmt(WsRepyFee wsRepyFee) throws DAOException;

	//列表插入数据库ws2301接口使用
	public void insertBatchfor2301(List<WsIn2301_1_2> list2301_1_2list) throws DAOException;
	public List<WsRepyFee > findByPage(WsRepyFee wsRepyFee) throws DAOException;

}