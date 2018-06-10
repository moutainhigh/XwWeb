package  app.creditapp.trade.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.trade.dao.CipTradeDao;
import app.creditapp.trade.entity.CipTrade;
/**
* Title: CipTradeDaoImpl.java
* Description:
**/
public class CipTradeDaoImpl extends BaseIbatisDao implements CipTradeDao {

	public void del(CipTrade cipTrade) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CipTrade.del", cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CipTrade> findByPage(CipTrade cipTrade) throws DAOException {
		List cipTradeList = null;
		try {
			cipTradeList = getSqlMapClientTemplate().queryForList(
					"CipTrade.findByPage", cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cipTradeList;
	}

	public CipTrade getById(CipTrade cipTrade) throws DAOException {
		try {
			cipTrade = (CipTrade) getSqlMapClientTemplate()
					.queryForObject("CipTrade.getById", cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cipTrade;
	}

	public int getCount(CipTrade cipTrade) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CipTrade.findPage.count", cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CipTrade cipTrade) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CipTrade.insert",
					cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CipTrade cipTrade) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CipTrade.update",
					cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * @方法说明:依据交易号与渠道 编号获取交易SQL语句
	 * @param channelId
	 * @param tradeId
	 * @return
	 * @throws DAOException 
	 * @see app.creditapp.trade.dao.CipTradeDao#getCipTradeSql(java.lang.String, java.lang.String)
	 * @修改说明:
	 */
	@Override
	public CipTrade getCipTradeSql(CipTrade cipTrade)
			throws DAOException {
		try {
			cipTrade = (CipTrade) getSqlMapClientTemplate()
					.queryForObject("CipTrade.getCipTradeSql", cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cipTrade;
	}

	/**
	 * @方法说明:
	 * @param tradeId
	 * @return
	 * @throws DAOException 
	 * @see app.creditapp.trade.dao.CipTradeDao#getCipTradeList(java.lang.String)
	 * @修改说明:
	 */
	public List<CipTrade> getCipTradeList(String trade_id) throws DAOException {
		List<CipTrade> cipTradeList = null;
		try {
			cipTradeList = getSqlMapClientTemplate().queryForList(
					"CipTrade.getCipTradeList", trade_id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cipTradeList;
	}

	@Override
	public CipTrade getByReqMsgid(CipTrade cipTrade) throws DAOException {
		try {
			cipTrade = (CipTrade) getSqlMapClientTemplate()
					.queryForObject("CipTrade.getByReqMsgid", cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cipTrade;
	}

	@Override
	public void updateTxFlag(CipTrade cipTrade) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CipTrade.updateTxFlag",
					cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void updateTxFlagByTradeId(CipTrade cipTrade) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CipTrade.updateTxFlagByTradeId",
					cipTrade);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
}