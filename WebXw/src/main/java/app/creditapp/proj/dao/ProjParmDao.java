package  app.creditapp.proj.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.proj.entity.ProjParm;
/**
* Title: ProjParmDao.java
* Description:
**/
public interface ProjParmDao {

	public ProjParm getById(ProjParm projParm) throws DAOException;

	public void del(ProjParm projParm) throws DAOException;

	public void insert(ProjParm projParm) throws DAOException;

	public void update(ProjParm projParm) throws DAOException;
	
	public int getCount(ProjParm projParm) throws DAOException;

	public List<ProjParm > findByPage(ProjParm projParm) throws DAOException;

	public int getCountQuotaForCorp(ProjParm projParm)throws DAOException;

	public List<ProjParm> findByPageQuotaForCorp(ProjParm projParm)throws DAOException;
	
	public List<Object>  findListBySts(String sts)throws DAOException;


}