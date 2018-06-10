package app.creditapp.wkf.service.impl;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import com.dhcc.workflow.identity.dao.IQueryDAO;
import com.dhcc.workflow.identity.service.interfaces.IQueryService;
import com.dhcc.workflow.query.domain.WorkflowActivity;
import com.dhcc.workflow.query.domain.WorkflowInstance;
import com.dhcc.workflow.query.domain.WorkflowTask;


public class QueryService implements IQueryService {

	private IQueryDAO queryDAO;
	
	public Ipage listActivity(Ipage ipage, WorkflowActivity workflowActivity) throws Exception {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) queryDAO.getCount(workflowActivity) });
			workflowActivity.setStartNumAndEndNum (ipage);
			ipage.setResult(queryDAO.listActivity(workflowActivity));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	public Ipage listInstance(Ipage ipage,
			WorkflowInstance workflowInstance) throws Exception {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) queryDAO.getCount(workflowInstance) });
			workflowInstance.setStartNumAndEndNum (ipage);
			ipage.setResult(queryDAO.listInstance(workflowInstance));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	public Ipage listTask(Ipage ipage, WorkflowTask workflowTask)
			throws Exception {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) queryDAO.getCount(workflowTask) });
			workflowTask.setStartNumAndEndNum (ipage);
			ipage.setResult(queryDAO.listTask(workflowTask));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
	
	public IQueryDAO getQueryDAO() {
		return queryDAO;
	}

	public void setQueryDAO(IQueryDAO queryDAO) {
		this.queryDAO = queryDAO;
	}

}
