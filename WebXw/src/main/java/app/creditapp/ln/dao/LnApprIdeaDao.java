package  app.creditapp.ln.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.ln.entity.LnApprIdea;
/**
* Title: LnApprIdeaDao.java
* Description:
**/
public interface LnApprIdeaDao {

	public LnApprIdea getById(LnApprIdea lnApprIdea) throws DAOException;

	public void del(LnApprIdea lnApprIdea) throws DAOException;

	public void insert(LnApprIdea lnApprIdea) throws DAOException;

	public void update(LnApprIdea lnApprIdea) throws DAOException;
	
	public int getCount(LnApprIdea lnApprIdea) throws DAOException;
	public int getCountForRead(LnApprIdea lnApprIdea) throws DAOException;
	
	public List<LnApprIdea > findByPage(LnApprIdea lnApprIdea) throws DAOException;
	public List<LnApprIdea > findByPageForRead(LnApprIdea lnApprIdea) throws DAOException;
	
	public List<LnApprIdea > findByPageForAppId(LnApprIdea lnApprIdea) throws DAOException;

}