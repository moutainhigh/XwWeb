package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.ParmRewRule;
/**
* Title: ParmRewRuleDao.java
* Description:
**/
public interface ParmRewRuleDao {

	public ParmRewRule getById(ParmRewRule parmRewRule) throws DAOException;

	public void del(ParmRewRule parmRewRule) throws DAOException;

	public void insert(ParmRewRule parmRewRule) throws DAOException;

	public void update(ParmRewRule parmRewRule) throws DAOException;
	
	public void updateRuleSts(ParmRewRule parmRewRule) throws DAOException;
	
	public int getCount(ParmRewRule parmRewRule) throws DAOException;

	public List<ParmRewRule > findByPage(ParmRewRule parmRewRule) throws DAOException;
	
	public List<ParmRewRule> findAll(ParmRewRule parmRewRule) throws DAOException;

}