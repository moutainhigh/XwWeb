package  app.creditapp.proj.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.proj.entity.ProjBase;
import app.creditapp.proj.entity.ProjMang;
/**
* Title: ProjMangDao.java
* Description:
**/
public interface ProjMangDao {

	public ProjMang getById(ProjMang projMang) throws DAOException;
	
	public List<ProjMang > getByIdLoginId(ProjMang projMang) throws DAOException;

	public void del(ProjMang projMang) throws DAOException;

	public void insert(ProjMang projMang) throws DAOException;

	public void update(ProjMang projMang) throws DAOException;
	
	public int getCount(ProjMang projMang) throws DAOException;

	public List<ProjMang > findByPage(ProjMang projMang) throws DAOException;
	
	public List<ProjMang > findByPageForManager(ProjMang projMang) throws DAOException;

	public String getKey()throws DAOException;
	public void updateforvw(ProjBase projBase) throws DAOException;

}