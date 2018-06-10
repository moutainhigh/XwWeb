package  app.creditapp.pac.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.pac.entity.DocConfig;

/**
* Title: DocConfigBo.java
* Description:
**/
public interface DocConfigBo {

	public DocConfig getById(DocConfig docConfig) throws ServiceException;

	public void del(DocConfig docConfig) throws ServiceException;

	public void insert(DocConfig docConfig) throws ServiceException;

	public void update(DocConfig docConfig) throws ServiceException;
	
	public DocConfig findDocTypeNo(DocConfig docConfig) throws ServiceException;

	public Ipage findByPage(Ipage ipg, DocConfig docConfig) throws ServiceException;

}