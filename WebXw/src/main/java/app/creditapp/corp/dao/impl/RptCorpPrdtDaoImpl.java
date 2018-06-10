package app.creditapp.corp.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.RptCorpPrdtDao;
import app.creditapp.corp.entity.RptCorpPrdt;
public class RptCorpPrdtDaoImpl extends BaseIbatisDao implements RptCorpPrdtDao {
		public void delete(RptCorpPrdt rptCorpPrdt) throws DAOException {
			try {
				getSqlMapClientTemplate().delete("RptCorpPrdt.del",rptCorpPrdt);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("删除失败");
			}
		}

		@SuppressWarnings("unchecked")
		public List<RptCorpPrdt> findByPage(Map map) throws DAOException {
			List<RptCorpPrdt> rptCorpPrdtlist = null;
			try {
				rptCorpPrdtlist = getSqlMapClientTemplate().queryForList(
						"RptCorpPrdt.findByPage", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("分页查询失败");
			}
			return rptCorpPrdtlist;
		}
		
		@SuppressWarnings("unchecked")
		public List<RptCorpPrdt> findByAll(RptCorpPrdt rptCorpPrdt) throws DAOException {
			List<RptCorpPrdt> rptCorpPrdtlist = null;
			try {
				rptCorpPrdtlist = getSqlMapClientTemplate().queryForList(
						"RptCorpPrdt.findByAll", rptCorpPrdt);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DAOException("查询全部失败");
			}
			return rptCorpPrdtlist;
		}

		public RptCorpPrdt getById(RptCorpPrdt rptCorpPrdt) throws DAOException {
			try {
				rptCorpPrdt = (RptCorpPrdt) getSqlMapClientTemplate().queryForObject(
						"RptCorpPrdt.getById", rptCorpPrdt);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return rptCorpPrdt;
		}
		
		public int getCount(RptCorpPrdt rptCorpPrdt) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"RptCorpPrdt.findPage.count", rptCorpPrdt);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return count;
		}

		public String insert(RptCorpPrdt rptCorpPrdt) throws DAOException {
			String sid = "";
			try {
				sid = (String)getSqlMapClientTemplate().insert("RptCorpPrdt.insert", rptCorpPrdt);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("新增失败");
			}
			return sid;
		}
		
		@SuppressWarnings("unchecked")
		public void insertBatch(final List<RptCorpPrdt> rptCorpPrdtList) throws DAOException {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// 每次提交最大条数
					final int batchSize = 200;
					int count = 0;
					for (RptCorpPrdt rptCorpPrdt : rptCorpPrdtList) {
						executor.insert("RptCorpPrdt.insert", rptCorpPrdt);
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

		public void update(RptCorpPrdt rptCorpPrdt) throws DAOException {
			try {
				getSqlMapClientTemplate().insert("RptCorpPrdt.update", rptCorpPrdt);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("更新失败");
			}
		}
		
}
