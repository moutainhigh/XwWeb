package  app.creditapp.fund.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.fund.entity.FundProvProj;
import app.creditapp.bat.entity.RptPrdtDaily;
/**
* Title: FundProvProjDao.java
* Description:
**/
public interface FundProvProjDao {

	public FundProvProj getById(FundProvProj fundProvProj) throws DAOException;

	public void del(FundProvProj fundProvProj) throws DAOException;

	public void insert(FundProvProj fundProvProj) throws DAOException;

	public void update(FundProvProj fundProvProj) throws DAOException;
	
	public int getCount(FundProvProj fundProvProj) throws DAOException;

	public List<FundProvProj > findByPage(FundProvProj fundProvProj) throws DAOException;
	//获取项目对应累计放贷额
	public List<RptPrdtDaily > RptRrdtDailygetByPrdtNoamt(RptPrdtDaily rptPrdtDaily) throws DAOException;
	//时间段内不同产品的累计放贷金额
	public List<RptPrdtDaily > RptRrdtDailygetByprdtno(RptPrdtDaily rptPrdtDaily) throws DAOException;

	public double RptRrdtDailygetByProjNoamt(RptPrdtDaily rptPrdtDaily) throws DAOException;
	
	public RptPrdtDaily RptRrdtDailygetBymaxDate(RptPrdtDaily rptPrdtDaily) throws DAOException;
	//获取provProjNo
	public String getProvProjNo() throws DAOException;
	
	public String getProjNameByProjNo(String projNo) throws DAOException;

	public List<FundProvProj> findAll(FundProvProj fundProvProj) throws DAOException;
}