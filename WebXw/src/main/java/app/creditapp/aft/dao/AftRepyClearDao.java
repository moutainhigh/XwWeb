package  app.creditapp.aft.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.aft.entity.AftRepyClear;
/**
* Title: AftRepyClearDao.java
* Description:
**/
public interface AftRepyClearDao {

	public AftRepyClear getById(AftRepyClear aftRepyClear) throws DAOException;

	public AftRepyClear getByWsId(AftRepyClear aftRepyClear) throws DAOException;

	public AftRepyClear getByPactNoIng(AftRepyClear aftRepyClear) throws DAOException;

	public void del(AftRepyClear aftRepyClear) throws DAOException;

	public void insert(AftRepyClear aftRepyClear) throws DAOException;

	public void update(AftRepyClear aftRepyClear) throws DAOException;
	
	public int getCount(AftRepyClear aftRepyClear) throws DAOException;

	public List<AftRepyClear > findByPage(AftRepyClear aftRepyClear) throws DAOException;
	
	public AftRepyClear getByPactnoAndPerdno(AftRepyClear aftRepyClear) throws DAOException;


}