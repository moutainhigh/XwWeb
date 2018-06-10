package  app.creditapp.ln.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.ln.entity.LnStage;
/**
* Title: LnStageDao.java
* Description:
**/
public interface LnStageDao {

	public LnStage getById(LnStage lnStage) throws DAOException;

	public void del(LnStage lnStage) throws DAOException;

	public void insert(LnStage lnStage) throws DAOException;

	public void update(LnStage lnStage) throws DAOException;
	
	public int getCount(LnStage lnStage) throws DAOException;

	public List<LnStage > findByPage(LnStage lnStage) throws DAOException;

}