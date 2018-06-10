package  app.creditapp.corp.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.corp.entity.CorpBase;
/**
* Title: CorpBaseDao.java
* Description:
**/
public interface CorpBaseDao {

	public CorpBase getById(CorpBase corpBase) throws DAOException;

	public void del(CorpBase corpBase) throws DAOException;

	public void insert(CorpBase corpBase) throws DAOException;

	public void update(CorpBase corpBase) throws DAOException;
	
	public int getCount(CorpBase corpBase) throws DAOException;

	public List<CorpBase > findByPage(CorpBase corpBase) throws DAOException;

	public String getKey()throws DAOException;
	//增加合作机构 通用方法 判断信息 是否填写完整
	public int getAllInf(String brNo) throws DAOException;

	public CorpBase getByBrNo(String brNo);

}