package  app.creditapp.acc.chg.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.acc.chg.entity.AcRepydayChga;

/**
* Title: AcRepydayChgaBo.java
* Description:
**/
public interface AcRepydayChgaBo {

	public AcRepydayChga getById(AcRepydayChga acRepydayChga) throws ServiceException;

	public void del(AcRepydayChga acRepydayChga) throws ServiceException;

	public void insert(AcRepydayChga acRepydayChga) throws ServiceException;

	public void update(AcRepydayChga acRepydayChga) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AcRepydayChga acRepydayChga) throws ServiceException;

}