package  app.creditapp.fund.bo.impl;

import app.creditapp.fund.dao.FundBaseDao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.DBUtils;
import app.util.ruleFiter.entity.ValidateLog;
import app.util.ruleFiter.fiter.FiterEngineInterface;
import app.util.toolkit.Ipage;
import app.creditapp.fund.action.FundBaseAction;
import app.creditapp.fund.bo.FundDetailBo;
import app.creditapp.fund.dao.FundDetailDao;

import app.creditapp.fund.entity.FundBase;
import app.creditapp.fund.entity.FundDetail;
/**
* Title: FundDetailBoImplImpl.java
* Description:
**/
public class FundDetailBoImpl extends BaseService implements FundDetailBo {

	private FundDetailDao fundDetailDao;
	private FiterEngineInterface fiterEngineInterface;
    private FundBaseDao fundBaseDao;
	public void del(FundDetail fundDetail) throws ServiceException {
		try {
			fundDetailDao.del(fundDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, FundDetail fundDetail)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) fundDetailDao
					.getCount(fundDetail) });// 初始化分页类
			fundDetail.setStartNumAndEndNum (ipg);
			ipg.setResult(fundDetailDao.findByPage(fundDetail));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public FundDetail getById(FundDetail fundDetail) throws ServiceException {
		try {
			fundDetail = fundDetailDao.getById(fundDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return fundDetail;
	}

	public void insert(FundDetail fundDetail) throws ServiceException {
		try {
			//1.插入资金明细（次级信息）
			fundDetailDao.insert(fundDetail);
			//2.修改资金信息中次级资金
			//3.修改分配方案
			//4.修改分配方案存续
			//5.修改分配方案明细
			//6.修改兑付计划
			//在存贮过程中处理
			//xbb修改
			//1.插入资金明细的同时，基本信息的资金总额以及资金中现金金额增加
			//insert_up(fundDetail);
			FundBase fundBase=new FundBase();
			//给fdAmt赋值
			fundBase.setFdAmt(fundDetail.getTxAmt());
			//给fundNo赋值
			fundBase.setFundNo(fundDetail.getFundNo());
			fundBaseDao.updateCash(fundBase); 
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public double getBalBytradeType(FundDetail fundDetail) throws ServiceException {
		Double bal = 0.00;
		try {
			bal = fundDetailDao.getBalBytradeType(fundDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return bal;
	}
	public void update(FundDetail fundDetail) throws ServiceException {
		try {
			fundDetailDao.update(fundDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void insert_only(FundDetail fundDetail) throws ServiceException {
		try {
			fundDetailDao.insert(fundDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	// 调用存储过程更新批量插入插入资金明细后影响
	public void insert_up(FundDetail fundDetail) throws ServiceException {
		Connection conn = null;
		CallableStatement proc = null;
		try {
			conn = DBUtils.getConn();
			// 调用存储过程更新插入明细后影响
			proc = conn.prepareCall("{call PKG_FUND.PROC_FUND_DETAIL_UPDATE(?,?,?,?,?)}"); // 设置存储过程
			proc.setString(1, fundDetail.getFundNo()); // 设置第1个参数输入参数
			proc.setString(2, fundDetail.getTradeType());
			proc.setDouble(3, fundDetail.getTxAmt());
			proc.setString(4, fundDetail.getTxDate());
			proc.setString(5, fundDetail.getOpNo());
			proc.execute(); // 执行
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(proc);
			DBUtils.closeConnection(conn);
		}
	}
	//判断该资金是否存在该存续编号
	public boolean isExatis(String termNo, String fundNo) throws ServiceException {
		Connection conn = null;
	//	CallableStatement proc = null;
		PreparedStatement psta = null;
		boolean val = false;
		try {
			conn = DBUtils.getConn();
			String sql = "SELECT TERM_NO FROM fund_split_term a," +
					"fund_split b where a.part_no = b.part_no and a.term_amt>0 " +
					"and a.end_date = b.end_date and a.fund_no = ?"; // sql查询语句
			psta = conn.prepareStatement(sql);
			psta.setString(1, fundNo); // 设置输入参数
			ResultSet rs = psta.executeQuery(); // 执行
			while(rs.next()){
				if(termNo.equals(rs.getString(1))){
					val = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(psta);
			DBUtils.closeConnection(conn);
		}
		return val;
	}
	//获取明细表的最迟日期
	public String getMaxDate(FundDetail fundDetail) throws ServiceException{
		String date = "";
		try {
			date = fundDetailDao.getMaxDate(fundDetail);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return date;
	}	
	//调用校验规则校验资金明细信息
	public String validate(FundDetail fundDetail) throws ServiceException{
		String val = null;
		ValidateLog _vl = null;
		try{
			_vl = fiterEngineInterface.createValidateLog("fundDetail",fundDetail, true);
		}catch(Exception e){
			e.printStackTrace();
		}
		val = _vl.getErrorMessage();
		return val;
	}
	public FundDetailDao getFundDetailDao() {
		return fundDetailDao;
	}

	public void setFundDetailDao(FundDetailDao fundDetailDao) {
		this.fundDetailDao = fundDetailDao;
	}

	public FiterEngineInterface getFiterEngineInterface() {
		return fiterEngineInterface;
	}

	public void setFiterEngineInterface(FiterEngineInterface fiterEngineInterface) {
		this.fiterEngineInterface = fiterEngineInterface;
	}

	public FundBaseDao getFundBaseDao() {
		return fundBaseDao;
	}

	public void setFundBaseDao(FundBaseDao fundBaseDao) {
		this.fundBaseDao = fundBaseDao;
	}
	
}