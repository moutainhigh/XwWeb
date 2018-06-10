package  app.creditapp.cred.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.cred.entity.CorpEval;
/**
* Title: CorpEvalDao.java
**/
public interface CorpEvalDao {

	public CorpEval getById(CorpEval corpEval) throws DAOException;

	public void del(CorpEval corpEval) throws DAOException;

	public void insert(CorpEval corpEval) throws DAOException;

	public void update(CorpEval corpEval) throws DAOException;
	
	public int getCount(CorpEval corpEval) throws DAOException;

	public List<CorpEval > findByPage(CorpEval corpEval) throws DAOException;

	public String getKey()throws DAOException;

	//获取最新评级信息
	public CorpEval getByMaxId(CorpEval corpEval) throws DAOException;
}