package  app.creditapp.cred.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cred.entity.CifEval;
/**
* Title: CifEvalDao.java
* Description:
**/
public interface CifEvalDao {

	public CifEval getById(CifEval cifEval) throws DAOException;

	public void del(CifEval cifEval) throws DAOException;

	public void insert(CifEval cifEval) throws DAOException;

	public void update(CifEval cifEval) throws DAOException;
	
	public int cifScoreUpdate(CifEval cifEval) throws DAOException;
	
	public int getCount(CifEval cifEval) throws DAOException;

	public List<CifEval > findByPage(CifEval cifEval) throws DAOException;

	public String getKey()throws DAOException;

	//获取最新评分信息
	public CifEval getByMaxId(CifEval cifEval) throws DAOException;
}