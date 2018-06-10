package  app.creditapp.ln.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsOut2006_1_1;
import app.creditapp.ln.entity.PreGage;
/**
* Title: PreGageDao.java
* Description:
**/
public interface PreGageDao {

	public PreGage getById(PreGage preGage) throws DAOException;

	public void del(PreGage preGage) throws DAOException;

	public void insert(PreGage preGage) throws DAOException;

	public void update(PreGage preGage) throws DAOException;
	
	public int getCount(PreGage preGage) throws DAOException;

	public List<PreGage > findByPage(PreGage preGage) throws DAOException;

	public int getCountQuotaForLn(PreGage preGage) throws DAOException;

	public List<PreGage> findByPageQuotaForLn(PreGage preGage) throws DAOException;
	
	public void insertfor2004_1(WsIn2004_1 wsIn2004_1) throws DAOException;
	
	public List<WsOut2006_1_1> getws2006_1_1list(PreGage preGage) throws DAOException;

}