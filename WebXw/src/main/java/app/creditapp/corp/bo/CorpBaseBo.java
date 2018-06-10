package  app.creditapp.corp.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.corp.entity.CorpBase;

/**
* Title: CorpBaseBo.java
* Description:
**/
public interface CorpBaseBo {

	public CorpBase getById(CorpBase corpBase) throws ServiceException;

	public void del(CorpBase corpBase) throws ServiceException;

	public void insert(CorpBase corpBase) throws ServiceException;

	public void update(CorpBase corpBase) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpBase corpBase) throws ServiceException;
	
	//增加合作机构 通用方法 判断信息 是否填写完整
	public int  getAllInf(String brNo) throws ServiceException;

	public CorpBase getBybrNo(String brNo) throws ServiceException;

}