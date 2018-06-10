package  app.creditapp.aft.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.aft.entity.AftPoolRel;
/**
* Title: AftPoolRelDao.java
* Description:
**/
public interface AftPoolRelDao {

	public AftPoolRel getById(AftPoolRel aftPoolRel) throws DAOException;

	public void del(AftPoolRel aftPoolRel) throws DAOException;

	public void insert(AftPoolRel aftPoolRel) throws DAOException;

	public void update(AftPoolRel aftPoolRel) throws DAOException;
	
	public int getCount(AftPoolRel aftPoolRel) throws DAOException;

	public List<AftPoolRel > findByPage(AftPoolRel aftPoolRel) throws DAOException;
	
	//根据资金池id和合同id删除
	public void delect(AftPoolRel aftPoolRel) throws DAOException;
	
	//通过fundNo将ln_due中的数据批量插入aft_pool_rel表中 
	public void insertList(AftPoolRel aftPoolRel) throws DAOException;

}