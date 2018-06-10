package  app.creditapp.ln.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.ln.entity.LnApplyReg;
/**
* Title: LnApplyRegDao.java
* Description:
**/
public interface LnApplyRegDao {

	public LnApplyReg getById(LnApplyReg lnApplyReg) throws DAOException;

	public void del(LnApplyReg lnApplyReg) throws DAOException;

	public void insert(LnApplyReg lnApplyReg) throws DAOException;

	public void update(LnApplyReg lnApplyReg) throws DAOException;
	
	public int resultIdUpdate(LnApplyReg lnApplyReg) throws DAOException;
	
	public int getCount(LnApplyReg lnApplyReg) throws DAOException;

	public List<LnApplyReg > findByPage(LnApplyReg lnApplyReg) throws DAOException;

	public String getKey()throws DAOException;
	//接口 5101 账户开户使用
	public LnApplyReg getByPact(LnApplyReg lnApplyReg) throws DAOException;

	public String getByNo(LnApplyReg lnApplyReg);

	public LnApplyReg getBypactNo(LnApplyReg lnApplyReg);
	
	public void updateBypactNo(LnApplyReg lnApplyReg);

}