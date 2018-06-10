package app.creditapp.ln.bo.impl;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.ln.bo.DebitCountBo;
import app.creditapp.ln.dao.DebitCountDao;
import app.creditapp.ln.entity.DebitCount;
import app.util.toolkit.Ipage;

public class DebitCountBoImpl extends BaseService implements DebitCountBo {
	
	private DebitCountDao debitCountDao;
	@Override
	public Ipage findByPage(Ipage ipg, DebitCount debitCount)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) debitCountDao
					.getCount(debitCount) });// 初始化分页类
			debitCount.setStartNumAndEndNum (ipg);
			ipg.setResult(debitCountDao.findByPage(debitCount));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public DebitCountDao getDebitCountDao() {
		return debitCountDao;
	}
	public void setDebitCountDao(DebitCountDao debitCountDao) {
		this.debitCountDao = debitCountDao;
	}

}
