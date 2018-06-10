package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcLnSetlmtLog;
/**
* Title: AcLnSetlmtLogDao.java
* Description:
**/
public interface AcLnSetlmtLogDao {

	public AcLnSetlmtLog getById(AcLnSetlmtLog acLnSetlmtLog) throws DAOException;

	public void del(AcLnSetlmtLog acLnSetlmtLog) throws DAOException;

	public void insert(AcLnSetlmtLog acLnSetlmtLog) throws DAOException;

	public void update(AcLnSetlmtLog acLnSetlmtLog) throws DAOException;
	
	public int getCount(AcLnSetlmtLog acLnSetlmtLog) throws DAOException;

	public List<AcLnSetlmtLog > findByPage(AcLnSetlmtLog acLnSetlmtLog) throws DAOException;

}