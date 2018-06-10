package app.creditapp.corp.bo;
import app.base.ServiceException;
import app.creditapp.corp.entity.RptCorpArea;
import app.util.toolkit.Ipage;
import java.util.List;
public interface RptCorpAreaBo {

	public RptCorpArea getById(RptCorpArea rptCorpArea) throws ServiceException;
	
	public void del(RptCorpArea rptCorpArea) throws ServiceException;
	
	public String insert(RptCorpArea rptCorpArea) throws ServiceException;
	
	public void insertBatch(final List<RptCorpArea> rptCorpAreaList) throws ServiceException;
	
	public void update(RptCorpArea rptCorpArea) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, RptCorpArea rptCorpArea)
		throws ServiceException;
	
	public List<RptCorpArea> findByAll(RptCorpArea rptCorpArea) throws ServiceException;
}
