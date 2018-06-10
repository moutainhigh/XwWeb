package  app.creditapp.sys.dao;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.sys.entity.InterfaceBackMeg;
/**
* Title: InterfaceBackMegDao.java
* Description:
**/
public interface InterfaceBackMegDao {

	public InterfaceBackMeg getById(InterfaceBackMeg interfaceBackMeg) throws DAOException;

	public void del(InterfaceBackMeg interfaceBackMeg) throws DAOException;

	public void insert(InterfaceBackMeg interfaceBackMeg) throws DAOException;

	public void update(InterfaceBackMeg interfaceBackMeg) throws DAOException;
	
	public int getCount(InterfaceBackMeg interfaceBackMeg) throws DAOException;

	public List<InterfaceBackMeg > findByPage(InterfaceBackMeg interfaceBackMeg) throws DAOException;

}