package  app.creditapp.fund.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.fund.entity.FundSplit;
import app.util.toolkit.Ipage;

/**
* Title: FundSplitBo.java
* Description:
**/
public interface FundSplitBo {

	public FundSplit getById(FundSplit fundSplit) throws ServiceException;

	public void del(FundSplit fundSplit) throws ServiceException;

	public void insert(FundSplit fundSplit) throws ServiceException;

	public void update(FundSplit fundSplit) throws ServiceException;

	public Ipage findByPage(Ipage ipg, FundSplit fundSplit) throws ServiceException;
	
	public List<FundSplit> echarts(FundSplit fundSplit) throws ServiceException;

}