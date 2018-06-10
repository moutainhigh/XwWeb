package  app.creditapp.corp.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.corp.entity.CorpTaRel;
import app.creditapp.corp.entity.CorpTaRelforcif;
/**
* Title: CorpTaRelDao.java
* Description:
**/
public interface CorpTaRelDao {

	public CorpTaRel getById(CorpTaRel corpTaRel) throws DAOException;

	public void del(CorpTaRel corpTaRel) throws DAOException;

	public void insert(CorpTaRel corpTaRel) throws DAOException;

	public void update(CorpTaRel corpTaRel) throws DAOException;
	
	public int getCount(CorpTaRel corpTaRel) throws DAOException;

	public List<CorpTaRel > findByPage(CorpTaRel corpTaRel) throws DAOException;
	//通过dblink向软通ta表插如数据
	public void insertforCorp(CorpTaRel corpTaRel) throws DAOException;
	//通过dblink向软通ta表删除重复数据
	public void deleteforDblink() throws DAOException;
	//通过brNo进行查询符合条件的客户号
	public List getCifNo(CorpTaRel corpTaRel) throws DAOException;
	//通过brNo查找视图中所有符合条件的用户号
	public List<CorpTaRelforcif> getByBrNoForDb(CorpTaRel corpTaRel) throws DAOException;

}