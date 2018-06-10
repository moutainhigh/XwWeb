package  app.creditapp.acc.loan.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.acc.loan.entity.AcLnRepayPlnCur;
/**
* Title: AcLnRepayPlnCurDao.java
* Description:
**/
public interface AcLnRepayPlnCurDao {

	public AcLnRepayPlnCur getById(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;
	
	public AcLnRepayPlnCur getByPactNoAndDt(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;

	public void del(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;

	public void insert(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;

	public void update(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;
	
	public int getCount(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;

	public List<AcLnRepayPlnCur > findByPage(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;
	
	//新增根据合同号和机构号关联查询 20160801 sunmingyi
	public AcLnRepayPlnCur getByPactNo(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;

	public AcLnRepayPlnCur getCurByCnt(AcLnRepayPlnCur acLnRepayPlnCur);
	
	//根据合同号查 当期还款计划表中的应收本金-已还本金
//	public int pactAmt_repayPactAmt(AcLnRepayPlnCur acLnRepayPlnCur) throws DAOException;

}