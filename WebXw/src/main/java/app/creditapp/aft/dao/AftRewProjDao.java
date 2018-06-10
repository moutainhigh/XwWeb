package  app.creditapp.aft.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.aft.entity.AftRewProj;
/**
* Title: AftRewProjDao.java
* Description:
**/
public interface AftRewProjDao {

	public AftRewProj getById(AftRewProj aftRewProj) throws DAOException;

	public void del(AftRewProj aftRewProj) throws DAOException;

	public void insert(AftRewProj aftRewProj) throws DAOException;

	public void update(AftRewProj aftRewProj) throws DAOException;
	
	public int getCount(AftRewProj aftRewProj) throws DAOException;

	public List<AftRewProj > findByPage(AftRewProj aftRewProj) throws DAOException;
	public List<AftRewProj > findByList(AftRewProj aftRewProj) throws DAOException;

	public void updateSts(AftRewProj aftRewProj)throws DAOException;

	public void updateDealRest(AftRewProj aftRewProj)throws DAOException;

	public List<AftRewProj> findByDateBetween(Map<String, String> paramMap)throws DAOException;
}