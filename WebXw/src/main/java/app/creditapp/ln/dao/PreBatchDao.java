package  app.creditapp.ln.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.inf.entity.WsOut2005_1;
import app.creditapp.ln.entity.PreBatch;
/**
* Title: PreBatchDao.java
* Description:
**/
public interface PreBatchDao {

	public PreBatch getById(PreBatch preBatch) throws DAOException;

	public void del(PreBatch preBatch) throws DAOException;

	public void insert(PreBatch preBatch) throws DAOException;

	public void update(PreBatch preBatch) throws DAOException;
	
	public void updateNum(PreBatch preBatch) throws DAOException;
	
	public int getCount(PreBatch preBatch) throws DAOException;

	public List<PreBatch > findByPage(PreBatch preBatch) throws DAOException;

	public PreBatch getByPreApply(PreBatch preBatch) throws DAOException;

	public  int getCountforlistWs(PreBatch preBatch) throws DAOException;
	//接口2005根据map获取分页列表	
	public  List<WsOut2005_1 > findByPageforlistWs(Map map) throws DAOException;

	public List<PreBatch> findByPage(Map map) throws DAOException;
}