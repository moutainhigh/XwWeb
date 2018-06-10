package  app.creditapp.cif.bo.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.cif.bo.CifPersInfBo;
import app.creditapp.cif.dao.CifPersHisDao;
import app.creditapp.cif.dao.CifPersInfDao;
import app.creditapp.cif.entity.CifPersHis;
import app.creditapp.cif.entity.CifPersInf;
import app.creditapp.cif.entity.CifPersRelCore;
import app.creditapp.cif.entity.CifPersRelLine;
import app.creditapp.cred.entity.PcrpQueryInfo;
import app.util.DBUtils;
import app.util.toolkit.Ipage;
import oracle.jdbc.OracleTypes;
/**
* Title: CifPersBoImplImpl.java
* Description:
**/
public class CifPersInfBoImpl extends BaseService implements CifPersInfBo {

	private CifPersInfDao cifPersInfDao;
	private CifPersHisDao cifPersHisDao;
//	private CreditSelService creditSelResultInfoDao; //单笔查征结果接口dao

	public void del(CifPersInf cifPersInf) throws ServiceException {
		try {
			cifPersInfDao.del(cifPersInf);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, CifPersInf cifPersInf)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) cifPersInfDao
					.getCount(cifPersInf) });// 初始化分页类
			cifPersInf.setStartNumAndEndNum (ipg);
			ipg.setResult(cifPersInfDao.findByPage(cifPersInf));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public CifPersInf getById(CifPersInf cifPersInf) throws ServiceException {
		try {
			cifPersInf = cifPersInfDao.getById(cifPersInf);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return cifPersInf;
	}

	public void insert(CifPersInf cifPersInf) throws ServiceException {
		try {
			/**
			 * 新增客户信息的客户号是序列
			 */
			cifPersInf.setCifNo(cifPersInfDao.getKey());
			
			cifPersInfDao.insert(cifPersInf);
			
			/**
			 * 新增客户信息的同时，把对应的客户信息添加到历史表中，保存历史数据
			 */
			//cifPersHisBo.insert(cifPersInf);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(CifPersInf cifPersInf) throws ServiceException {
		
		CifPersHis cifPersHis = new CifPersHis();
		String cifSeq = "";
		
		try {
			
			cifPersInfDao.update(cifPersInf);
			
			/**
			 * xiugai客户信息的同时，把对应的客户信息添加到历史表中，保存历史数据
			 */
			BeanUtils.copyProperties(cifPersHis, cifPersInf);
			cifSeq = cifPersHisDao.getSeqKey();
			cifPersHis.setRecId(cifSeq);
			cifPersHisDao.insert(cifPersHis);
			
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 单笔查询征信结果调用 孙明义
	 */
	public String getByCrp(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		try {
			//将对象转json
			String selBatchJson = JSON.toJSONString(pcrpQueryInfo);
			//调用服务端接口
			String selBatch = null;
//			String selBatch = creditSelResultInfoDao.analyticForOneOnLocal(selBatchJson);
		return selBatch;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	

	@Override
	public List<CifPersRelCore> getCifPersRelCores(CifPersInf cifPers)
			throws ServiceException {
		List<CifPersRelCore> list = new ArrayList<CifPersRelCore>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			// conn = DBUtil.getConnection();
			proc = conn.prepareCall("{ call proc_getrel_core(?,?) }"); // 设置存储过程
			proc.setString(1, cifPers.getIdNo());// 设置第一个参数输入参数
			proc.registerOutParameter(2, OracleTypes.CURSOR);// 第二个参数输出参数,是VARCHAR类型的
			// proc.registerOutParameter(2,
			// OracleTypes.CURSOR);//第二个参数输出参数,是游标类型的
			proc.execute();// 执行
			rs = (ResultSet)proc.getObject(2);
			while (rs != null && rs.next()) {
				CifPersRelCore crl = new CifPersRelCore();
				crl.setIdNo(rs.getString(1));
				crl.setCifName(rs.getString(2));
				crl.setRel(rs.getString(3));
				list.add(crl);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
			DBUtils.closeStatement(proc);
			DBUtils.closeResultset(rs);
		}
		return list;
	}

	@Override
	public List<CifPersRelLine> getCifPersRelLines(CifPersInf cifPers)
			throws ServiceException {
		List<CifPersRelLine> list = new ArrayList<CifPersRelLine>();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			// conn = DBUtil.getConnection();
			proc = conn.prepareCall("{ call proc_getrel_lines(?,?) }"); // 设置存储过程
			proc.setString(1, cifPers.getIdNo());// 设置第一个参数输入参数
			proc.registerOutParameter(2, OracleTypes.CURSOR);// 第二个参数输出参数,是VARCHAR类型的
			// proc.registerOutParameter(2,
			// OracleTypes.CURSOR);//第二个参数输出参数,是游标类型的
			proc.execute();// 执行
			rs = (ResultSet)proc.getObject(2);
			while (rs != null && rs.next()) {
				CifPersRelLine crl = new CifPersRelLine();
				crl.setIdNo(rs.getString(1));
				crl.setCifName(rs.getString(2));
				crl.setRel(rs.getString(3));
				crl.setRelIdNo(rs.getString(4));
				crl.setRelCifName(rs.getString(5));
				list.add(crl);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} finally {
			DBUtils.closeConnection(conn);
			DBUtils.closeStatement(proc);
			DBUtils.closeResultset(rs);
		}
		return list;
	}

	public CifPersInfDao getCifPersInfDao() {
		return cifPersInfDao;
	}

	public void setCifPersInfDao(CifPersInfDao cifPersInfDao) {
		this.cifPersInfDao = cifPersInfDao;
	}
	
	public CifPersHisDao getCifPersHisDao() {
		return cifPersHisDao;
	}

	public void setCifPersHisDao(CifPersHisDao cifPersHisDao) {
		this.cifPersHisDao = cifPersHisDao;
	}

	

}