package  app.creditapp.wkf.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.sys.entity.ProdBrno;
import app.creditapp.wkf.entity.AppWkfcfg;
import app.util.toolkit.Ipage;

/**
* Title: AppWkfcfgBo.java
* Description:
**/
public interface AppWkfcfgBo {

	public String getById(AppWkfcfg appWkfcfg) throws ServiceException;
	
	public AppWkfcfg getById2(AppWkfcfg appWkfcfg) throws ServiceException;
	
	public String getByIdForLoan(AppWkfcfg appWkfcfg) throws ServiceException;
	
	public List<AppWkfcfg> getAppWkfcfgList(AppWkfcfg appWkfcfg) throws ServiceException;

	public void update(AppWkfcfg appWkfcfg,List<String> prdt_no) throws ServiceException;
	
	public void insert(AppWkfcfg appWkfcfg,String prdt_no) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AppWkfcfg appWkfcfg) throws ServiceException;
	
	public List<ProdBrno> getProdList(String brNo) throws ServiceException;
	
	public void updateProcessStsByKey(AppWkfcfg appWkfcfg) throws Exception; 
	
	public void deleteByKeyAndType(AppWkfcfg appWkfcfg) throws Exception;

	
	public String chkPrdtNo(String id,String appType,String brNo,String prdtno) throws ServiceException;
	
	public List<AppWkfcfg> getByProcessKey(String processKey) throws Exception;

}