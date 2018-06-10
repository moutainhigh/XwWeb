package  app.creditapp.trade.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.trade.bo.CipRuleBo;
import app.creditapp.trade.bo.CipTradeBo;
import app.creditapp.trade.dao.CipTradeDao;
import app.creditapp.trade.entity.CipRule;
import app.creditapp.trade.entity.CipTrade;
/**
* Title: CipTradeBoImplImpl.java
* Description:
**/
public class CipTradeBoImpl extends BaseService implements CipTradeBo {

	private CipTradeDao cipTradeDao;
	private CipRuleBo cipRuleBo;

	public void del(CipTrade cipTrade) throws ServiceException {
		try {
			cipTradeDao.del(cipTrade);
			CipRule cipRule = new CipRule();
			cipRule.setTrade_id(cipTrade.getTrade_id());
			cipRuleBo.delByTradeId(cipRule);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CipTrade cipTrade)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cipTradeDao
					.getCount(cipTrade) });// 初始化分页类
			cipTrade.setStartNumAndEndNum (ipg);
			ipg.setResult(cipTradeDao.findByPage(cipTrade));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CipTrade getById(CipTrade cipTrade) throws ServiceException {
		try {
			cipTrade = cipTradeDao.getById(cipTrade);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cipTrade;
	}

	public void insert(CipTrade cipTrade) throws ServiceException {
		try {
			cipTradeDao.insert(cipTrade);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CipTrade cipTrade) throws ServiceException {
		try {
			cipTradeDao.update(cipTrade);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	 * @方法说明:依据交易号与渠道 编号获取交易SQL语句
	 * @param channelId
	 * @param tradeId
	 * @return
	 * @throws ServiceException 
	 * @see app.creditapp.trade.bo.CipTradeBo#getCipTradeSql(java.lang.String, java.lang.String)
	 * @修改说明:
	 */
	@Override
	public CipTrade getCipTradeSql(String channel_id, String trade_id)
			throws ServiceException {
		CipTrade cipTrade = new CipTrade();
		cipTrade.setChannel_id(channel_id);
		cipTrade.setTrade_id(trade_id);
		try {
			cipTrade = cipTradeDao.getCipTradeSql(cipTrade);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cipTrade;
	}
	/**
	 * @方法说明:
	 * @param tradeId
	 * @return
	 * @throws ServiceException 
	 * @see app.creditapp.trade.bo.CipTradeBo#getCipTradeList(java.lang.String)
	 * @修改说明:
	 */
	@Override
	public List<CipTrade> getCipTradeList(String trade_id)
			throws ServiceException {
		List<CipTrade> cipTradeList = null;
		try {
			cipTradeList = cipTradeDao.getCipTradeList(trade_id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cipTradeList;
	}
	public CipTradeDao getCipTradeDao() {
		return cipTradeDao;
	}

	public void setCipTradeDao(CipTradeDao cipTradeDao) {
		this.cipTradeDao = cipTradeDao;
	}

	public CipRuleBo getCipRuleBo() {
		return cipRuleBo;
	}

	public void setCipRuleBo(CipRuleBo cipRuleBo) {
		this.cipRuleBo = cipRuleBo;
	}

	public CipTrade getByReqMsgid(CipTrade cipTrade) throws ServiceException {
		try {
			cipTrade = cipTradeDao.getByReqMsgid(cipTrade);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cipTrade;
	}

	@Override
	public void updateTxFlag(CipTrade cipTrade) throws ServiceException {
		try {
			cipTradeDao.updateTxFlag(cipTrade);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateTxFlagByTradeId(CipTrade cipTrade)
			throws ServiceException {
		try {
			cipTradeDao.updateTxFlagByTradeId(cipTrade);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
}