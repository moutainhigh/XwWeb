package app.creditapp.proj.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.proj.dao.RptProjDao;
import app.creditapp.proj.entity.RptProj;
public class RptProjDaoImpl extends BaseIbatisDao implements RptProjDao {
		public void delete(RptProj rptProj) throws DAOException {
			try {
				getSqlMapClientTemplate().delete("RptProj.del",rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("删除失败");
			}
		}

		@SuppressWarnings("unchecked")
		public List<RptProj> findByPage(Map map) throws DAOException {
			List<RptProj> rptProjlist = null;
			try {
				rptProjlist = getSqlMapClientTemplate().queryForList(
						"RptProj.findByPage", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("分页查询失败");
			}
			return rptProjlist;
		}
		
		@SuppressWarnings("unchecked")
		public List<RptProj> findByAll(RptProj rptProj) throws DAOException {
			List<RptProj> rptProjlist = null;
			try {
				rptProjlist = getSqlMapClientTemplate().queryForList(
						"RptProj_Daily.findByAll", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询全部失败");
			}
			return rptProjlist;
		}
		
		@SuppressWarnings("unchecked")
		public List<RptProj> findByAllNum(RptProj rptProj) throws DAOException {
			List<RptProj> rptProjlist = null;
			try {
				rptProjlist = getSqlMapClientTemplate().queryForList(
						"RptProj_Daily.findByAllNum", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询全部失败");
			}
			return rptProjlist;
		}

		public RptProj getById(RptProj rptProj) throws DAOException {
			try {
				rptProj = (RptProj) getSqlMapClientTemplate().queryForObject(
						"RptProj.getById", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return rptProj;
		}

		public RptProj getDailyById(RptProj rptProj) throws DAOException {
			try {
				rptProj = (RptProj) getSqlMapClientTemplate().queryForObject(
						"RptProj_Daily.getById", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return rptProj;
		}		
		public int getCount(RptProj rptProj) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"RptProj.findPage.count", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return count;
		}

		public String insert(RptProj rptProj) throws DAOException {
			String sid = "";
			try {
				sid = (String)getSqlMapClientTemplate().insert("RptProj.insert", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("新增失败");
			}
			return sid;
		}
		
		@SuppressWarnings("unchecked")
		public void insertBatch(final List<RptProj> rptProjList) throws DAOException {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// 每次提交最大条数
					final int batchSize = 200;
					int count = 0;
					for (RptProj rptProj : rptProjList) {
						executor.insert("RptProj.insert", rptProj);
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

		public void update(RptProj rptProj) throws DAOException {
			try {
				getSqlMapClientTemplate().insert("RptProj.update", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("更新失败");
			}
		}
		@SuppressWarnings("unchecked")
		public List<RptProj> getAccountBal(RptProj rptProj) throws DAOException {
			List<RptProj> rptProjlist = null;
			try {
				rptProjlist = getSqlMapClientTemplate().queryForList(
						"RptProj_Daily.findByAll", rptProj);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询全部失败");
			}
			return rptProjlist;
		}
}
