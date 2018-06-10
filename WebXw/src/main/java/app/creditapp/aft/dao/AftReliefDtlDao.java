package  app.creditapp.aft.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.aft.entity.AftReliefDtl;
/**
* Title: AftReliefDtlDao.java
* Description:
**/
public interface AftReliefDtlDao {

	public AftReliefDtl getById(AftReliefDtl aftReliefDtl) throws DAOException;

	public void del(AftReliefDtl aftReliefDtl) throws DAOException;

	public void insert(AftReliefDtl aftReliefDtl) throws DAOException;

	public void update(AftReliefDtl aftReliefDtl) throws DAOException;
	
	public int getCount(AftReliefDtl aftReliefDtl) throws DAOException;

	public List<AftReliefDtl > findByPage(AftReliefDtl aftReliefDtl) throws DAOException;

}