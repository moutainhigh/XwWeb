package app.creditapp.inf.dao.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.inf.dao.WsRepyMesDao;
import app.creditapp.inf.entity.WsOut2302_1;
import app.creditapp.inf.entity.WsRepyMes;
import app.creditapp.inf.entity.WsRepyMes_Count;

import com.ibatis.sqlmap.client.SqlMapExecutor;
public class WsRepyMesDaoImpl extends BaseIbatisDao implements WsRepyMesDao {
		public void delete(WsRepyMes wsRepyMes) throws DAOException {
			try {
				getSqlMapClientTemplate().delete("WsRepyMes.del",wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("删除失败");
			}
		}

		@SuppressWarnings("unchecked")
		public List<WsRepyMes> findByPage(Map map) throws DAOException {
			List<WsRepyMes> wsRepyMeslist = null;
			try {
				wsRepyMeslist = getSqlMapClientTemplate().queryForList(
						"WsRepyMes.findByPage", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("分页查询失败");
			}
			return wsRepyMeslist;
		}
		/*//接口ws2302分页处理
		public List<WsRepyMes> findByPage(Map map) throws DAOException {
			List<WsRepyMes> wsRepyMeslist = null;
			try {
				wsRepyMeslist = getSqlMapClientTemplate().queryForList(
						"WsRepyMes.findByPage", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("分页查询失败");
			}
			return wsRepyMeslist;
		}*/
		
		@SuppressWarnings("unchecked")
		public List<WsRepyMes> findByAll(Map map) throws DAOException {
			List<WsRepyMes> wsRepyMeslist = null;
			try {
				wsRepyMeslist = getSqlMapClientTemplate().queryForList(
						"WsRepyMes.findByAll", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询全部失败");
			}
			return wsRepyMeslist;
		}

		public WsRepyMes getById(WsRepyMes wsRepyMes) throws DAOException {
			try {
				wsRepyMes = (WsRepyMes) getSqlMapClientTemplate().queryForObject(
						"WsRepyMes.getById", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return wsRepyMes;
		}
		
		public WsOut2302_1 getByIdForNew(WsRepyMes wsRepyMes) throws DAOException {
			WsOut2302_1 wsOut2302_1 = new WsOut2302_1();
			try {
				wsOut2302_1 = (WsOut2302_1) getSqlMapClientTemplate().queryForObject(
						"WsRepyMes.getIdForNew", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return wsOut2302_1;
		}
		
		public int getCount(WsRepyMes wsRepyMes) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"WsRepyMes.findPage.count", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return count;
		}

		public String insert(WsRepyMes wsRepyMes) throws DAOException {
			String sid = "";
			try {
				sid = (String)getSqlMapClientTemplate().insert("WsRepyMes.insert", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("新增失败");
			}
			return sid;
		}
		
		@SuppressWarnings("unchecked")
		public void insertBatch(final List<WsRepyMes> wsRepyMesList) throws DAOException {
			getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					// 每次提交最大条数
					final int batchSize = 200;
					int count = 0;
					for (WsRepyMes wsRepyMes : wsRepyMesList) {
						executor.insert("WsRepyMes.insert", wsRepyMes);
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

		public void update(WsRepyMes wsRepyMes) throws DAOException {
			try {
				getSqlMapClientTemplate().insert("WsRepyMes.update", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("更新失败");
			}
		}
		public void updateSts(WsRepyMes wsRepyMes) throws DAOException{
			try {
				getSqlMapClientTemplate().insert("WsRepyMes.updateSts", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("更新失败");
			}
		}
		//接口ws2302分页查找
		public List<WsOut2302_1> findByPageforws2302(WsRepyMes wsRepyMes) throws DAOException {
			List<WsOut2302_1> wsOut2302_1list = new ArrayList<WsOut2302_1>();
			try {
				wsOut2302_1list = getSqlMapClientTemplate().queryForList(
						"WsRepyMes.findByPageforws2302", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException(e.getMessage());
			}
			return wsOut2302_1list;
		}
		//接口ws2302得到符合条件的总数
		public int getCountforws2302(WsRepyMes wsRepyMes) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"WsRepyMes.findPage.countforws2302", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return count;
		}
		//结束
		//接口ws2301详细表删除相同合同号的数据
		public void deleteForPactNo() throws DAOException {
			try {
				getSqlMapClientTemplate().delete("WsRepyMesforPactNo.del");
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("删除失败");
			}
		}
		
		//根据批次返回列表
		public List<WsRepyMes> getByBatch(String wsRepyMesBatch) throws DAOException {
			List<WsRepyMes> wsRepyMeslist = new ArrayList<WsRepyMes>();
			try {
				wsRepyMeslist = getSqlMapClientTemplate().queryForList(
						"WsRepyMes.getByBatch", wsRepyMesBatch);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException(e.getMessage());
			}
			return wsRepyMeslist;
		}
		
		public WsRepyMes getByBatchAndPactNo(WsRepyMes wsRepyMes) throws DAOException {
			try {
				wsRepyMes = (WsRepyMes) getSqlMapClientTemplate().queryForObject(
						"WsRepyMes.getByBatchAndPactNo", wsRepyMes);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询失败");
			}
			return wsRepyMes;
		}
		//扣款信息统计
		public int getMesCount(WsRepyMes_Count wc) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"WsRepyMes.getCountMes.count", wc);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return count;
		}
		public List<WsRepyMes_Count> getCountMes(Map map) throws DAOException{
			List<WsRepyMes_Count> wcList = null;
			try {
				wcList = getSqlMapClientTemplate().queryForList(
						"WsRepyMes.getCountMes", map);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("查询统计失败");
			}
			return wcList;
		}
}