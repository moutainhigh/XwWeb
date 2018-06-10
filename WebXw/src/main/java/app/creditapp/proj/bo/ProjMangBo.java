package  app.creditapp.proj.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.proj.entity.ProjBase;
import app.creditapp.proj.entity.ProjMang;

/**
* Title: ProjMangBo.java
* Description:
**/
public interface ProjMangBo {

	public ProjMang getById(ProjMang projMang) throws ServiceException;
	
	public List<ProjMang > getByIdLoginId(ProjMang projMang) throws ServiceException;

	public void del(ProjMang projMang) throws ServiceException;

	public void insert(ProjMang projMang) throws ServiceException;

	public void update(ProjMang projMang) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ProjMang projMang) throws ServiceException;
	
	public List<ProjMang > findByPageForManager(ProjMang projMang) throws ServiceException;

}