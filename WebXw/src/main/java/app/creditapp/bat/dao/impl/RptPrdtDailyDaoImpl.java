package app.creditapp.bat.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.bat.dao.RptPrdtDailyDao;
import app.creditapp.bat.entity.RptPrdtDaily;
public class RptPrdtDailyDaoImpl extends BaseIbatisDao implements RptPrdtDailyDao {


		@SuppressWarnings("unchecked")
		@Override
		public List<RptPrdtDaily> getList(RptPrdtDaily rptPrdtDaily)
				throws DAOException {
			List<RptPrdtDaily> rptPrdtDailylist = null;
			try {
				rptPrdtDailylist = getSqlMapClientTemplate().queryForList(
						"RptPrdtDaily.getList", rptPrdtDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException("≤È—Ø ß∞‹");
			}
			return rptPrdtDailylist;
		}
		public int getCount(RptPrdtDaily rptPrdtDaily) throws DAOException {
			int count = 0;
			try {
				count = (Integer) getSqlMapClientTemplate().queryForObject(
						"RptPrdtDaily.count", rptPrdtDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException(e.getMessage());
			}
			return count;
		}
		
}
