package  app.creditapp.cif.bo;

import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cif.entity.CifPortrait;

/**
* Title: CifPortraitBo.java
* Description:
**/
public interface CifPortraitBo {

	public CifPortrait getById(CifPortrait cifPortrait) throws ServiceException;

	public void del(CifPortrait cifPortrait) throws ServiceException;

	public void insert(CifPortrait cifPortrait) throws ServiceException;

	public void update(CifPortrait cifPortrait) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CifPortrait cifPortrait) throws ServiceException;
	//查询工作类型
	public String getWorkType(CifPortrait cifPortrait) throws ServiceException;
	//查询是否有车
	public String getIfCar(CifPortrait cifPortrait) throws ServiceException;
	//查询是否有房
	public String getIfRoom(CifPortrait cifPortrait) throws ServiceException;
	
	public List<CifPortrait> getCifGroup(CifPortrait cifPortrait) throws ServiceException;

}