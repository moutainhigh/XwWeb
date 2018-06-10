package  app.creditapp.acc.log.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.log.entity.AcLnIntstLog;
/**
* Title: AcLnIntstLogDao.java
* Description:
**/
public interface AcLnIntstLogDao {

	public AcLnIntstLog getById(AcLnIntstLog acLnIntstLog) throws DAOException;

	public void del(AcLnIntstLog acLnIntstLog) throws DAOException;

	public void insert(AcLnIntstLog acLnIntstLog) throws DAOException;

	public void update(AcLnIntstLog acLnIntstLog) throws DAOException;
	
	public int getCount(AcLnIntstLog acLnIntstLog) throws DAOException;

	public List<AcLnIntstLog > findByPage(AcLnIntstLog acLnIntstLog) throws DAOException;

}