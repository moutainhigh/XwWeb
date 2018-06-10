package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.sys.entity.PubNote;
import app.util.toolkit.Ipage;

/**
* Title: PubNoteBo.java
* Description:
**/
public interface PubNoteBo {

	public PubNote getById(PubNote pubNote) throws ServiceException;

	public void del(PubNote pubNote) throws ServiceException;

	public void insert(PubNote pubNote) throws ServiceException;

	public void update(PubNote pubNote) throws ServiceException;

	public Ipage findByPage(Ipage ipg, PubNote pubNote) throws ServiceException;
	
	public List<PubNote> getTop10(PubNote pubNote) throws ServiceException;
	
	public List<PubNote> findAllByNote_type(PubNote pubNote) throws ServiceException;

}