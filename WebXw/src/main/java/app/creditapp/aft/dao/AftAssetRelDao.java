package  app.creditapp.aft.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.aft.entity.AftAssetRel;
import app.creditapp.sys.entity.SysUserRole;
/**
* Title: AftAssetRelDao.java
* Description:
**/
public interface AftAssetRelDao {

	public AftAssetRel getById(AftAssetRel aftAssetRel) throws DAOException;

	public void del(AftAssetRel aftAssetRel) throws DAOException;

	public void insert(AftAssetRel aftAssetRel) throws DAOException;

	public void update(AftAssetRel aftAssetRel) throws DAOException;
	
	public int getCount(AftAssetRel aftAssetRel) throws DAOException;

	public List<AftAssetRel > findByPage(AftAssetRel aftAssetRel) throws DAOException;

	public void updateForLn(AftAssetRel aftAssetRel) throws DAOException;
	
	public void updateValue(Map<?, ?> map)throws DAOException;
	
	public double getTotalAmt(String pactNo,String recId)throws DAOException;
}