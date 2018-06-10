package  app.creditapp.acc.log.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.log.entity.AcChrgLog;
/**
* Title: AcChrgLogDao.java
* Description:
**/
public interface AcChrgLogDao {

	public AcChrgLog getById(AcChrgLog acChrgLog) throws DAOException;
	
	public AcChrgLog getAllAmt(AcChrgLog acChrgLog) throws DAOException;

	public AcChrgLog getLoFeeMinPerd(AcChrgLog acChrgLog) throws DAOException;

	public void del(AcChrgLog acChrgLog) throws DAOException;

	public void insert(AcChrgLog acChrgLog) throws DAOException;

	public void update(AcChrgLog acChrgLog) throws DAOException;
	
	public int getCount(AcChrgLog acChrgLog) throws DAOException;

	public List<AcChrgLog > findByPage(AcChrgLog acChrgLog) throws DAOException;
	
	public double getLoFeeAmt(AcChrgLog acChrgLog) throws DAOException;
	
	public List<AcChrgLog> getLoFeeList(AcChrgLog acChrgLog) throws DAOException;
	
	public List<AcChrgLog> getListByPerdNo(AcChrgLog acChrgLog) throws DAOException;
	
	public List<AcChrgLog> getListByTraceno(AcChrgLog acChrgLog) throws DAOException;



}