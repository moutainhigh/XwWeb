package  app.creditapp.inf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.inf.dao.WsRepyFineDao;
import app.creditapp.inf.entity.WsIn2301_1_1;
import app.creditapp.inf.entity.WsRepyFine;
/**
* Title: WsRepyFineDaoImpl.java
* Description:
**/
public class WsRepyFineDaoImpl extends BaseIbatisDao implements WsRepyFineDao {

	public void del(WsRepyFine wsRepyFine) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WsRepyFine.del", wsRepyFine);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WsRepyFine> findByPage(WsRepyFine wsRepyFine) throws DAOException {
		List wsRepyFineList = null;
		try {
			wsRepyFineList = getSqlMapClientTemplate().queryForList(
					"WsRepyFine.findByPage", wsRepyFine);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyFineList;
	}
	
	public List<WsRepyFine> getListByWsId(WsRepyFine wsRepyFine) throws DAOException {
		List wsRepyFineList = null;
		try {
			wsRepyFineList = getSqlMapClientTemplate().queryForList(
					"WsRepyFine.getListByWsId", wsRepyFine);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyFineList;
	}

	public WsRepyFine getById(WsRepyFine wsRepyFine) throws DAOException {
		try {
			wsRepyFine = (WsRepyFine) getSqlMapClientTemplate()
					.queryForObject("WsRepyFine.getById", wsRepyFine);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRepyFine;
	}

	public int getCount(WsRepyFine wsRepyFine) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WsRepyFine.findPage.count", wsRepyFine);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WsRepyFine wsRepyFine) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsRepyFine.insert",
					wsRepyFine);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(WsRepyFine wsRepyFine) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsRepyFine.update",
					wsRepyFine);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	// 列表插入数据库ws2301接口使用
	public void insertBatchfor2301(final List<WsIn2301_1_1> list2301_1_1list)
			throws DAOException {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				// 每次提交最大条数
				final int batchSize = 100;
				int count = 0;
				for (WsIn2301_1_1 wsIn2301_1_1 : list2301_1_1list) {
					// 插入预进件申请信息
					executor.insert("WsRepyFine.insert", wsIn2301_1_1);
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
}