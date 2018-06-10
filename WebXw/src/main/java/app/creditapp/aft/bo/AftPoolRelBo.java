package  app.creditapp.aft.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.aft.entity.AftPoolRel;

/**
* Title: AftPoolRelBo.java
**/
public interface AftPoolRelBo {

	public AftPoolRel getById(AftPoolRel aftPoolRel) throws ServiceException;

	public void del(AftPoolRel aftPoolRel) throws ServiceException;

	public void insert(AftPoolRel aftPoolRel) throws ServiceException;

	public void update(AftPoolRel aftPoolRel) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AftPoolRel aftPoolRel) throws ServiceException;

	//根据资金池id和合同id删除
	public void delect(AftPoolRel aftPoolRel) throws ServiceException;
	
	//通过fundNo将ln_due中的数据批量插入aft_pool_rel表中 
	public void insertList(AftPoolRel aftPoolRel) throws ServiceException;
}