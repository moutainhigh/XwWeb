package app.creditapp.proj.dao;

import java.util.List;
import java.util.Map;
import app.base.DAOException;
import app.creditapp.proj.entity.RptProj;

public interface RptProjDao {

	public RptProj getById(RptProj rptProj) throws DAOException;
	
	public RptProj getDailyById(RptProj rptProj) throws DAOException;

	public void delete(RptProj rptProj) throws DAOException;

	public String insert(RptProj rptProj) throws DAOException;
	
	public void insertBatch(final List<RptProj> rptProjList) throws DAOException;

	public void update(RptProj rptProj) throws DAOException;

	public int getCount(RptProj rptProj) throws DAOException;

	public List<RptProj> findByPage(Map map) throws DAOException;
	
	public List<RptProj> findByAll(RptProj rptProj) throws DAOException;
	
	public List<RptProj> findByAllNum(RptProj rptProj) throws DAOException;
	
	public List<RptProj> getAccountBal(RptProj rptProj) throws DAOException;
}
