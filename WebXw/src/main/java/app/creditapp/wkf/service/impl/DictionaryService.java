package app.creditapp.wkf.service.impl;

import java.util.List;

import com.dhcc.workflow.flex.interfaces.IDictionaryService;
import com.dhcc.workflow.identity.dao.IVarConfigDAO;

import app.oscache.CachecodeUtil;
import app.oscache.MBaseCache;

public class DictionaryService implements IDictionaryService {

	private IVarConfigDAO varconfigDAO;
	
	@SuppressWarnings("unchecked")
	public List list(String dataSource,String name) {
		List list = (List) MBaseCache.getCache().getBeanCache(name,CachecodeUtil.CACHE_DATADICT);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List listFromDB(String apptype) {
		return varconfigDAO.list(apptype);
	}

	public IVarConfigDAO getVarconfigDAO() {
		return varconfigDAO;
	}

	public void setVarconfigDAO(IVarConfigDAO varconfigDAO) {
		this.varconfigDAO = varconfigDAO;
	}

	public List listFromDB(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
