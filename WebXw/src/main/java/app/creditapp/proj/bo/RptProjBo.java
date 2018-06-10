package app.creditapp.proj.bo;
import app.base.ServiceException;
import app.creditapp.proj.entity.RptProj;
import app.util.toolkit.Ipage;
import java.util.List;
public interface RptProjBo {

	public RptProj getById(RptProj rptProj) throws ServiceException;
	
	public void del(RptProj rptProj) throws ServiceException;
	
	public String insert(RptProj rptProj) throws ServiceException;
	
	public void insertBatch(final List<RptProj> rptProjList) throws ServiceException;
	
	public void update(RptProj rptProj) throws ServiceException;
	
	public Ipage findByPage(Ipage ipg, RptProj rptProj)
		throws ServiceException;
	
	public List<RptProj> findByAll(RptProj rptProj) throws ServiceException;
	
	public List<RptProj> findByAllNum(RptProj rptProj) throws ServiceException;
	
	public List<RptProj> getAccountBal(RptProj rptProj) throws ServiceException;
	
	public RptProj getDailyById(RptProj rptProj) throws ServiceException;
}
