package  app.creditapp.wkf.bo.impl;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
//import app.creditapp.sys.bo.TblOrgUserBo;
//import app.creditapp.sys.entity.TblOrgUser;
import app.creditapp.wkf.bo.WkfApprovalRoleBo;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.dao.WkfApprovalRoleDao;
import app.creditapp.wkf.entity.WkfApprovalRole;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.toolkit.Ipage;
/**
* Title: WkfApprovalRoleBoImplImpl.java
* Description:
**/
public class WkfApprovalRoleBoImpl extends BaseService implements WkfApprovalRoleBo {

	private WkfApprovalRoleDao wkfApprovalRoleDao;
	private WkfApprovalUserBo wkfApprovalUserBo;
//	private TblOrgUserBo tblOrgUserBO;
	
	public void del(WkfApprovalRole wkfApprovalRole) throws ServiceException {
		try {
			wkfApprovalRoleDao.del(wkfApprovalRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void delGroup(WkfApprovalRole wkfApprovalRole)	throws ServiceException {
		try {
			wkfApprovalRoleDao.del(wkfApprovalRole);
			WkfApprovalUser wkfApprovalUser=new WkfApprovalUser();
			wkfApprovalUser.setWkfRoleNo(wkfApprovalRole.getWkfRoleNo());
			wkfApprovalUserBo.delByWkfRoleNo(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WkfApprovalRole wkfApprovalRole)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wkfApprovalRoleDao.getCount(wkfApprovalRole) });// 初始化分页类
			wkfApprovalRole.setStartNumAndEndNum (ipg);
			ipg.setResult(wkfApprovalRoleDao.findByPage(wkfApprovalRole));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public List<WkfApprovalRole> getAllList(WkfApprovalRole wkfApprovalRole) throws ServiceException {
		List<WkfApprovalRole> list;
		try {
			list=wkfApprovalRoleDao.getAllList(wkfApprovalRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	public WkfApprovalRole getById(WkfApprovalRole wkfApprovalRole) throws ServiceException {
		try {
			wkfApprovalRole = wkfApprovalRoleDao.getById(wkfApprovalRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfApprovalRole;
	}

	public void insert(WkfApprovalRole wkfApprovalRole) throws ServiceException {
		try {
			wkfApprovalRoleDao.insert(wkfApprovalRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(WkfApprovalRole wkfApprovalRole) throws ServiceException {
		try {
			wkfApprovalRoleDao.update(wkfApprovalRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void insertOrUpdate(WkfApprovalRole wkfApprovalRole,String members,String saveFlag) throws ServiceException 
	{
//		try 
//		{
//			if("insert".equals(saveFlag))
//				wkfApprovalRoleDao.insert(wkfApprovalRole);
//			else
//				wkfApprovalRoleDao.update(wkfApprovalRole);
//			if(null!=members)
//			{
//				TblOrgUser tblOrgUser=null;
//				members=members.replaceAll(" ","");
//				System.out.println(members);
//				if(members.length()>0)
//				{
//					String [] memberArray=members.split(",");
//					WkfApprovalUser wkfApprovalUser=new WkfApprovalUser();
//					WkfApprovalUser wkfApprovalUserTemp;
//					for(int i=0;i<memberArray.length;i++)
//					{
//						String [] tempArray=memberArray[i].split(":");
//						String value=tempArray[0];
//						String type=tempArray[1];
//						if("0".equals(type))//机构
//						{
//							tblOrgUser=new TblOrgUser();
//							tblOrgUser.setBrNo(value);
//							List<TblOrgUser> tblOrgUserList= null;
//								//tblOrgUserBo.getAllList(tblOrgUser);
//							for(int j=0;j<tblOrgUserList.size();j++)
//							{
//								wkfApprovalUser.setWkfRoleNo(wkfApprovalRole.getWkfRoleNo());
//								wkfApprovalUser.setWkfUserName(tblOrgUserList.get(j).getUsername());
//								wkfApprovalUser.setWkfBrNo(value);
//								wkfApprovalUser.setSeq(null);
//								wkfApprovalUserTemp=wkfApprovalUserBo.getByUser(wkfApprovalUser);
//								if(null==wkfApprovalUserTemp)
//									wkfApprovalUserBo.insert(wkfApprovalUser);
//								else
//								{
//									wkfApprovalUser.setSeq(wkfApprovalUserTemp.getSeq());
//									wkfApprovalUserBo.update(wkfApprovalUser);
//								}
//							}
//							tblOrgUser=null;
//						}
//						else if("1".equals(type))//用户
//						{
//							tblOrgUser=tblOrgUserBO.getByUsername(value);
//							wkfApprovalUser.setWkfRoleNo(wkfApprovalRole.getWkfRoleNo());
//							wkfApprovalUser.setWkfUserName(value);
//							wkfApprovalUser.setWkfBrNo(tblOrgUser.getBrNo());
//							wkfApprovalUser.setSeq(null);
//							wkfApprovalUserTemp=wkfApprovalUserBo.getByUser(wkfApprovalUser);
//							if(null==wkfApprovalUserTemp)
//								wkfApprovalUserBo.insert(wkfApprovalUser);
//							else
//							{
//								wkfApprovalUser.setSeq(wkfApprovalUserTemp.getSeq());
//								wkfApprovalUserBo.update(wkfApprovalUser);
//							}
//						}
//						else if("2".equals(type))//登录角色
//						{
//							tblOrgUser=new TblOrgUser();
//							tblOrgUser.setRoleNo(value);
//							List<TblOrgUser> tblOrgUserList=null;
//								//tblOrgUserBo.getAllList(tblOrgUser);
//							for(int j=0;j<tblOrgUserList.size();j++)
//							{
//								wkfApprovalUser.setWkfRoleNo(wkfApprovalRole.getWkfRoleNo());
//								wkfApprovalUser.setWkfUserName(tblOrgUserList.get(j).getUsername());
//								wkfApprovalUser.setWkfBrNo(tblOrgUserList.get(j).getBrNo());
//								wkfApprovalUser.setSeq(null);
//								wkfApprovalUserTemp=wkfApprovalUserBo.getByUser(wkfApprovalUser);
//								if(null==wkfApprovalUserTemp)
//									wkfApprovalUserBo.insert(wkfApprovalUser);
//								else
//								{
//									wkfApprovalUser.setSeq(wkfApprovalUserTemp.getSeq());
//									wkfApprovalUserBo.update(wkfApprovalUser);
//								}
//							}
//							tblOrgUser=null;
//						}
//						else if("3".equals(type))//审批角色
//						{
//							WkfApprovalUser wkfApprovalUserQuery=new WkfApprovalUser();
//							wkfApprovalUserQuery.setWkfRoleNo(value);
//							List<WkfApprovalUser> wkfApprovalUserList= wkfApprovalUserBo.getAllList(wkfApprovalUserQuery);
//							for(int j=0;j<wkfApprovalUserList.size();j++)
//							{
//								wkfApprovalUser.setWkfRoleNo(wkfApprovalRole.getWkfRoleNo());
//								wkfApprovalUser.setWkfUserName(wkfApprovalUserList.get(j).getWkfUserName());
//								wkfApprovalUser.setWkfBrNo(null);
//								wkfApprovalUser.setSeq(null);
//								wkfApprovalUserTemp=wkfApprovalUserBo.getByUser(wkfApprovalUser);
//								wkfApprovalUser.setWkfBrNo(wkfApprovalUserList.get(j).getWkfBrNo());
//								if(null==wkfApprovalUserTemp)
//									wkfApprovalUserBo.insert(wkfApprovalUser);
//								else
//								{
//									wkfApprovalUser.setSeq(wkfApprovalUserTemp.getSeq());
//									wkfApprovalUserBo.update(wkfApprovalUser);
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			throw new ServiceException(e.getMessage());
//		}
	}

	public WkfApprovalRoleDao getWkfApprovalRoleDao() {
		return wkfApprovalRoleDao;
	}

	public void setWkfApprovalRoleDao(WkfApprovalRoleDao wkfApprovalRoleDao) {
		this.wkfApprovalRoleDao = wkfApprovalRoleDao;
	}

	public WkfApprovalUserBo getWkfApprovalUserBo() {
		return wkfApprovalUserBo;
	}

	public void setWkfApprovalUserBo(WkfApprovalUserBo wkfApprovalUserBo) {
		this.wkfApprovalUserBo = wkfApprovalUserBo;
	}


	public Ipage findByPagePop(Ipage ipg, WkfApprovalRole wkfApprovalRole)
			throws ServiceException {
		try {
			/*if("2".equals(tblorgdepartmentsdao.getByBrno(wkfApprovalRole.getWkfbrno()).getBr_lev())){
				ipg.initPageCounts(new Integer[] { (Integer) wkfApprovalRoleDao.getCountTwoPop(wkfApprovalRole) });// 初始化分页类
				wkfApprovalRole.setStartNumAndEndNum (ipg);
				ipg.setResult(wkfApprovalRoleDao.findByPageTwoPop(wkfApprovalRole));
			}else{*/
				ipg.initPageCounts(new Integer[] { (Integer) wkfApprovalRoleDao.getCountPop(wkfApprovalRole) });// 初始化分页类
				wkfApprovalRole.setStartNumAndEndNum (ipg);
				ipg.setResult(wkfApprovalRoleDao.findByPagePop(wkfApprovalRole));
			//}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	public List<WkfApprovalRole>  findWkfApprovalRole(WkfApprovalRole wkfApprovalRole){
		List<WkfApprovalRole>  war;
		try {
			
			war = wkfApprovalRoleDao.findWkfApprovalRole(wkfApprovalRole);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return war;
	}

//	public TblOrgUserBo getTblOrgUserBO() {
//		return tblOrgUserBO;
//	}
//
//	public void setTblOrgUserBO(TblOrgUserBo tblOrgUserBO) {
//		this.tblOrgUserBO = tblOrgUserBO;
//	}

}