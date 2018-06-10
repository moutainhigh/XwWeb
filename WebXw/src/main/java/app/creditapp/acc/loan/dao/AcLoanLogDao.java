package  app.creditapp.acc.loan.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.loan.entity.AcLoanLog;
/**
* Title: AcLoanLogDao.java
* Description:
**/
public interface AcLoanLogDao {

	public AcLoanLog getById(AcLoanLog acLoanLog) throws DAOException;

	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-9-5
	 * @描述	根据放款账号查询放款日志中成功信息
	 */
	public AcLoanLog getSuccessByAcno(AcLoanLog acLoanLog) throws DAOException;

	public void del(AcLoanLog acLoanLog) throws DAOException;

	public void insert(AcLoanLog acLoanLog) throws DAOException;

	public void update(AcLoanLog acLoanLog) throws DAOException;
	
	public int getCount(AcLoanLog acLoanLog) throws DAOException;

	public List<AcLoanLog > findByPage(AcLoanLog acLoanLog) throws DAOException;

	/**
	 * 
	 * @作者 DHCC-LIUJ
	 * @日期 2016-8-22
	 * @描述	根据放款账号及借据号查询发送中或放款成功数据
	 */
	public List<AcLoanLog > getListByAcnoAndLoanno(AcLoanLog acLoanLog) throws DAOException;

	public List<AcLoanLog > getListByTraceNo(AcLoanLog acLoanLog) throws DAOException;

}