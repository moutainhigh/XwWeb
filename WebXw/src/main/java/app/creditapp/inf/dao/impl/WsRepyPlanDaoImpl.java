package  app.creditapp.inf.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.inf.dao.WsRepyPlanDao;
import app.creditapp.inf.entity.WsIn2201_1;
import app.creditapp.inf.entity.WsIn2202;
import app.creditapp.inf.entity.WsIn2602_1;
import app.creditapp.inf.entity.WsIn2604_2;
import app.creditapp.inf.entity.WsIn2802_1;
import app.creditapp.inf.entity.WsIn2804_2;
import app.creditapp.inf.entity.WsOut2202;
import app.creditapp.inf.entity.WsRepyPlan;

import com.ibatis.sqlmap.client.SqlMapExecutor;
/**
* Title: WsRepyPlanDaoImpl.java
* Description:
**/
public class WsRepyPlanDaoImpl extends BaseIbatisDao implements WsRepyPlanDao {

	@SuppressWarnings("unchecked")
	public void insertBatch(final List<WsRepyPlan> wsRepyPlanlist) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsRepyPlan wsRepyPlan : wsRepyPlanlist) {
					// 插入还款计划信息
					executor.insert("WsRepyPlan.insert", wsRepyPlan);
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
	public void del(WsRepyPlan wsRepyPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WsRepyPlan.del", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WsRepyPlan> findByPage(WsRepyPlan wsRepyPlan) throws DAOException {
		List wsRepyPlanList = null;
		try {
			wsRepyPlanList = getSqlMapClientTemplate().queryForList(
					"WsRepyPlan.findByPage", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyPlanList;
	}

	public WsRepyPlan getById(WsRepyPlan wsRepyPlan) throws DAOException {
		try {
			wsRepyPlan = (WsRepyPlan) getSqlMapClientTemplate()
					.queryForObject("WsRepyPlan.getById", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyPlan;
	}

	public int getCount(WsRepyPlan wsRepyPlan) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WsRepyPlan.findPage.count", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WsRepyPlan wsRepyPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsRepyPlan.insert",
					wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WsRepyPlan> getByPactNo(WsRepyPlan wsRepyPlan) throws DAOException {
		List<WsRepyPlan> wsRepyPlanlist = null;
		try {
			wsRepyPlanlist = (List<WsRepyPlan>) getSqlMapClientTemplate().
			queryForList("WsRepyPlan.getByPactNo", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyPlanlist;
	}
	public void delByPactNo(WsRepyPlan wsRepyPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WsRepyPlan.delByPactNo", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//批量插入还款计划
	@SuppressWarnings("unchecked")
	public void insertBatchForList(final List<WsIn2201_1> wsIn2201_1list) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2201_1 wsIn2201_1:wsIn2201_1list) {
					// 插入还款计划信息
					executor.insert("WsRepyPlan.insertWs", wsIn2201_1);
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
	//批量插入还款计划ws2602
	@SuppressWarnings("unchecked")
	public void insertForList( final List<WsIn2602_1> wsIn2602_1list) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2602_1 wsIn2602_1 : wsIn2602_1list) {
					// 插入还款计划信息
					executor.insert("WsRepyPlan.insertForWs", wsIn2602_1);
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
	//批量插入还款计划ws2604
	@SuppressWarnings("unchecked")
	public void insertforList(final List<WsIn2604_2> wsIn2604_2list) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2604_2 wsIn2604_2 : wsIn2604_2list) {
					// 插入还款计划信息
					executor.insert("WsRepyPlan.insertForWs", wsIn2604_2);
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
	//批量插入还款计划ws2802
	@SuppressWarnings("unchecked")
	public void insertList( final List<WsIn2802_1> wsIn2802_1list) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2802_1 wsIn2802_1 : wsIn2802_1list) {
					// 插入还款计划信息
					executor.insert("WsRepyPlan.insertForWs", wsIn2802_1);
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
	//批量插入还款计划ws2804
	@SuppressWarnings("unchecked")
	public void insertlist(final List<WsIn2804_2> wsIn2804_2list) throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2804_2 wsIn2804_2 : wsIn2804_2list) {
					// 插入还款计划信息
					executor.insert("WsRepyPlan.insertForWs", wsIn2804_2);
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
	/**
	 * @作者 DHCC-ZKX
	 * @日期 July 20, 2016
	 * @方法说明 还款计划查询，从数据库WS_REPY_PLAN表中获取数据
	 * @返回参数 List<WsOut2202> 
	 */
	public List<WsOut2202> findByWsIn(WsIn2202 wsIn2202) throws DAOException {
		List<WsOut2202> wsOutlist = new ArrayList<WsOut2202>();
		try {
			wsOutlist = (List<WsOut2202>) getSqlMapClientTemplate().queryForList(
					"WsRepyPlan.findByWsIn", wsIn2202);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return wsOutlist;
	}
	
	
	public void update(WsRepyPlan wsRepyPlan) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsRepyPlan.update",
					wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	//同一个合同中的每期应还本金之和 PRCP_AMT
	public double getPrcpAmtCount(WsRepyPlan wsRepyPlan) throws DAOException {
		double count = 0;
		try {
			count = (Double) getSqlMapClientTemplate().queryForObject(
					"WsRepyPlan.getPrcpAmtCount", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	
	//同一个合同的最大到期日
	public String getMaxEndDate(WsRepyPlan wsRepyPlan) throws DAOException {
		String matEndDate = "";
		try {
			matEndDate = (String) getSqlMapClientTemplate().queryForObject(
					"WsRepyPlan.getMaxEndDate", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return matEndDate;
	}
	
	//取期次号（判断期次号连续）
	public List<WsRepyPlan> getByPactNoList(WsRepyPlan wsRepyPlan) throws DAOException {
		List<WsRepyPlan> wsRepyPlanList = new ArrayList<WsRepyPlan>();
		try {
			wsRepyPlanList = (List<WsRepyPlan>) getSqlMapClientTemplate().queryForList(
					"WsRepyPlan.getByPactNoList", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return wsRepyPlanList;
	}
	
	//取开始日和结束日（判断本期次起始日应该等于上一期的到期日）
//	public WsRepyPlan getBybegendDate(WsRepyPlan wsRepyPlan) throws DAOException {
//		WsRepyPlan wsrp = new WsRepyPlan();
//		try {
//			wsrp = (WsRepyPlan) getSqlMapClientTemplate().queryForList(
//					"WsRepyPlan.getBybegendDate", wsRepyPlan);
//		} catch (Exception e) {
//			log.error(e);
//			throw new DAOException("查询失败");
//		}
//		return wsrp;
//	}
	
	//取每一期的利息(必须大于等于0)  取开始日和结束日（判断本期次起始日应该等于上一期的到期日）
	public WsRepyPlan getByPactNoCnt(WsRepyPlan wsRepyPlan) throws DAOException {
		WsRepyPlan wsrp = new WsRepyPlan();
		try {
			wsrp = (WsRepyPlan) getSqlMapClientTemplate()
			.queryForObject("WsRepyPlan.getNormInt", wsRepyPlan);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return wsrp;
	}
	
}