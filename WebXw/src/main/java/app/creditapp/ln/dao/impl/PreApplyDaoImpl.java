package  app.creditapp.ln.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.inf.entity.WsIn2001_1;
import app.creditapp.inf.entity.WsIn2001_1_1;
import app.creditapp.inf.entity.WsIn2004;
import app.creditapp.inf.entity.WsIn2004_1;
import app.creditapp.inf.entity.WsOut2002_1;
import app.creditapp.inf.entity.WsOut2006_1;
import app.creditapp.ln.dao.PreApplyDao;
import app.creditapp.ln.entity.LnApplyMid;
import app.creditapp.ln.entity.PreApply;
import app.util.DateUtil;

import com.ibatis.sqlmap.client.SqlMapExecutor;
/**
* Title: PreApplyDaoImpl.java
* Description:
**/
public class PreApplyDaoImpl extends BaseIbatisDao implements PreApplyDao {

	public void del(PreApply preApply) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("PreApply.del", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<PreApply> findByPage(PreApply preApply) throws DAOException {
		List preApplyList = null;
		try {
			preApplyList = getSqlMapClientTemplate().queryForList(
					"PreApply.findByPage", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return preApplyList;
	}

	public List<PreApply > findListByBatNo(PreApply preApply) throws DAOException{
		List preApplyList = null;
		try {
			preApplyList = getSqlMapClientTemplate().queryForList(
					"PreApply.findListByBatNo", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return preApplyList;
	}
	
	public PreApply getById(PreApply preApply) throws DAOException {
		try {
			preApply = (PreApply) getSqlMapClientTemplate()
					.queryForObject("PreApply.getById", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return preApply;
	}

	public int getCount(PreApply preApply) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PreApply.findPage.count", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(PreApply preApply) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PreApply.insert",
					preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(PreApply preApply) throws DAOException {
		try {
			getSqlMapClientTemplate().update("PreApply.update",
					preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	public void updateZDSP(PreApply preApply) throws DAOException {
		try {
			getSqlMapClientTemplate().update("PreApply.updateZDSP",
					preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {
		String lnSeq = "";
		try {
			lnSeq = (String) getSqlMapClientTemplate()
					.queryForObject("PreApply.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return lnSeq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PreApply> getListByPreApply(PreApply preApply)
			throws DAOException {
		List<PreApply> preApplyList = null;
		try {
			preApplyList = (List<PreApply>)getSqlMapClientTemplate().queryForList(
					"PreApply.getListByPreApply", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return preApplyList;
	}
	

	//接口ws2006，wsout2006_1 分页查找
	public List<WsOut2006_1> findByPageforws2006(PreApply preApply) throws DAOException {
		List<WsOut2006_1> wsOut2006_1list = new ArrayList<WsOut2006_1>();
		try {
			wsOut2006_1list = getSqlMapClientTemplate().queryForList(
					"PreApply.findByPageforws2006", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsOut2006_1list;
	}
	//接口ws2006 wsout2006_1返回符合条件的个数
	public int getCountforws2006(PreApply preApply) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PreApply.findPageforws2006.count", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	

	
	
	public List<WsOut2002_1> findByBatchNo(PreApply preApply) throws DAOException {
		
		List<WsOut2002_1> wsOut2002_1list = new ArrayList<WsOut2002_1>();
		try {
			wsOut2002_1list = getSqlMapClientTemplate().queryForList("WsOut2002_1.find", preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsOut2002_1list;
	}

	

	
	
	public void insertforws(WsIn2004 wsIn2004) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PreApplyforws.insert",
					wsIn2004);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	
	

	
	//WsIn2004_1 预审批单笔进件
	@SuppressWarnings("unchecked")
	public void insertBatch(final List<WsIn2004_1> wsIn2004_1list) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2004_1 wsIn2004_1 : wsIn2004_1list) {
					// 插入预进件申请信息
					executor.insert("WsIn2004_1.insert", wsIn2004_1);// 插入pre_gage表
					/*
					// 插入预进件押品信息
					for (WsIn2004_1_1 wsPreGage : wsPreApply.getGageList()) {
						executor.insert("WsPreGage.insert", wsPreGage);
					}*/
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
	@SuppressWarnings("unchecked")
	public void insertBatchfor2001(final List<WsIn2001_1> wsIn2001_1list) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2001_1 wsIn2001_1 : wsIn2001_1list) {
					// 插入预进件申请信息
					wsIn2001_1.setAppDate(DateUtil.getSysDate());
					executor.insert("PreApplyforws.insert", wsIn2001_1);
					// 插入预进件押品信息
					if(wsIn2001_1.getListGage()!=null && wsIn2001_1.getListGage().size()!=0){
						for (WsIn2001_1_1 wsIn2001_1_1 : wsIn2001_1.getListGage()) {
							executor.insert("WsIn2004_1.insert", wsIn2001_1_1);
						}
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
	
	//根据合同号更新AppId
	public void updateToAppId(PreApply preApply) throws ServiceException{
		try {//pregagemid
			getSqlMapClientTemplate().update("PreApply.updateToPreGageMid",
					preApply);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
}