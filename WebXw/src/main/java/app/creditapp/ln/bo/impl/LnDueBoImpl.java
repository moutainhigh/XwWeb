package  app.creditapp.ln.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.acc.loan.bo.LoanBo;
import app.creditapp.ln.bo.LnDueBo;
import app.creditapp.ln.dao.LnDueDao;
import app.creditapp.ln.entity.LnDue;
import app.util.toolkit.Ipage;
/**
* Title: LnDueBoImplImpl.java
* Description:
**/
public class LnDueBoImpl extends BaseService implements LnDueBo {

	private LnDueDao lnDueDao;
	private LoanBo loanBo;
	
	public void del(LnDue lnDue) throws ServiceException {
		try {
			lnDueDao.del(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnDue lnDue)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnDueDao
					.getCount(lnDue) });// 初始化分页类
			lnDue.setStartNumAndEndNum (ipg);
			ipg.setResult(lnDueDao.findByPage(lnDue));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public Ipage findByPageForPop(Ipage ipg, LnDue lnDue)
	throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnDueDao
					.getCountForPop(lnDue) });// 初始化分页类
			lnDue.setStartNumAndEndNum (ipg);
			ipg.setResult(lnDueDao.findByPageForPop(lnDue));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public Ipage findByPageForRead(Ipage ipg, LnDue lnDue)
	throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnDueDao
					.getCountForRead(lnDue) });// 初始化分页类
			lnDue.setStartNumAndEndNum (ipg);
			ipg.setResult(lnDueDao.findByPageForRead(lnDue));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	//抽查回访获取列表
	public Ipage getCheckList(Ipage ipg, LnDue lnDue) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnDueDao
					.getCnt(lnDue) });// 初始化分页类
			lnDue.setStartNumAndEndNum (ipg);
			ipg.setResult(lnDueDao.getCheckList(lnDue));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public List<LnDue> findListByFundNo(LnDue lnDue) throws ServiceException{
		List lnDueList = null;
		try {
			lnDueList=(List<LnDue>)lnDueDao.findListByFundNo(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnDueList;	}

	public LnDue getById(LnDue lnDue) throws ServiceException {
		try {
			lnDue = lnDueDao.getById(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnDue;
	}
	public LnDue getByIdForPactNo(LnDue lnDue) throws ServiceException {
		try {
			lnDue = lnDueDao.getByIdForPactNo(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnDue;
	}
	
	public LnDue getByPactId(String pactId) throws ServiceException {
		LnDue lnDue = null;
		try {
			lnDue = lnDueDao.getByPactId(pactId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnDue;
	}
	
	public LnDue getByPactNoAndBrNo(LnDue lnDue) throws ServiceException {
		try {
			lnDue = lnDueDao.getByPactNoAndBrNo(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnDue;
	}
	
	@Override
	public Ipage findAllFail(Ipage ipg, LnDue lnDue) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnDueDao
					.getCountFail(lnDue) });// 初始化分页类
			lnDue.setStartNumAndEndNum (ipg);
			ipg.setResult(lnDueDao.findAllFail(lnDue));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public void insert(LnDue lnDue) throws ServiceException {
		try {
			lnDueDao.insert(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnDue lnDue) throws ServiceException {
		try {
			lnDueDao.update(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<LnDue> findList() throws ServiceException{
		List<LnDue>  lnDueList;
		try {
			lnDueList =  lnDueDao.findList();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnDueList;
	}
	
	public List<LnDue> findListToWorkF() throws ServiceException{
		List<LnDue> lnDueList;
		try {
			lnDueList =  lnDueDao.findListToWorkF();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnDueList;
	}
	public int dueStsUpdate(LnDue lnDue) throws ServiceException{
		int result = 0;
		try {
			result = lnDueDao.dueStsUpdate(lnDue);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return result;
	}
	public void getLnDueList(){
		List<LnDue> lnDueList = null;
		LnDue lnDue = new LnDue();
		
		lnDueList = lnDueDao.getLndueList(lnDue);
		for(int i=0;i<lnDueList.size();i++){
			
			lnDue = lnDueList.get(i);
			System.out.println(lnDue.getDueNo()+"==============================");
			loanBo.grantLoan(lnDue);
		}
	}

	public LnDueDao getLnDueDao() {
		return lnDueDao;
	}

	public void setLnDueDao(LnDueDao lnDueDao) {
		this.lnDueDao = lnDueDao;
	}

	public LoanBo getLoanBo() {
		return loanBo;
	}

	public void setLoanBo(LoanBo loanBo) {
		this.loanBo = loanBo;
	}

	
}