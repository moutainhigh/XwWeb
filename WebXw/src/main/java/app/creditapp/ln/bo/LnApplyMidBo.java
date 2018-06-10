package  app.creditapp.ln.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.inf.entity.ResponseParm;
import app.creditapp.inf.entity.WsIn2102;
import app.creditapp.inf.entity.WsIn2104;
import app.creditapp.inf.entity.WsOut2102;
import app.creditapp.ln.entity.LnApplyMid;
import app.util.toolkit.Ipage;

/**
* Title: LnApplyMidBo.java
* Description:
**/
public interface LnApplyMidBo {

	public LnApplyMid getById(LnApplyMid lnApplyMid) throws ServiceException;

	public void del(LnApplyMid lnApplyMid) throws ServiceException;

	public void insert(LnApplyMid lnApplyMid) throws ServiceException;

	public void update(LnApplyMid lnApplyMid) throws ServiceException;

	public Ipage findByPage(Ipage ipg, LnApplyMid lnApplyMid) throws ServiceException;
	
	//wangtao 查询lnappymid表
	public List<LnApplyMid> findListByBatNo(String batNo) throws ServiceException;

	public ResponseParm validateforListWs(WsIn2102 wsIn2102) throws ServiceException;
	
	public List<WsOut2102> findByWsIn(WsIn2102 wsIn2102) throws ServiceException;

	public List<LnApplyMid> getListByLnApplyMid(LnApplyMid lnApplyMid) throws ServiceException;

	public  ResponseParm validateWsIn(WsIn2104 wsIn2104) throws ServiceException;
	
	public Ipage findByPageforList(Ipage ipg, LnApplyMid lnApplyMid) throws ServiceException;
	
	public int countforListWs(LnApplyMid lnApplyMid) throws ServiceException;
	
	public List<LnApplyMid> findListToWorkA() throws ServiceException;
	
	public List<LnApplyMid> findListToWorkB() throws ServiceException;
	
	public List<LnApplyMid> findListToWorkC() throws ServiceException;
	
	public List<LnApplyMid> findListToWorkD() throws ServiceException;
	//根据合同号更新AppId
	public void updateToAppId(LnApplyMid lnApplyMid) throws ServiceException;
	//根据合同号查询APPID
	public LnApplyMid getByPactNo(LnApplyMid lnApplyMid) throws ServiceException;

	public Ipage findByPageToLnFail(Ipage ipg, LnApplyMid lnApplyMid) throws ServiceException;
	//插入自动审批returnId
	public int resultIdUpdate(LnApplyMid lnApplyMid) throws ServiceException;
}