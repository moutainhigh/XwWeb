package  app.creditapp.inf.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.inf.entity.AllocateRegWsIn;
/**
* Title: AllocateRegWsInDao.java
* Description:
**/
public interface AllocateRegWsInDao {

	public AllocateRegWsIn getById(AllocateRegWsIn allocateRegWsIn) throws DAOException;

	public void del(AllocateRegWsIn allocateRegWsIn) throws DAOException;

	public void insert(AllocateRegWsIn allocateRegWsIn) throws DAOException;

	public void update(AllocateRegWsIn allocateRegWsIn) throws DAOException;
	
	public int getCount(AllocateRegWsIn allocateRegWsIn) throws DAOException;
	
	public String getByid() throws DAOException;

	public List<AllocateRegWsIn > findByPage(AllocateRegWsIn allocateRegWsIn) throws DAOException;

}