package  app.creditapp.cred.bo;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.cred.entity.CifEval;

/**
* Title: CifEvalBo.java
**/
public interface CifEvalBo {

	public CifEval getById(CifEval cifEval) throws ServiceException;

	public void del(CifEval cifEval) throws ServiceException;

	public void insert(CifEval cifEval) throws ServiceException;

	public void update(CifEval cifEval) throws ServiceException;
	
	public int cifScoreUpdate(CifEval cifEval) throws ServiceException;

	public Ipage findByPage(Ipage ipg, CifEval cifEval) throws ServiceException;

	//获取最新评分信息
	public CifEval getByMaxId(CifEval cifEval) throws ServiceException;
}