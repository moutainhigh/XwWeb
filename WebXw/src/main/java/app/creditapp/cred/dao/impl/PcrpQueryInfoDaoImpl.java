package app.creditapp.cred.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cred.dao.PcrpQueryInfoDao;
import app.creditapp.cred.entity.PcrpQueryInfo;
import app.oscache.CachecodeUtil;

/**
 * 
  * 实现类名称：PcrpQueryInfoDaoImpl
  * 类描述：个人征信实现方法 
  * 创建人：lidong dhcc 
  * 创建时间：2015-7-15 上午01:13:33
  * 修改人：Administrator  
 * @version
 */
public class PcrpQueryInfoDaoImpl extends BaseIbatisDao implements PcrpQueryInfoDao {

	/**
	 * 查询登记信息
	 * 创建人：lidong dhcc
	 */
	public List<PcrpQueryInfo> findByPage(PcrpQueryInfo pcrpDocInfo) throws DAOException {
		List CrpDocInfoList = null;
		try {
			CrpDocInfoList = getSqlMapClientTemplate().queryForList("PcrpQueryInfo.selectAll", pcrpDocInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return CrpDocInfoList;
	}
	public List ExportDate(PcrpQueryInfo pcrpQueryInfo) throws DAOException {
		List pcrpQueryInfoList = null;
		try {
			pcrpQueryInfoList = getSqlMapClientTemplate().queryForList("PcrpQueryInfo.selectExportDate", pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return pcrpQueryInfoList;
	}
	
	/**
	 * 统计总的登记信息总数
	 * 创建人：lidong dhcc
	 */
	public int getCount(PcrpQueryInfo pcrpDocInfo) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject("PcrpQueryInfo.findPage.count",pcrpDocInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}

	/**
	 * 统计条件查询登记信息总数
	 * 创建人：lidong dhcc
	 */
	
	public int singleGetCount(PcrpQueryInfo pcrpDocInfo) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
						"PcrpQueryInfo.singleFindPage.count",pcrpDocInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	
	//根据查询条件进行人行查征笔数查询
	public int getBankCount(PcrpQueryInfo pcrpQueryInfo) throws DAOException{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
						"PcrpQueryInfo.getBankCount.count",pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	//根据查询条件进行条数查询
	public int getBatchCount(PcrpQueryInfo pcrpQueryInfo) throws DAOException{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PcrpQueryInfo.getBatchCount.count",pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	/**
	 * 个人单笔条件查询登记信息
	 * 创建人：lidong dhcc
	 */
	public List<PcrpQueryInfo> singleFindByPage(PcrpQueryInfo pcrpDocInfo)
			throws DAOException {
		List CrpDocInfoList = null;
		try {
			CrpDocInfoList = getSqlMapClientTemplate().queryForList("PcrpQueryInfo.singleFindByPage", pcrpDocInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return CrpDocInfoList;
	}


	public void insert(PcrpQueryInfo pcrpDocInfo) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PcrpQueryInfo.insert",pcrpDocInfo);
			//更新授权文件使用状态
//			if(pcrpDocInfo.getCrpSts().equals(CachecodeUtil.CRP_CRP_STS1)&&pcrpDocInfo.getCrpReason().equals(CachecodeUtil.CRP_TYPE0_REASON_CREDLAT)){
//				String aftid= pcrpDocInfo.getAft_id();
//		    getSqlMapClientTemplate().update("PcrpQueryInfoAFT.updateaftloanuseflag",aftid);
//			}else{
//			getSqlMapClientTemplate().update("PcrpGrantFileInfo.updateuseflag",pcrpDocInfo.getGrandId());
//			}
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("插入失败");
		}
	}


	public PcrpQueryInfo getByCrpId(PcrpQueryInfo pcrpQueryInfo) {
		try {
			pcrpQueryInfo = (PcrpQueryInfo) getSqlMapClientTemplate().queryForObject("PcrpQueryInfo.getByCrpId", pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return pcrpQueryInfo;
	}
	
	//根据客户信息查询本地信用报告
	public List<PcrpQueryInfo> getByCrp(PcrpQueryInfo pcrpQueryInfo) {
		List<PcrpQueryInfo> list = null;
		try {
			list = (List<PcrpQueryInfo>) getSqlMapClientTemplate().queryForObject("PcrpQueryInfo.getByCrp", pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("本地查询客户信用报告失败");
		}
		return list;
	}
	
	//根据客户信息查询本地信用报告
	public String getByUrl(PcrpQueryInfo pcrpQueryInfo) {
		String url = "";
		try {
			url = (String)getSqlMapClientTemplate().queryForObject("PcrpQueryInfo.getByUrl", pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("本地查询客户信用报告失败");
		}
		return url;
	}
	
	//根据客户证件号码，查询征信信息表中有无数据
	public PcrpQueryInfo existValid(PcrpQueryInfo pcrpQueryInfo) throws DAOException {
		try {
			pcrpQueryInfo = (PcrpQueryInfo) getSqlMapClientTemplate().queryForObject("pcrpQueryInfo.existValid", pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return pcrpQueryInfo;
	}
	
	//根据客户证件号码，更新状态
	public void update(PcrpQueryInfo pcrpQueryInfo) throws DAOException {
		try {
			 getSqlMapClientTemplate().update(
					"pcrpQueryInfo.update", pcrpQueryInfo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

}
