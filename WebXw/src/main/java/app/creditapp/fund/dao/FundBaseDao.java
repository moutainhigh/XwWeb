package  app.creditapp.fund.dao;

import java.util.HashMap;
import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundBase;
/**
* Title: FundBaseDao.java
* Description:
**/
public interface FundBaseDao {

	public FundBase getById(FundBase fundBase) throws DAOException;

	public void del(FundBase fundBase) throws DAOException;

	public void insert(FundBase fundBase) throws DAOException;

	public void update(FundBase fundBase) throws DAOException;
	//转让更新
	public void updateBytransts(FundBase fundBase) throws DAOException;
	
	public int getCount(FundBase fundBase) throws DAOException;
	//获取不符合规定的条数
	public int getCountunmatched(FundBase fundBase) throws DAOException;
	
//	public void insertadd() throws DAOException;
	
	//获取不符合规定的条数
	public int getCountunmatcheds(FundBase fundBase) throws DAOException;
	//资金清算提醒
	public int getFundRepayRemind(FundBase fundBase) throws DAOException;
	
	public String getmaxtxDate(FundBase fundBase) throws DAOException;
	
	public String getmaxtxDateSingle(FundBase fundBase) throws DAOException ;

	public List<FundBase > findByPage(FundBase fundBase) throws DAOException;
	
	public List<FundBase > echarts(FundBase fundBase) throws DAOException;
    //获取按资金性质划分，获取资金规模
	public Double getByfdTypeCount(FundBase fundBase) throws DAOException;
    //获取按资金性质划分，获取现金规模
	public Double getByfdTypeCashbal(FundBase fundBase) throws DAOException;
	//同步数据
	public void sync(HashMap HashMap) throws DAOException;
	//同步单笔资金
	public void syncSingle(HashMap HashMap) throws DAOException;
	//查询失效的资金
	public List<FundBase> findByFdflag(FundBase fundBase) throws DAOException;

	public void updateCash(FundBase fundBase) throws DAOException;
}