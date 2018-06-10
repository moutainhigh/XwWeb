package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.ParmArea;

/**
* Title: ParmAreaBo.java
* Description:
**/
public interface ParmAreaBo {

	public ParmArea getById(ParmArea parmArea) throws ServiceException;

	public void del(ParmArea parmArea) throws ServiceException;

	public void insert(ParmArea parmArea) throws ServiceException;

	public void update(ParmArea parmArea) throws ServiceException;

	public Ipage findByPage(Ipage ipg, ParmArea parmArea) throws ServiceException;
	
	public ParmArea getTreeTop()throws ServiceException;

	public List<ParmArea> getChildren(String areaLev)throws ServiceException;
	
	public ParmArea getByDeptid(ParmArea parmArea)throws ServiceException;



}