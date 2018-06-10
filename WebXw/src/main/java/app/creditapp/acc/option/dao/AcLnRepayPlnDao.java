package  app.creditapp.acc.option.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.acc.option.entity.AcLnRepayPln;
/**
* Title: AcLnRepayPlnDao.java
**/
public interface AcLnRepayPlnDao {

	public AcLnRepayPln getById(AcLnRepayPln acLnRepayPln) throws DAOException;
	
	//取还款计划表最大的perd_no
	public int getByIdMaxPerdNo(AcLnRepayPln acLnRepayPln) throws DAOException;

	public void del(AcLnRepayPln acLnRepayPln) throws DAOException;

	public void insert(AcLnRepayPln acLnRepayPln) throws DAOException;

	public void update(AcLnRepayPln acLnRepayPln) throws DAOException;
	
	public int getCount(AcLnRepayPln acLnRepayPln) throws DAOException;

	public List<AcLnRepayPln > findByPage(AcLnRepayPln acLnRepayPln) throws DAOException;
	//根据合同号返回对应的数据
	public AcLnRepayPln getByIdforpact(AcLnRepayPln acLnRepayPln) throws DAOException;
	
	//获取贷款的应收本金、利息、已还本金、已还正常利息
	public AcLnRepayPln getAllAmt(AcLnRepayPln acLnRepayPln) throws DAOException;
	public List<AcLnRepayPln> getListByLoanNo(AcLnRepayPln acLnRepayPln) throws DAOException;

	public AcLnRepayPln getByBrNoPactNoPerdNo(AcLnRepayPln acLnRepayPln) throws DAOException;

   

}