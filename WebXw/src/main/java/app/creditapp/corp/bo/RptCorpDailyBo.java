package app.creditapp.corp.bo;
import java.util.List;

import app.base.ServiceException;
import app.creditapp.corp.entity.RptCorpDaily;
import app.util.toolkit.Ipage;
public interface RptCorpDailyBo {

	public RptCorpDaily getById(RptCorpDaily rptCorpDaily) throws ServiceException;
	
	public void del(RptCorpDaily rptCorpDaily) throws ServiceException;
	
	public String insert(RptCorpDaily rptCorpDaily) throws ServiceException;
	
	public void insertBatch(final List<RptCorpDaily> rptCorpDailyList) throws ServiceException;
	
	public void update(RptCorpDaily rptCorpDaily) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, RptCorpDaily rptCorpDaily)
		throws ServiceException;
	
	public List<RptCorpDaily> getList(RptCorpDaily rptCorpDaily) throws ServiceException;

	public List<RptCorpDaily> findByAllNum(RptCorpDaily rptCorpDaily) throws ServiceException;
	
	public RptCorpDaily findRpt(RptCorpDaily rptCorpDaily) throws ServiceException;
	
}
