package  app.creditapp.acc.loan.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.loan.entity.AcLoanBackLog;
/**
* Title: AcLoanBackLogDao.java
* Description:
**/
public interface AcLoanBackLogDao {

	public AcLoanBackLog getById(AcLoanBackLog acLoanBackLog) throws DAOException;

	public void del(AcLoanBackLog acLoanBackLog) throws DAOException;
	
	public List<AcLoanBackLog> reconciZf(String timeSec) throws DAOException;

	public void insert(AcLoanBackLog acLoanBackLog) throws DAOException;

	public void update(AcLoanBackLog acLoanBackLog) throws DAOException;
	
	public void updateSts(AcLoanBackLog acLoanBackLog) throws DAOException;
	
	public AcLoanBackLog getByLoanNoAndStsType(AcLoanBackLog acLoanBackLog) throws DAOException;

	public int getCount(AcLoanBackLog acLoanBackLog) throws DAOException;

	public List<AcLoanBackLog > findByPage(AcLoanBackLog acLoanBackLog) throws DAOException;
	
	public List<AcLoanBackLog > getListByLogNoAndType(AcLoanBackLog acLoanBackLog) throws DAOException;

	public List<AcLoanBackLog > getListByBackLogNo(AcLoanBackLog acLoanBackLog) throws DAOException;
	
	public AcLoanBackLog getByBackCnt(AcLoanBackLog acLoanBackLog) throws DAOException;

}