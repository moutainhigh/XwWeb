package  app.creditapp.acc.option.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.option.entity.AcReplanSec;
/**
* Title: AcReplanSecDao.java
* Description:
**/
public interface AcReplanSecDao {

	public AcReplanSec getById(AcReplanSec acReplanSec) throws DAOException;

	public AcReplanSec getByIdAndOrderNo(AcReplanSec acReplanSec) throws DAOException;

	public void del(AcReplanSec acReplanSec) throws DAOException;

	public void delByReplanId(AcReplanSec acReplanSec) throws DAOException;

	public void insert(AcReplanSec acReplanSec) throws DAOException;

	public void update(AcReplanSec acReplanSec) throws DAOException;
	
	public int getCount(AcReplanSec acReplanSec) throws DAOException;

	public List<AcReplanSec > findByPage(AcReplanSec acReplanSec) throws DAOException;
	
	public List<AcReplanSec > getListByReplanId(AcReplanSec acReplanSec) throws DAOException;


}