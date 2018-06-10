package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.PrdtBase;
/**
* Title: PrdtBaseDao.java
* Description:
**/
public interface PrdtBaseDao {

	public PrdtBase getById(PrdtBase prdtBase) throws DAOException;
	
	public PrdtBase getByPrdtNo(PrdtBase prdtBase) throws DAOException;

	public void del(PrdtBase prdtBase) throws DAOException;

	public void insert(PrdtBase prdtBase) throws DAOException;
	
	public void insertForCopy(PrdtBase prdtBase) throws DAOException;

	public void update(PrdtBase prdtBase) throws DAOException;
	
	public int getCount(PrdtBase prdtBase) throws DAOException;

	public List<PrdtBase > findByPage(PrdtBase prdtBase) throws DAOException;
	
	public int getCountPrd(PrdtBase prdtBase)throws DAOException;

	public PrdtBase getMaxNoByBrNo(PrdtBase prdtBase) throws DAOException;
}