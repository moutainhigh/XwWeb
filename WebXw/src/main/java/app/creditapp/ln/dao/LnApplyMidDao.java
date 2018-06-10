package  app.creditapp.ln.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.inf.entity.WsIn2101_1;
import app.creditapp.inf.entity.WsIn2102;
import app.creditapp.inf.entity.WsOut2102;
import app.creditapp.inf.entity.WsOut2104_1;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.LnFail;
/**
* Title: LnApplyMidDao.java
* Description:
**/
public interface LnApplyMidDao {

	public LnApplyMid getById(LnApplyMid lnApplyMid) throws DAOException;

	public void del(LnApplyMid lnApplyMid) throws DAOException;

	public void insert(LnApplyMid lnApplyMid) throws DAOException;

	public void update(LnApplyMid lnApplyMid) throws DAOException;
	
	public int getCount(LnApplyMid lnApplyMid) throws DAOException;

	public List<LnApplyMid > findByPage(LnApplyMid lnApplyMid) throws DAOException;
	
	public List<WsOut2102> findByWsIn(WsIn2102 wsIn2102) throws DAOException;

	public List<LnApplyMid> getListByLnApplyMid(LnApplyMid lnApplyMid);
	
	public void insertBatchWs2101_1(final List<WsIn2101_1> wsIn2101_1List) throws DAOException;
	
	public List<LnApplyMid> findListByBatNo(String batNo)throws DAOException;

	public int getCountforlist(LnApplyMid lnApplyMid) throws DAOException;		

	public List<WsOut2104_1> findByPageforlist(Map map) throws DAOException; 
	
	public List<LnApplyMid> findListToWorkA() throws DAOException;
	
	public List<LnApplyMid> findListToWorkB() throws DAOException;
	
	public List<LnApplyMid> findListToWorkC() throws DAOException;
	
	public List<LnApplyMid> findListToWorkD() throws DAOException;
	//根据合同号更新AppId
	public void updateToAppId(LnApplyMid lnApplyMid) throws ServiceException;
	//根据合同号查询AppId
	public LnApplyMid getByPactNo(LnApplyMid lnApplyMid) throws DAOException;
	
	public int getCountToLnFail(LnApplyMid lnApplyMid) throws DAOException;
	
	public List<LnFail > findByPageToLnFail(LnApplyMid lnApplyMid) throws DAOException;
	
	public int resultIdUpdate(LnApplyMid lnApplyMid) throws DAOException;
}