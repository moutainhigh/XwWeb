package app.creditapp.corp.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.RptCorpDailyDao;
import app.creditapp.corp.entity.RptCorpDaily;

import com.ibatis.sqlmap.client.SqlMapExecutor;
public class RptCorpDailyDaoImpl extends BaseIbatisDao implements RptCorpDailyDao {
		public void delete(RptCorpDaily rptCorpDaily) throws DAOException {
			try {
				getSqlMapClientTemplate().delete("RptCorpDaily.del",rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("删除失败");
			}
		}

		@SuppressWarnings("unchecked")
		public List<RptCorpDaily> findByPage(Map map) throws DAOException {
			List<RptCorpDaily> rptCorpDailylist = null;
			try {
				rptCorpDailylist = getSqlMapClientTemplate().queryForList(
						"RptCorpDaily.findByPage", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("分页查询失败");
			}
			return rptCorpDailylist;
		}
		
		@SuppressWarnings("unchecked")
		public List<RptCorpDaily> findByAll(Map map) throws DAOException {
			List<RptCorpDaily> rptCorpDailylist = null;
			try {
				rptCorpDailylist = getSqlMapClientTemplate().queryForList(
						"RptCorpDaily.findByAll", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询全部失败");
			}
			return rptCorpDailylist;
		}

		public RptCorpDaily getById(RptCorpDaily rptCorpDaily) throws DAOException {
			try {
				rptCorpDaily = (RptCorpDaily) getSqlMapClientTemplate().queryForObject(
						"RptCorpDaily.getById", rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return rptCorpDaily;
		}
		
		public int getCount(RptCorpDaily rptCorpDaily) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"RptCorpDaily.findPage.count", rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return count;
		}

		public String insert(RptCorpDaily rptCorpDaily) throws DAOException {
			String sid = "";
			try {
				sid = (String)getSqlMapClientTemplate().insert("RptCorpDaily.insert", rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("新增失败");
			}
			return sid;
		}
		
		@SuppressWarnings("unchecked")
		public void insertBatch(final List<RptCorpDaily> rptCorpDailyList) throws DAOException {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// 每次提交最大条数
					final int batchSize = 200;
					int count = 0;
					for (RptCorpDaily rptCorpDaily : rptCorpDailyList) {
						executor.insert("RptCorpDaily.insert", rptCorpDaily);
						// 每10000条数据提交一次
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

		public void update(RptCorpDaily rptCorpDaily) throws DAOException {
			try {
				getSqlMapClientTemplate().insert("RptCorpDaily.update", rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("更新失败");
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<RptCorpDaily> getList(RptCorpDaily rptCorpDaily)
				throws DAOException {
			List<RptCorpDaily> rptCorpDailylist = null;
			try {
				rptCorpDailylist = getSqlMapClientTemplate().queryForList(
						"RptCorpDaily.getList", rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DAOException("查询失败");
			}
			return rptCorpDailylist;
		}

		@SuppressWarnings("unchecked")
		public List<RptCorpDaily> findByAllNum(RptCorpDaily rptCorpDaily) throws DAOException {
			List<RptCorpDaily> rptCorpDailylist = null;
			try {
				rptCorpDailylist = getSqlMapClientTemplate().queryForList(
						"RptCorpDaily.findByAllNum", rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DAOException("查询全部失败");
			}
			return rptCorpDailylist;
		}
		
		public RptCorpDaily findRpt(RptCorpDaily rptCorpDaily) throws DAOException {
			try {
				rptCorpDaily = (RptCorpDaily) getSqlMapClientTemplate().queryForObject(
						"RptCorpDaily.findRpt", rptCorpDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return rptCorpDaily;
		}
}
