package app.creditapp.corp.dao;

import java.util.List;
import java.util.Map;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.corp.entity.RptCorpDaily;

public interface RptCorpDailyDao {

	public RptCorpDaily getById(RptCorpDaily rptCorpDaily) throws DAOException;

	public void delete(RptCorpDaily rptCorpDaily) throws DAOException;

	public String insert(RptCorpDaily rptCorpDaily) throws DAOException;
	
	public void insertBatch(final List<RptCorpDaily> rptCorpDailyList) throws DAOException;

	public void update(RptCorpDaily rptCorpDaily) throws DAOException;

	public int getCount(RptCorpDaily rptCorpDaily) throws DAOException;

	public List<RptCorpDaily> findByPage(Map map) throws DAOException;
	
	public List<RptCorpDaily> findByAll(Map map) throws DAOException;
	
	public List<RptCorpDaily> getList(RptCorpDaily rptCorpDaily) throws DAOException;

	public List<RptCorpDaily> findByAllNum(RptCorpDaily rptCorpDaily) throws DAOException;
	
	public RptCorpDaily findRpt(RptCorpDaily rptCorpDaily) throws DAOException;
	
}
