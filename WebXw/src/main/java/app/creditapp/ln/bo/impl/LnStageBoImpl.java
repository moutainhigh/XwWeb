package  app.creditapp.ln.bo.impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.ln.bo.LnStageBo;
import app.creditapp.ln.dao.LnStageDao;
import app.creditapp.ln.entity.LnStage;
import app.util.DBUtils;
import app.util.toolkit.Ipage;
/**
* Title: LnStageBoImplImpl.java
* Description:
**/
public class LnStageBoImpl extends BaseService implements LnStageBo {

	private LnStageDao lnStageDao;

	public void del(LnStage lnStage) throws ServiceException {
		try {
			lnStageDao.del(lnStage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, LnStage lnStage)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) lnStageDao
					.getCount(lnStage) });// 初始化分页类
			lnStage.setStartNumAndEndNum (ipg);
			ipg.setResult(lnStageDao.findByPage(lnStage));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public LnStage getById(LnStage lnStage) throws ServiceException {
		try {
			lnStage = lnStageDao.getById(lnStage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return lnStage;
	}

	public void insert(LnStage lnStage) throws ServiceException {
		try {
			lnStageDao.insert(lnStage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(LnStage lnStage) throws ServiceException {
		try {
			lnStageDao.update(lnStage);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void updatests(String appId,String columns,String value) throws ServiceException {
		Connection conn = null;
		CallableStatement proc = null;
		try {
			  conn = DBUtils.getConn();
			  //conn = DBUtil.getConnection(); // 单独调用进行测试用
			  // 调用存储过程更新ln_stage业务阶段表
		      proc = conn.prepareCall("{ call PKG_LN_APPLY.PROC_UP_STAGE(?,?,?) }"); //设置存储过程
		      proc.setString(1, appId); //设置第1个参数输入参数
		      proc.setString(2, columns); //设置第2个参数输入参数
		      proc.setString(3, value); //设置第3个参数输入参数
		      proc.execute(); // 执行
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
			DBUtils.closeStatement(proc);
		}
	}

	public LnStageDao getLnStageDao() {
		return lnStageDao;
	}

	public void setLnStageDao(LnStageDao lnStageDao) {
		this.lnStageDao = lnStageDao;
	}
}