package  app.creditapp.wkf.dao;
import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.sys.entity.ProdBrno;
import app.creditapp.wkf.entity.AppWkfcfg;
/**
* Title: AppWkfcfgDao.java
* Description:
**/
public interface AppWkfcfgDao {

	public String  getById(AppWkfcfg appWkfcfg) throws DAOException;
	public AppWkfcfg  getById2(AppWkfcfg appWkfcfg) throws DAOException;
	/**
	 * 功能描述：贷款业务审批流程比其他审批复杂，为添加要素项方便，重写
	 * @see
	 * @修改日志：
	 * @modify by shenpeng 2013-10-02
	 * 
	 */
	public String getByIdForLoan(AppWkfcfg appWkfcfg) throws DAOException;
	/**
	 * 功能描述：贷款审批流程唯一性判断
	 * @see
	 * @修改日志：
	 */
	public int getCountForWkfLoan(AppWkfcfg appWkfcfg) throws DAOException;

	public void update(AppWkfcfg appWkfcfg) throws DAOException;
	
	public void deleteByKeyAndType(AppWkfcfg appWkfcfg) throws Exception;
	
	public List<AppWkfcfg> getByProcessKey(String processKey) throws Exception;
	
	public void insert(AppWkfcfg appWkfcfg) throws DAOException;
	
	public int getCount(AppWkfcfg appWkfcfg) throws DAOException;
	
	public void updateProcessStsByKey(AppWkfcfg appWkfcfg) throws Exception; 
	
	public List<AppWkfcfg > findByPage(AppWkfcfg appWkfcfg) throws DAOException;
	
	public List<ProdBrno> getProdList(String brNo) throws DAOException;
	
	public List<AppWkfcfg> chkPrdtNo(AppWkfcfg appWkfcfg) throws DAOException;
	
	public String getNextVal() throws DAOException;
	
	public List<AppWkfcfg> getAppWkfcfgList(AppWkfcfg appWkfcfg) throws DAOException;
}