package app.creditapp.cred.bo;

import java.util.List;

import javax.jws.WebParam;

import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.cred.entity.PcrpQueryInfo;
import app.creditapp.inf.entity.WsIn4103;
import app.util.toolkit.Ipage;
/**
 * 
  * 接口名称：PcrpQueryInfoBo
  * 接口描述：个人征信相关方法 
  *创建人：lidong dhcc
  * 创建时间：2015-7-15 上午01:08:11
  * 修改人：Administrator  
 * @version
 */
public interface PcrpQueryInfoBo {
	
	public Ipage findByPage(Ipage ipg,PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
	
	public Ipage singleSearch(Ipage ipg,PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
	
	public void insert(PcrpQueryInfo pcrpQueryInfo) throws ServiceException;

	public PcrpQueryInfo getByCrpId(PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
	
	//根据客户信息查询本地信用报告
	//public List<PcrpQueryInfo> getByCrp(PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
	
	//根据客户信息返回URL
	public String getByUrl(PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
	
	public List ExportDate( PcrpQueryInfo pcrpQueryInfo)throws ServiceException;
	
	//根据客户证件号码，查询征信信息表中有无数据
	public PcrpQueryInfo existValid(PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
	
	//根据客户证件号码，更新状态
	public void update(PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
	
	/**
	 * 单笔查询征信结果调用 孙明义
	 */
	public String getByCrp(@WebParam(name="pcrpQueryInfoJson") PcrpQueryInfo pcrpQueryInfo);
	
	/**
	 * 批量查询征信结果调用 孙明义
	 */
	public String analyticForResult(@WebParam(name="pcrpQueryInfoJson") WsIn4103 wsIn4103);

	//根据查询条件进行人行查征笔数查询
	public int getBankCount(PcrpQueryInfo pcrpQueryInfo) throws DAOException;
	
	//根据条件查询征信笔数
	public int getBatchCount(PcrpQueryInfo pcrpQueryInfo) throws ServiceException;
}
