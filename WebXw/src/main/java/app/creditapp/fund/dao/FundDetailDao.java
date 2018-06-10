package  app.creditapp.fund.dao;

import java.util.HashMap;
import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundDetail;
/**
* Title: FundDetailDao.java
* Description:
**/
public interface FundDetailDao {

	public FundDetail getById(FundDetail fundDetail) throws DAOException;

	public void del(FundDetail fundDetail) throws DAOException;

	public void insert(FundDetail fundDetail) throws DAOException;
	//数据新增引起其他变化
	public void insert_after(FundDetail fundDetail) throws DAOException;

	public void update(FundDetail fundDetail) throws DAOException;
	
	public int getCount(FundDetail fundDetail) throws DAOException;

	public List<FundDetail > findByPage(FundDetail fundDetail) throws DAOException;
	//查看资金现金金额是否满足历史兑付流水
	public int findByhisfund(FundDetail fundDetail) throws DAOException;
	//调用自动赎买接口
	public void reDeem(HashMap HashMap) throws DAOException;
	
	public double getBalBytradeType(FundDetail fundDetail) throws DAOException;
	
	//获取明细表的最迟日期
	public String getMaxDate(FundDetail fundDetail) throws DAOException;
}