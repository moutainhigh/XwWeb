package app.creditapp.corp.dao;

import java.util.List;
import java.util.Map;
import app.base.DAOException;
import app.creditapp.corp.entity.RptCorpPrdt;

public interface RptCorpPrdtDao {

	public RptCorpPrdt getById(RptCorpPrdt rptCorpPrdt) throws DAOException;

	public void delete(RptCorpPrdt rptCorpPrdt) throws DAOException;

	public String insert(RptCorpPrdt rptCorpPrdt) throws DAOException;
	
	public void insertBatch(final List<RptCorpPrdt> rptCorpPrdtList) throws DAOException;

	public void update(RptCorpPrdt rptCorpPrdt) throws DAOException;

	public int getCount(RptCorpPrdt rptCorpPrdt) throws DAOException;

	public List<RptCorpPrdt> findByPage(Map map) throws DAOException;
	
	public List<RptCorpPrdt> findByAll(RptCorpPrdt rptCorpPrdt) throws DAOException;
}
