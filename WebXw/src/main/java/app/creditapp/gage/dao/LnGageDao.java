package  app.creditapp.gage.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.gage.entity.LnGage;
/**
* Title: LnGageDao.java
* Description:
**/
public interface LnGageDao {

	public LnGage getById(LnGage lnGage) throws DAOException;

	public void del(LnGage lnGage) throws DAOException;

	public void insert(LnGage lnGage) throws DAOException;

	public void update(LnGage lnGage) throws DAOException;
	
	public void updateSts(LnGage lnGage) throws DAOException;
	
	public int getCount(LnGage lnGage) throws DAOException;

	public List<LnGage > findByPage(LnGage lnGage) throws DAOException;

	public int getCountQuotaForCif(LnGage lnGage) throws DAOException;

	public List<LnGage> findByPageQuotaForCif(LnGage lnGage) throws DAOException;

	public int getCountQuotaForLn(LnGage lnGage)throws DAOException;
	public int findlistBygegeStsCount() throws DAOException;
	public List<LnGage> findByPageQuotaForLn(LnGage lnGage)throws DAOException;
	//查询所有待解押产品信息
	public List<LnGage> findlistBygegeSts(Map map)throws DAOException;
	//根据押品ID查询申请ID
	public String findAppIdByGageId(LnGage lnGage) throws DAOException;
}