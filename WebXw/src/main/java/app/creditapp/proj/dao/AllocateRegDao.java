package  app.creditapp.proj.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.proj.entity.AllocateReg;
/**
* Title: AllocateRegDao.java
* Description:
**/
public interface AllocateRegDao {

	public AllocateReg getById(AllocateReg allocateReg) throws DAOException;

	public void del(AllocateReg allocateReg) throws DAOException;

	public void insert(AllocateReg allocateReg) throws DAOException;

	public void update(AllocateReg allocateReg) throws DAOException;
	
	public int getCount(AllocateReg allocateReg) throws DAOException;

	public List<AllocateReg > findByPage(AllocateReg allocateReg) throws DAOException;

}