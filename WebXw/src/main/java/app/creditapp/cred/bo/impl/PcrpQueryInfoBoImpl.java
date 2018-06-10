package app.creditapp.cred.bo.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.cred.bo.PcrpQueryInfoBo;
import app.creditapp.cred.dao.PcrpQueryInfoDao;
import app.creditapp.cred.dao.PcrpQueryInfoWebService;
import app.creditapp.cred.entity.PcrpQueryInfo;
import app.creditapp.inf.entity.WsIn4103;
import app.util.toolkit.Ipage;
/**
 * 
  * 类名称：PcrpQueryInfoBoImpl
  * 类描述： 
  *创建人：lidong dhcc
  * 创建时间：2015-7-15 上午01:30:19
  * 修改人：Administrator  
 * @version
 */
public class PcrpQueryInfoBoImpl extends BaseService implements PcrpQueryInfoBo {
	private PcrpQueryInfoDao pcrpQueryInfoDao;
//	private CreditSelService creditSelResultDao;
	private PcrpQueryInfoWebService pcrpQueryInfoWebService;
	
	public PcrpQueryInfoWebService getPcrpQueryInfoWebService() {
		return pcrpQueryInfoWebService;
	}


	public void setPcrpQueryInfoWebService(
			PcrpQueryInfoWebService pcrpQueryInfoWebService) {
		this.pcrpQueryInfoWebService = pcrpQueryInfoWebService;
	}


	/**
	 * 分页查询全部登记信息
	 * 创建人：lidong dhcc
	 */
	
	public Ipage findByPage(Ipage ipg, PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) pcrpQueryInfoDao.getCount(pcrpQueryInfo) });// 初始化分页类
			pcrpQueryInfo.setStartNumAndEndNum(ipg);
			ipg.setResult(pcrpQueryInfoDao.findByPage(pcrpQueryInfo));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return ipg;

	}
	

	public List ExportDate( PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		List PcrpQueryInfoList=pcrpQueryInfoDao.ExportDate(pcrpQueryInfo);
		return PcrpQueryInfoList;
	}

	
	/**
	 * 个人单笔分页查询登记信息
	 * 创建人：lidong dhcc
	 */
	
	public Ipage singleSearch(Ipage ipg,PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) pcrpQueryInfoDao.singleGetCount(pcrpQueryInfo) });// 初始化分页类
			pcrpQueryInfo.setStartNumAndEndNum(ipg);
			ipg.setResult(pcrpQueryInfoDao.singleFindByPage(pcrpQueryInfo));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	//根据条件查询人行征信笔数
	public int getBankCount(PcrpQueryInfo pcrpQueryInfo) throws ServiceException{
		int count = 0;
		try {
			count = (Integer) pcrpQueryInfoDao.getBankCount(pcrpQueryInfo);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	//根据条件查询征信笔数
	public int getBatchCount(PcrpQueryInfo pcrpQueryInfo) throws ServiceException{
		int count = 0;
		try {
			count = (Integer) pcrpQueryInfoDao.getBatchCount(pcrpQueryInfo);
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return count;
	}
	
	public void insert(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		
		try {
			pcrpQueryInfoDao.insert(pcrpQueryInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	public PcrpQueryInfo getByCrpId(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		try {
		pcrpQueryInfo=(PcrpQueryInfo) pcrpQueryInfoDao.getByCrpId(pcrpQueryInfo);
		return pcrpQueryInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	//根据客户信息查询本地信用报告
//	public List<PcrpQueryInfo> getByCrp(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
//		try {
//			List<PcrpQueryInfo> list =(List<PcrpQueryInfo>) pcrpQueryInfoDao.getByCrp(pcrpQueryInfo);
//		return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException(e.getMessage());
//		}
//	}
	/**
	 * 单笔查询征信结果调用 孙明义
	 */
	public String getByCrp(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		try {
			//将对象转json
//			String selBatchJson = JsonUtil.getJsonString4JavaPOJO(pcrpQueryInfo);
			String selBatchJson = JSON.toJSONString(pcrpQueryInfo);
			//调用服务端接口
			String selBatch = null;
//			String selBatch = creditSelResultDao.analyticForOneOnLocal(selBatchJson);
		return selBatch;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 批量查询征信结果调用 孙明义
	 */
	public String analyticForResult(WsIn4103 wsIn4103) throws ServiceException {
		try {
			//将对象转json
//			String selBatchCrpJson = JsonUtil.getJsonString4JavaPOJO(wsQueryCreditBean);
//			ipg.initPageCounts(new Integer[] { (Integer) creditSelResultDao.getCount(wsQueryCreditBean) });// 初始化分页类
//			wsQueryCreditBean.setStartNumAndEndNum (ipg);
			
			String selBatchCrpJson = JSON.toJSONString(wsIn4103);
			//调用服务端接口
			String selBatchCrp = null;
//			String selBatchCrp = creditSelResultDao.analyticForResult(selBatchCrpJson);
			
		return selBatchCrp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	//根据客户信息返回URL
	public String getByUrl(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		String url="";
		try {
			url= pcrpQueryInfoDao.getByUrl(pcrpQueryInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return url;
	}
	
	
	public PcrpQueryInfoDao getPcrpQueryInfoDao() {
		return pcrpQueryInfoDao;
	}


	public void setPcrpQueryInfoDao(PcrpQueryInfoDao pcrpQueryInfoDao) {
		this.pcrpQueryInfoDao = pcrpQueryInfoDao;
	}

	//根据客户证件号码，查询征信信息表中有无数据
	public PcrpQueryInfo existValid(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		// TODO Auto-generated method stub
//		PcrpQueryInfo pcrpQueryInfo = new PcrpQueryInfo();
		try {
			pcrpQueryInfo = pcrpQueryInfoDao.existValid(pcrpQueryInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pcrpQueryInfo;
	}
	
	////根据客户证件号码，更新状态
	public void update(PcrpQueryInfo pcrpQueryInfo) throws ServiceException {
		try {
			pcrpQueryInfoDao.update(pcrpQueryInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
