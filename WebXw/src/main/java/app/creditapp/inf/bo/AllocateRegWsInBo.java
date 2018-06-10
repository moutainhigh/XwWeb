package app.creditapp.inf.bo;

import app.base.ServiceException;
import app.creditapp.inf.entity.AllocateRegWsIn;
import app.creditapp.inf.entity.ResData;
import app.creditapp.inf.entity.TaState;
import app.util.toolkit.Ipage;

/**
 * Title: AllocateRegWsInBo.java Description:
 * 
 **/
public interface AllocateRegWsInBo {

	public AllocateRegWsIn getById(AllocateRegWsIn allocateRegWsIn) throws ServiceException;

	public void del(AllocateRegWsIn allocateRegWsIn) throws ServiceException;

	public void insert(AllocateRegWsIn allocateRegWsIn) throws ServiceException;

	public void update(AllocateRegWsIn allocateRegWsIn) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AllocateRegWsIn allocateRegWsIn) throws ServiceException;

	public ResData sendTA(String id) throws ServiceException;
	
	public TaState searchTA(String id) throws ServiceException;
	
}