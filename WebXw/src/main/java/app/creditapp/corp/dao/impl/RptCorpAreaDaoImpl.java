package app.creditapp.corp.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.RptCorpAreaDao;
import app.creditapp.corp.entity.RptCorpArea;
public class RptCorpAreaDaoImpl extends BaseIbatisDao implements RptCorpAreaDao {
		public void delete(RptCorpArea rptCorpArea) throws DAOException {
			try {
				getSqlMapClientTemplate().delete("RptCorpArea.del",rptCorpArea);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("删除失败");
			}
		}

		@SuppressWarnings("unchecked")
		public List<RptCorpArea> findByPage(Map map) throws DAOException {
			List<RptCorpArea> rptCorpArealist = null;
			try {
				rptCorpArealist = getSqlMapClientTemplate().queryForList(
						"RptCorpArea.findByPage", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("分页查询失败");
			}
			return rptCorpArealist;
		}
		
		@SuppressWarnings("unchecked")
		public List<RptCorpArea> findByAll(RptCorpArea rptCorpArea) throws DAOException {
			List<RptCorpArea> rptCorpArealist = null;
			try {
				rptCorpArealist = getSqlMapClientTemplate().queryForList(
						"RptCorpArea.findByAll", rptCorpArea);
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				throw new DAOException("查询全部失败");
			}
			return rptCorpArealist;
		}

		public RptCorpArea getById(RptCorpArea rptCorpArea) throws DAOException {
			try {
				rptCorpArea = (RptCorpArea) getSqlMapClientTemplate().queryForObject(
						"RptCorpArea.getById", rptCorpArea);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return rptCorpArea;
		}
		
		public int getCount(RptCorpArea rptCorpArea) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"RptCorpArea.findPage.count", rptCorpArea);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return count;
		}

		public String insert(RptCorpArea rptCorpArea) throws DAOException {
			String sid = "";
			try {
				sid = (String)getSqlMapClientTemplate().insert("RptCorpArea.insert", rptCorpArea);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("新增失败");
			}
			return sid;
		}
		
		@SuppressWarnings("unchecked")
		public void insertBatch(final List<RptCorpArea> rptCorpAreaList) throws DAOException {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// 每次提交最大条数
					final int batchSize = 200;
					int count = 0;
					for (RptCorpArea rptCorpArea : rptCorpAreaList) {
						executor.insert("RptCorpArea.insert", rptCorpArea);
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

		public void update(RptCorpArea rptCorpArea) throws DAOException {
			try {
				getSqlMapClientTemplate().insert("RptCorpArea.update", rptCorpArea);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("更新失败");
			}
		}
		
}
