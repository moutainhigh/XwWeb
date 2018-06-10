package  app.creditapp.sys.bo;

import app.base.DAOException;
import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.AccessCheckInfo;

/**
* Title: AccessCheckInfoBo.java
* Description:
**/
public interface AccessCheckInfoBo {

	public AccessCheckInfo getById(AccessCheckInfo accessCheckInfo) throws ServiceException;

	public Ipage findByPage(Ipage ipg, AccessCheckInfo accessCheckInfo) throws ServiceException;

	//1、当前选择的合作机构，是否关联了TA的客户信息  返回值 如果是 > 0 就“是”如果是0 则是“否”
	public int countForMsg(AccessCheckInfo accessCheckInfo) throws ServiceException;
	
	//2、合作机构维度的配置参数是否配置完成  返回值如果是0 则是 “否” 如果等于1 则是 “是”
	public int countForConf(AccessCheckInfo accessCheckInfo) throws ServiceException;
	
	//3、合作机构是否已经关联信托项目  返回值如果是0 则是 “否” 如果大于0 则是 “是”
	public int countForRele(AccessCheckInfo accessCheckInfo) throws ServiceException;
	
	//4、信托项目维度是否参数已配置完成
	//返回如果为0 则没有配置完成，如果返回有数据，再执行第二个SQL 
	public int countForParam(AccessCheckInfo accessCheckInfo) throws DAOException;
	// 如果返回值为0，则配置完成，如果返回值大于0，则没有配置完成
	public int countForParamConf(AccessCheckInfo accessCheckInfo) throws DAOException;
	
	//5、合作机构关联的信托项目中运营人员是否已经全部指派  返回值如果是0 则已经全部指派，返回值如果大于0 则有未指派的项目
	public int countForDesig(AccessCheckInfo accessCheckInfo) throws DAOException;
}