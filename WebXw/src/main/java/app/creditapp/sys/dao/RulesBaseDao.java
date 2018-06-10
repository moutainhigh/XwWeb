package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.RulesBase;
/**
* Title: RulesBaseDao.java
* Description:
**/
public interface RulesBaseDao {

	public RulesBase getById(RulesBase rulesBase) throws DAOException;

	public void del(RulesBase rulesBase) throws DAOException;

	public void insert(RulesBase rulesBase) throws DAOException;

	public void update(RulesBase rulesBase) throws DAOException;
	
	public int getCount(RulesBase rulesBase) throws DAOException;

	public List<RulesBase > findByPage(RulesBase rulesBase) throws DAOException;

	public List<RulesBase > findById(RulesBase rulesBase) throws DAOException;

}