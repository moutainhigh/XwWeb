package  app.creditapp.cred.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cred.entity.CorpEval;

/**
* Title: CorpEvalBo.java
* Description:
**/
public interface CorpEvalBo {

	public CorpEval getById(CorpEval corpEval) throws ServiceException;

	public void del(CorpEval corpEval) throws ServiceException;

	public void insert(CorpEval corpEval) throws ServiceException;

	public void update(CorpEval corpEval) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CorpEval corpEval) throws ServiceException;

	//获取最新评级信息
	public CorpEval getByMaxId(CorpEval corpEval) throws ServiceException;

}