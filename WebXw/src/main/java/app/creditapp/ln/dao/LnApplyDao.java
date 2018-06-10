package  app.creditapp.ln.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.ln.entity.LnApply;
import app.creditapp.ln.entity.LnApplyMid;
/**
* Title: LnApplyDao.java
* Description:
**/
public interface LnApplyDao {

	public LnApply getById(LnApply lnApply) throws DAOException;

	public void del(LnApply lnApply) throws DAOException;

	public void insert(LnApply lnApply) throws DAOException;

	public void update(LnApply lnApply) throws DAOException;
	
	public int getCount(LnApply lnApply) throws DAOException;

	public List<LnApply > findByPage(LnApply lnApply) throws DAOException;

	public String getKey()throws DAOException;

	public int resultIdUpdate(LnApply lnApply) throws DAOException;
}