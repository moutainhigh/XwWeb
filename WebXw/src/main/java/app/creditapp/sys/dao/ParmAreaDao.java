package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.ParmArea;
/**
* Title: ParmAreaDao.java
* Description:
**/
public interface ParmAreaDao {

	public ParmArea getById(ParmArea parmArea) throws DAOException;

	public void del(ParmArea parmArea) throws DAOException;

	public void insert(ParmArea parmArea) throws DAOException;

	public void update(ParmArea parmArea) throws DAOException;
	
	public int getCount(ParmArea parmArea) throws DAOException;

	public List<ParmArea> findByPage(ParmArea parmArea) throws DAOException;
	
	public ParmArea getTreeTop()throws DAOException;
	
	public List<ParmArea> getChildren(String areaLev)throws DAOException;
	
	public ParmArea getByDeptid(ParmArea parmArea) throws DAOException;
	
	public List<ParmArea> getByUpOne(String upOne) throws DAOException;


}