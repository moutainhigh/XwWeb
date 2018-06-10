package app.creditapp.corp.bo;
import app.base.ServiceException;
import app.creditapp.corp.entity.RptCorpPrdt;
import app.util.toolkit.Ipage;
import java.util.List;
public interface RptCorpPrdtBo {

	public RptCorpPrdt getById(RptCorpPrdt rptCorpPrdt) throws ServiceException;
	
	public void del(RptCorpPrdt rptCorpPrdt) throws ServiceException;
	
	public String insert(RptCorpPrdt rptCorpPrdt) throws ServiceException;
	
	public void insertBatch(final List<RptCorpPrdt> rptCorpPrdtList) throws ServiceException;
	
	public void update(RptCorpPrdt rptCorpPrdt) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, RptCorpPrdt rptCorpPrdt)
		throws ServiceException;
	
	public List<RptCorpPrdt> findByAll(RptCorpPrdt rptCorpPrdt) throws ServiceException;
}
