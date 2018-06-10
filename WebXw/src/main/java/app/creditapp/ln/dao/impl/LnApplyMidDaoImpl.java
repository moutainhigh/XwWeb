package  app.creditapp.ln.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.gage.entity.LnGage;
import app.creditapp.inf.entity.WsIn2101_1;
import app.creditapp.inf.entity.WsIn2101_1_1;
import app.creditapp.inf.entity.WsIn2101_1_2;
import app.creditapp.inf.entity.WsIn2101_1_3;
import app.creditapp.inf.entity.WsIn2101_1_4;
import app.creditapp.inf.entity.WsIn2102;
import app.creditapp.inf.entity.WsOut2102;
import app.creditapp.inf.entity.WsOut2104_1;
import app.creditapp.ln.dao.LnApplyMidDao;
import app.creditapp.ln.entity.LnAcct;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.LnCom;
import app.creditapp.ln.entity.LnFail;
import app.creditapp.ln.entity.LnRel;

import com.ibatis.sqlmap.client.SqlMapExecutor;
/**
* Title: LnApplyMidDaoImpl.java
* Description:
**/
public class LnApplyMidDaoImpl extends BaseIbatisDao implements LnApplyMidDao {
	
	public void del(LnApplyMid lnApplyMid) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("LnApplyMid.del", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<LnApplyMid> findByPage(LnApplyMid lnApplyMid) throws DAOException {
		List lnApplyMidList = null;
		try {
			lnApplyMidList = getSqlMapClientTemplate().queryForList(
					"LnApplyMid.findByPage", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMidList;
	}

	public LnApplyMid getById(LnApplyMid lnApplyMid) throws DAOException {
		try {
			lnApplyMid = (LnApplyMid) getSqlMapClientTemplate()
					.queryForObject("LnApplyMid.getById", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMid;
	}

	public int getCount(LnApplyMid lnApplyMid) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnApplyMid.findPage.count", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(LnApplyMid lnApplyMid) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("LnApplyMid.insert",
					lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(LnApplyMid lnApplyMid) throws DAOException {
		try {
			getSqlMapClientTemplate().update("LnApplyMid.update",
					lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	/**
	 * @作者 DHCC-ZKX
	 * @日期 July 12, 2016
	 * @方法说明：进件处理结果查询，从数据库LN_APPLY_MID表中获取数据
	 * @返回参数 List<WsOut2102> 
	 */
	public List<WsOut2102> findByWsIn(WsIn2102 wsIn2102) throws DAOException {
		List<WsOut2102> wsOutlist = new ArrayList<WsOut2102>();
		try {
			wsOutlist = (List<WsOut2102>) getSqlMapClientTemplate().queryForList(
					"LnApplyMid.findByWsIn", wsIn2102);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return wsOutlist;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LnApplyMid> getListByLnApplyMid(LnApplyMid lnApplyMid) {
		List<LnApplyMid> lnApplyMidList = null;
		try {
			lnApplyMidList = (List<LnApplyMid>)getSqlMapClientTemplate().queryForList(
					"LnApplyMid.getListByLnApplyMid", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMidList;
	}
	
	@SuppressWarnings("unchecked")
	public void insertBatchWs2101_1(final List<WsIn2101_1> wsIn2101_1List) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 50;
				int count = 0;
				for (WsIn2101_1 wsIn2101_1 : wsIn2101_1List) {
					// 插入正式申请信息中间表
					executor.insert("WsIn2101_1.insert", wsIn2101_1);
					// 插入正式申请账户中间表
					for (WsIn2101_1_1 wsIn2101_1_1 : wsIn2101_1.getListAc()) {
						executor.insert("WsIn2101_1_1.insert", wsIn2101_1_1);
					}
					// 插入正式申请押品中间表
					if(wsIn2101_1.getListGage()!=null && wsIn2101_1.getListGage().size() !=0){
					for (WsIn2101_1_2 wsIn2101_1_2 : wsIn2101_1.getListGage()) {
						executor.insert("WsIn2101_1_2.insert", wsIn2101_1_2);
				     	}
					}
					// 插入共同借款人
					for (WsIn2101_1_3 wsIn2101_1_3 : wsIn2101_1.getListCom()) {
						executor.insert("WsIn2101_1_3.insert", wsIn2101_1_3);
					}
					// 插入借款关联人
					for (WsIn2101_1_4 wsIn2101_1_4 : wsIn2101_1.getListRel()) {
						executor.insert("WsIn2101_1_4.insert", wsIn2101_1_4);
					}
					// 每batchSize条数据提交一次
					if (++count % batchSize == 0) {
						executor.executeBatch();
					}
				}
				// 提交剩余的数据
				executor.executeBatch();
				return null;
			}
		});

	}
	
	public List<LnApplyMid> findListByBatNo(String batNo)throws DAOException{
		List<LnApplyMid>  lnApplyMidList = null;
		try {
			lnApplyMidList = getSqlMapClientTemplate().queryForList("LnApplyMid.findListByBatNo", batNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMidList;
	}

	public int getCountforlist(LnApplyMid lnApplyMid) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnApplyMid.findPageforlist.count", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	
	/**
	 * @作者 DHCC-ZKX
	 * @日期 July 15, 2016
	 * @方法说明：接口ws2104 查询列表List<WsOut2104_1>并插入对应其他表的记录
	 * @返回参数 List<WsOut2104_1> 
	 */
	public List<WsOut2104_1> findByPageforlist(Map map) throws DAOException {
		List<WsOut2104_1> wsOut2104_1list = null;		
		LnAcct lnAcct = new LnAcct();
		LnGage lnGage = new LnGage();
		LnCom lnCom = new LnCom();
		LnRel lnRel = new LnRel();
		try {
			//查询LN_APPLY_MID表 并返回对应内容列表
			wsOut2104_1list = getSqlMapClientTemplate().queryForList(
					"LnApplyMid.findByPageforlist", map);
			for(WsOut2104_1 wsOut2104_1:wsOut2104_1list){	
				//获取共同字段APP_ID内容
				String appId = wsOut2104_1.getAppId();
				//查询LN_ACCT表 并插入list
				lnAcct.setAppId(appId);
				wsOut2104_1.setListAc(getSqlMapClientTemplate().queryForList(
						"LnAcct.findlist", lnAcct));
				//查询LN_GAGE表 并插入list
				lnGage.setAppId(appId);
				wsOut2104_1.setListGage(getSqlMapClientTemplate().queryForList(
						"LnGage.findlist", lnGage));
				//查询LN_COM表 并插入list
				lnCom.setAppId(appId);
				wsOut2104_1.setListCom(getSqlMapClientTemplate().queryForList(
						"LnCom.findlist", lnCom));
				//查询LN_REL表 并插入list
				lnRel.setAppId(appId);
				wsOut2104_1.setListRel(getSqlMapClientTemplate().queryForList(
						"LnRel.findlist", lnRel));
			}
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return wsOut2104_1list;
	}
	
	public List<LnApplyMid> findListToWorkA() throws DAOException{
		List<LnApplyMid> lnApplyMidList = null;
		try {
			lnApplyMidList = getSqlMapClientTemplate().queryForList(
					"LnApplyMidDao.findListToWorkA");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMidList;
	}
	
	public List<LnApplyMid> findListToWorkB() throws DAOException{
		List<LnApplyMid> lnApplyMidList = null;
		try {
			lnApplyMidList = getSqlMapClientTemplate().queryForList(
					"LnApplyMidDao.findListToWorkB");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMidList;
	}
	public List<LnApplyMid> findListToWorkC() throws DAOException{
		List<LnApplyMid> lnApplyMidList = null;
		try {
			lnApplyMidList = getSqlMapClientTemplate().queryForList(
					"LnApplyMidDao.findListToWorkC");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMidList;
	}
	public List<LnApplyMid> findListToWorkD() throws DAOException{
		List<LnApplyMid> lnApplyMidList = null;
		try {
			lnApplyMidList = getSqlMapClientTemplate().queryForList(
					"LnApplyMidDao.findListToWorkD");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMidList;
	}
	
	//根据合同号更新AppId
	public void updateToAppId(LnApplyMid lnApplyMid) throws ServiceException{
		try {//lnacctmid,lngagemid,lnrelmid,lncommid
			getSqlMapClientTemplate().update("LnApplyMid.updateToLnAcctMid",
					lnApplyMid);
			getSqlMapClientTemplate().update("LnApplyMid.updateToLnGageMid",
					lnApplyMid);
			getSqlMapClientTemplate().update("LnApplyMid.updateToLnRelMid",
					lnApplyMid);
			getSqlMapClientTemplate().update("LnApplyMid.updateToLnComMid",
					lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public LnApplyMid getByPactNo(LnApplyMid lnApplyMid) throws DAOException{
		try {
			lnApplyMid = (LnApplyMid) getSqlMapClientTemplate()
					.queryForObject("LnApplyMid.getByPactNo", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnApplyMid;
	}
	public int getCountToLnFail(LnApplyMid lnApplyMid) throws DAOException{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"LnApplyMid.findByPageToLnFail.count", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	public List<LnFail > findByPageToLnFail(LnApplyMid lnApplyMid) throws DAOException{
		List acLnMstList = null;
		try {
			acLnMstList = getSqlMapClientTemplate().queryForList(
					"LnApplyMid.findByPageToLnFail", lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return acLnMstList;
	}
	public int resultIdUpdate(LnApplyMid lnApplyMid) throws DAOException{
		int result = 0;
		try {
			result = getSqlMapClientTemplate().update("LnApplyMid.resultIdUpdate",
					lnApplyMid);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return result;
	}
}