package app.creditapp.corp.dao;

import java.util.List;
import java.util.Map;
import app.base.DAOException;
import app.creditapp.corp.entity.RptCorpArea;

public interface RptCorpAreaDao {

	public RptCorpArea getById(RptCorpArea rptCorpArea) throws DAOException;

	public void delete(RptCorpArea rptCorpArea) throws DAOException;

	public String insert(RptCorpArea rptCorpArea) throws DAOException;
	
	public void insertBatch(final List<RptCorpArea> rptCorpAreaList) throws DAOException;

	public void update(RptCorpArea rptCorpArea) throws DAOException;

	public int getCount(RptCorpArea rptCorpArea) throws DAOException;

	public List<RptCorpArea> findByPage(Map map) throws DAOException;
	
	public List<RptCorpArea> findByAll(RptCorpArea rptCorpArea) throws DAOException;
}
