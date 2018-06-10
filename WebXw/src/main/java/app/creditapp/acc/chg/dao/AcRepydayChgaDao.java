package  app.creditapp.acc.chg.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.acc.chg.entity.AcRepydayChga;
/**
* Title: AcRepydayChgaDao.java
* Description:
**/
public interface AcRepydayChgaDao {

	public AcRepydayChga getById(AcRepydayChga acRepydayChga) throws DAOException;

	public void del(AcRepydayChga acRepydayChga) throws DAOException;

	public void insert(AcRepydayChga acRepydayChga) throws DAOException;

	public void update(AcRepydayChga acRepydayChga) throws DAOException;
	
	public int getCount(AcRepydayChga acRepydayChga) throws DAOException;

	public List<AcRepydayChga > findByPage(AcRepydayChga acRepydayChga) throws DAOException;

}