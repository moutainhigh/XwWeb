package  app.creditapp.ln.dao;

import java.util.List;

import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.inf.entity.WsIn2001_1;
import app.creditapp.inf.entity.WsIn2004;
import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsOut2002_1;
import app.creditapp.inf.entity.WsOut2006_1;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.PreApply;
/**
* Title: PreApplyDao.java
* Description:
**/
public interface PreApplyDao {

	public PreApply getById(PreApply preApply) throws DAOException;

	public void del(PreApply preApply) throws DAOException;

	public void insert(PreApply preApply) throws DAOException;

	public void update(PreApply preApply) throws DAOException;
	
	public void updateZDSP(PreApply preApply) throws DAOException;
	
	public int getCount(PreApply preApply) throws DAOException;

	public List<PreApply > findByPage(PreApply preApply) throws DAOException;
	
	public List<PreApply > findListByBatNo(PreApply preApply) throws DAOException;
	//接口ws2002 返回符合条件的WsOut2002_1的列表
	public List<WsOut2002_1> findByBatchNo(PreApply preApply) throws DAOException;

	public String getKey()throws DAOException;
	
	public void insertBatch(final List<WsIn2004_1> preApplyList) throws DAOException;
	
	public void insertBatchfor2001(final List<WsIn2001_1> wsIn2001_1list) throws DAOException;
	
	public void insertforws(WsIn2004 wsIn2004) throws DAOException;
	//接口ws2006 分页查找
	public List<WsOut2006_1> findByPageforws2006(PreApply preApply) throws DAOException;
	//接口ws2006 符合条件的总条数
	public int getCountforws2006(PreApply preApply) throws DAOException;
	//根据合同号更新AppId
	public void updateToAppId(PreApply preApply) throws ServiceException;

	public List<PreApply> getListByPreApply(PreApply preApply)throws DAOException;

}