package app.creditapp.dao;

import java.util.List;

import app.base.DAOException;

public interface CacheDAO {	
	public List<Object> findByCondition(Object object) throws DAOException;
	public List<String> findKeyByCondition() throws DAOException;

}
