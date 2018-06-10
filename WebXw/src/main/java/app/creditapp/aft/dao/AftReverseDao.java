package  app.creditapp.aft.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.aft.entity.AftReverse;
/**
* Title: AftReverseDao.java
* Description:
**/
public interface AftReverseDao {

	public AftReverse getById(AftReverse aftReverse) throws DAOException;
	public AftReverse getByIdForRead(AftReverse aftReverse) throws DAOException;

	public void del(AftReverse aftReverse) throws DAOException;

	public void insert(AftReverse aftReverse) throws DAOException;

	public void update(AftReverse aftReverse) throws DAOException;
	
	public void updateReveSts(AftReverse aftReverse) throws DAOException;
	
	public int getCount(AftReverse aftReverse) throws DAOException;

	public List<AftReverse > findByPage(AftReverse aftReverse) throws DAOException;

}