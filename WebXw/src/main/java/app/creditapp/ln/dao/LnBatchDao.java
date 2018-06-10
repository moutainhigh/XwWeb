package  app.creditapp.ln.dao;

import java.util.List;
import java.util.Map;
import app.creditapp.inf.entity.WsOut2103_1;
import app.base.DAOException;
import app.creditapp.ln.entity.LnBatch;
/**
* Title: LnBatchDao.java
* Description:
**/
public interface LnBatchDao {

	public LnBatch getById(LnBatch lnBatch) throws DAOException;

	public void del(LnBatch lnBatch) throws DAOException;

	public void insert(LnBatch lnBatch) throws DAOException;

	public void update(LnBatch lnBatch) throws DAOException;
	
	public void updateNum(LnBatch lnBatch) throws DAOException;

	public int getCount(LnBatch lnBatch) throws DAOException;

	public List<LnBatch > findByPage(LnBatch lnBatch) throws DAOException;
	
//	public List<LnBatch> findByPage(Map map) throws DAOException;
	
	public int getCountforlist(LnBatch lnBatch) throws DAOException;		

	public List<WsOut2103_1> findByPageforlist(Map map) throws DAOException; 
	
	public LnBatch getByLnApplyMid(LnBatch lnBatch) throws DAOException;

}