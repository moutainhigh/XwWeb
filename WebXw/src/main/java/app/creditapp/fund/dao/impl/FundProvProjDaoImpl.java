package  app.creditapp.fund.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.fund.dao.FundProvProjDao;
import app.creditapp.fund.entity.FundProvProj;
import app.creditapp.bat.entity.RptPrdtDaily;
/**
* Title: FundProvProjDaoImpl.java
* Description:
**/
public class FundProvProjDaoImpl extends BaseIbatisDao implements FundProvProjDao {

	public void del(FundProvProj fundProvProj) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("FundProvProj.del", fundProvProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<FundProvProj> findByPage(FundProvProj fundProvProj) throws DAOException {
		List fundProvProjList = null;
		try {
			fundProvProjList = getSqlMapClientTemplate().queryForList(
					"FundProvProj.findByPage", fundProvProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvProjList;
	}
	
	public List<FundProvProj> findAll(FundProvProj fundProvProj) throws DAOException {
		List fundProvProjList = null;
		try {
			fundProvProjList = getSqlMapClientTemplate().queryForList(
					"FundProvProj.findAll", fundProvProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvProjList;
	}

	public FundProvProj getById(FundProvProj fundProvProj) throws DAOException {
		try {
			fundProvProj = (FundProvProj) getSqlMapClientTemplate()
					.queryForObject("FundProvProj.getById", fundProvProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return fundProvProj;
	}

	public int getCount(FundProvProj fundProvProj) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"FundProvProj.findPage.count", fundProvProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(FundProvProj fundProvProj) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("FundProvProj.insert",
					fundProvProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(FundProvProj fundProvProj) throws DAOException {
		try {
			getSqlMapClientTemplate().update("FundProvProj.update",
					fundProvProj);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//获取项目对应累计放贷额
	public List<RptPrdtDaily> RptRrdtDailygetByPrdtNoamt(RptPrdtDaily rptPrdtDaily) throws DAOException {
		List rptPrdtDailyList = null;
		try {
			rptPrdtDailyList = getSqlMapClientTemplate().queryForList(
					"RptRrdtDaily.getByPrdtNoamt", rptPrdtDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rptPrdtDailyList;
	}
	public double RptRrdtDailygetByProjNoamt(RptPrdtDaily rptPrdtDaily) throws DAOException {
		double count = 0;
		try {
			count =  (Double) getSqlMapClientTemplate().queryForObject(
					"RptRrdtDaily.getByProjNoamt", rptPrdtDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
    public RptPrdtDaily RptRrdtDailygetBymaxDate(RptPrdtDaily rptPrdtDaily) throws DAOException {
			try {
				rptPrdtDaily = (RptPrdtDaily) getSqlMapClientTemplate()
						.queryForObject("RptRrdtDaily.getBymaxDate", rptPrdtDaily);
			} catch (Exception e) {
				log.error(e);
				throw new DAOException(e.getMessage());
			}
			return rptPrdtDaily;
		}
    //获取时间段内不同产品累计放贷量
	public List<RptPrdtDaily> RptRrdtDailygetByprdtno(RptPrdtDaily rptPrdtDaily) throws DAOException {
		List rptPrdtDailyList = null;
		try {
			rptPrdtDailyList = getSqlMapClientTemplate().queryForList(
					"RptRrdtDaily.getByprdtno", rptPrdtDaily);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return rptPrdtDailyList;
	}
	//获取provProjNo
	public String getProvProjNo() throws DAOException {
		String count = null;
		try {
			count = (String) getSqlMapClientTemplate().queryForObject(
					"FundProvProj.getprovProjNo");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public String getProjNameByProjNo(String projNo) throws DAOException {
		String projName = null;
		try {
			projName = (String) getSqlMapClientTemplate().queryForObject(
					"FundProvProj.getProjNameByProjNo",projNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return projName;
	}
}