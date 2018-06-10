package  app.creditapp.aft.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.aft.entity.AftAssetPool;
/**
* Title: AftAssetPoolDao.java
* Description:
**/
public interface AftAssetPoolDao {

	public AftAssetPool getById(AftAssetPool aftAssetPool) throws DAOException;

	public void del(AftAssetPool aftAssetPool) throws DAOException;

	public void insert(AftAssetPool aftAssetPool) throws DAOException;

	public void update(AftAssetPool aftAssetPool) throws DAOException;
	
	public int getCount(AftAssetPool aftAssetPool) throws DAOException;

	public List<AftAssetPool > findByPage(AftAssetPool aftAssetPool) throws DAOException;

	//更新资金池借据关联数、资金池金额、本金总额
	public void updateTotal(AftAssetPool aftAssetPool) throws DAOException;
}