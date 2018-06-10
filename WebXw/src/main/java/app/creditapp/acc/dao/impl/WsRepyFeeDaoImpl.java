package  app.creditapp.acc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.acc.dao.WsRepyFeeDao;
import app.creditapp.acc.entity.WsRepyFee;
import app.creditapp.inf.entity.WsIn2301_1_1;
import app.creditapp.inf.entity.WsIn2301_1_2;
/**
* Title: WsRepyFeeDaoImpl.java
* Description:
**/
public class WsRepyFeeDaoImpl extends BaseIbatisDao implements WsRepyFeeDao {

	public void del(WsRepyFee wsRepyFee) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WsRepyFee.del", wsRepyFee);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WsRepyFee> findByPage(WsRepyFee wsRepyFee) throws DAOException {
		List wsRepyFeeList = null;
		try {
			wsRepyFeeList = getSqlMapClientTemplate().queryForList(
					"WsRepyFee.findByPage", wsRepyFee);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyFeeList;
	}

	public List<WsRepyFee> getFeeType(WsRepyFee wsRepyFee) throws DAOException {
		List wsRepyFeeList = null;
		try {
			wsRepyFeeList = getSqlMapClientTemplate()
					.queryForList("WsRepyFee.getFeeType", wsRepyFee);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyFeeList;
	}

	public int getCount(WsRepyFee wsRepyFee) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WsRepyFee.findPage.count", wsRepyFee);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WsRepyFee wsRepyFee) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsRepyFee.insert",
					wsRepyFee);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	// 列表插入数据库ws2301接口使用
	public void insertBatchfor2301(final List<WsIn2301_1_2> list2301_1_2list)
			throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2301_1_2 wsIn2301_1_2 : list2301_1_2list) {
					// 插入预进件申请信息
					executor.insert("WsRepyFee.insert", wsIn2301_1_2);
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
	public void update(WsRepyFee wsRepyFee) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsRepyFee.update",
					wsRepyFee);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public double getFeeAmt(WsRepyFee wsRepyFee) throws DAOException {
		double feeAmt = 0;
		try {
			feeAmt = (Double) getSqlMapClientTemplate().queryForObject(
					"WsRepyFee.getfeeAmt", wsRepyFee);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return feeAmt;
	}
}