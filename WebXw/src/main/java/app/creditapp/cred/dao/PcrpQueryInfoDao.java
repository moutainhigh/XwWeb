package app.creditapp.cred.dao;


import java.util.List;

import javax.jws.WebParam;

import app.base.DAOException;
import app.creditapp.cred.entity.PcrpQueryInfo;

/**
 * 
  * 接口类名称：PcrpQueryInfoDao
  * 类描述：个人业务深层实现方法 
  *创建人：lidong dhcc 
  * 创建时间：2015-7-15 上午01:21:47
  * 修改人：Administrator  
 * @version
 */
public interface PcrpQueryInfoDao {

	//分页查询
	public List<PcrpQueryInfo> findByPage(PcrpQueryInfo pcrpDocInfo)throws DAOException;
	//信用报告查询
	public List<PcrpQueryInfo> singleFindByPage(PcrpQueryInfo pcrpDocInfo)throws DAOException;
	//分页总条数查询
	public int getCount(PcrpQueryInfo pcrpDocInfo) throws DAOException;
	//信用报告总条数查询
	public int singleGetCount(PcrpQueryInfo pcrpDocInfo) throws DAOException;
	//信用报告新增
	public void insert(PcrpQueryInfo pcrpDocInfo) throws DAOException;
    //根据信用报告编号查询信用报告
	public PcrpQueryInfo getByCrpId(PcrpQueryInfo pcrpQueryInfo);
	
	//根据客户信息查询本地信用报告
	public List<PcrpQueryInfo> getByCrp(PcrpQueryInfo pcrpQueryInfo);
	
	//根据客户信息返回URL
	public String getByUrl(PcrpQueryInfo pcrpQueryInfo);
	
	public List ExportDate(PcrpQueryInfo pcrpQueryInfo) throws DAOException;
	
	//根据客户证件号码，查询征信信息表中有无数据
	public PcrpQueryInfo existValid(PcrpQueryInfo pcrpQueryInfo) throws DAOException;
	
	//根据客户证件号码，更新状态
	public void update(PcrpQueryInfo pcrpQueryInfo) throws DAOException;

	//根据查询条件查询人行查征笔数
	public int getBankCount(PcrpQueryInfo pcrpQueryInfo) throws DAOException;
	
	//根据查询条件查询查征笔数
	public int getBatchCount(PcrpQueryInfo pcrpQueryInfo) throws DAOException;
}
