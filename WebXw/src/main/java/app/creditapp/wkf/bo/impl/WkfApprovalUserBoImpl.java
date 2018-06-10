package  app.creditapp.wkf.bo.impl;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
//import app.creditapp.sys.entity.TblOrgUser;
import app.creditapp.wkf.bo.WkfApprovalUserBo;
import app.creditapp.wkf.dao.WkfApprovalUserDao;
import app.creditapp.wkf.entity.WkfApprovalUser;
import app.util.toolkit.Ipage;
/**
* Title: WkfApprovalUserBoImplImpl.java
* Description:
**/
public class WkfApprovalUserBoImpl extends BaseService implements WkfApprovalUserBo {

	private WkfApprovalUserDao wkfApprovalUserDao;

	public void del(WkfApprovalUser wkfApprovalUser) throws ServiceException {
		try {
			wkfApprovalUserDao.del(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public void delForRole(WkfApprovalUser wkfApprovalUser) throws ServiceException {
		try {
			wkfApprovalUserDao.delForRole(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}	
	public void delByWkfRoleNo(WkfApprovalUser wkfApprovalUser)throws ServiceException {
		try {
			wkfApprovalUserDao.delByWkfRoleNo(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, WkfApprovalUser wkfApprovalUser)throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) wkfApprovalUserDao.getCount(wkfApprovalUser) });// 
			wkfApprovalUser.setStartNumAndEndNum (ipg);
			ipg.setResult(wkfApprovalUserDao.findByPage(wkfApprovalUser));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	
	public Ipage findApprovalUserByPage(Ipage ipage,WkfApprovalUser wkfApprovalUser) throws ServiceException
	{
		try
		{
			if(null!=wkfApprovalUser.getWkfBrNo())
				wkfApprovalUser.setWkfBrNo(formatStr(wkfApprovalUser.getWkfBrNo()));
			if(null!=wkfApprovalUser.getWkfRoleNo())
				wkfApprovalUser.setWkfRoleNo(formatStr(wkfApprovalUser.getWkfRoleNo()));
			if(null!=wkfApprovalUser.getWkfUserName())
				wkfApprovalUser.setWkfUserName(formatStr(wkfApprovalUser.getWkfUserName()));
			ipage.initPageCounts(new Integer[] { (Integer) wkfApprovalUserDao.getApprovalUserCount(wkfApprovalUser) });// 
			wkfApprovalUser.setStartNumAndEndNum (ipage);
			ipage.setResult(wkfApprovalUserDao.findApprovalUserByPage(wkfApprovalUser));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
	
	/**
	 * 
	 * 功能描述：查询当前节点的会签成员
	 * @param ipage
	 * @param wkfApprovalUser
	 * @return
	 * @throws ServiceException
	 * @修改日志：
	 */
	public Ipage findApproveUserByPage(Ipage ipage,WkfApprovalUser wkfApprovalUser) throws ServiceException
	{
		try
		{
			if(null!=wkfApprovalUser.getWkfBrNo())
				wkfApprovalUser.setWkfBrNo(formatStr(wkfApprovalUser.getWkfBrNo()));
			if(null!=wkfApprovalUser.getWkfRoleNo())
				wkfApprovalUser.setWkfRoleNo(formatStr(wkfApprovalUser.getWkfRoleNo()));
			if(null!=wkfApprovalUser.getWkfUserName())
				wkfApprovalUser.setWkfUserName(formatStr(wkfApprovalUser.getWkfUserName()));
			ipage.initPageCounts(new Integer[] { (Integer) wkfApprovalUserDao.getApproveUserCount(wkfApprovalUser) });// 
			wkfApprovalUser.setStartNumAndEndNum (ipage);
			ipage.setResult(wkfApprovalUserDao.findApproveUserByPage(wkfApprovalUser));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}

	private static String formatStr(String str)
	{
		String[] strArray=null;;
		str=str.replaceAll(" ","");
		if(!"".equals(str))
		{
			strArray=str.split(",");
			str="";
			for(int i=0;i<strArray.length;i++)
			{
				str+="'"+strArray[i]+"',";
			}
//			str=str.substring(1,str.length()-2);
			str=str.substring(0,str.length()-1);
			
		}
		System.out.println(str);
		return str;
	}
	public List<WkfApprovalUser> getAllList(WkfApprovalUser wkfApprovalUser)throws ServiceException 
	{
		List<WkfApprovalUser> list;
		try {
			list = wkfApprovalUserDao.getAllList(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	public String getAllRoles(String wkfUserName)throws ServiceException 
	{
		List<WkfApprovalUser> list;
		String roles = "";
		try {
			list = wkfApprovalUserDao.getAllList(wkfUserName);
			for(WkfApprovalUser war:list){
				roles+=war.getWkfRoleNo()+"@";
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return roles;
	}

	public Ipage findByPageMapPop(Ipage ipage, WkfApprovalUser wkfApprovalUser)throws ServiceException {
		try {
			ipage.initPageCounts(new Integer[] { (Integer) wkfApprovalUserDao.getCount(wkfApprovalUser) });
			wkfApprovalUser.setStartNumAndEndNum (ipage);
			ipage.setResult(wkfApprovalUserDao.findByPageMapPop(wkfApprovalUser));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipage;
	}
	public List<String> getWkfApprovalUserList(WkfApprovalUser wkfApprovalUser) throws ServiceException 
	{
		String[] wkfRoleNoArray;
		String[] wkfBrNoArray;
		List<String> list = null;
		WkfApprovalUser tempWkfApprovalUser=new WkfApprovalUser();
		if(null==wkfApprovalUser.getWkfRoleNo()||"".equals(wkfApprovalUser.getWkfRoleNo()))
		{
			if(null==wkfApprovalUser.getWkfBrNo()||"".equals(wkfApprovalUser.getWkfBrNo()))
				list=wkfApprovalUserDao.getWkfApprovalUserList(wkfApprovalUser);
			else
			{
				wkfBrNoArray=wkfApprovalUser.getWkfBrNo().split(",");
				for(int i=0;i<wkfBrNoArray.length;i++)
				{
					tempWkfApprovalUser.setWkfBrNo(wkfBrNoArray[i]);
					tempWkfApprovalUser.setWkfRoleNo("");
					if(null!=list)
						list.addAll(wkfApprovalUserDao.getWkfApprovalUserList(tempWkfApprovalUser));
					else
						list=wkfApprovalUserDao.getWkfApprovalUserList(tempWkfApprovalUser);
				}
			}
		}
		else if(null==wkfApprovalUser.getWkfBrNo()||"".equals(wkfApprovalUser.getWkfBrNo()))
		{
			if(null==wkfApprovalUser.getWkfRoleNo()||"".equals(wkfApprovalUser.getWkfRoleNo()))
				list=wkfApprovalUserDao.getWkfApprovalUserList(wkfApprovalUser);
			else
			{
				wkfRoleNoArray=wkfApprovalUser.getWkfRoleNo().split(",");
				for(int i=0;i<wkfRoleNoArray.length;i++)
				{
					tempWkfApprovalUser.setWkfRoleNo(wkfRoleNoArray[i]);
					tempWkfApprovalUser.setWkfBrNo("");
					if(null!=list)
						list.addAll(wkfApprovalUserDao.getWkfApprovalUserList(tempWkfApprovalUser));
					else
						list=wkfApprovalUserDao.getWkfApprovalUserList(tempWkfApprovalUser);
				}
			}
		}
		else
		{
			wkfBrNoArray=wkfApprovalUser.getWkfBrNo().split(",");
			wkfRoleNoArray=wkfApprovalUser.getWkfRoleNo().split(",");
			for(int i=0;i<wkfBrNoArray.length;i++)
			{
				tempWkfApprovalUser.setWkfBrNo(wkfBrNoArray[i]);
				for(int j=0;j<wkfRoleNoArray.length;j++)
				{
					tempWkfApprovalUser.setWkfRoleNo(wkfRoleNoArray[j]);
					if(null!=list)
						list.addAll(wkfApprovalUserDao.getWkfApprovalUserList(tempWkfApprovalUser));
					else
						list=wkfApprovalUserDao.getWkfApprovalUserList(tempWkfApprovalUser);
				}
			}
		}
		return list;
	}
	public WkfApprovalUser getById(WkfApprovalUser wkfApprovalUser) throws ServiceException {
		try {
			wkfApprovalUser = wkfApprovalUserDao.getById(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfApprovalUser;
	}
	public WkfApprovalUser getById2(WkfApprovalUser wkfApprovalUser) throws ServiceException {
		try {
			wkfApprovalUser = wkfApprovalUserDao.getById2(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfApprovalUser;
	}
	
	public WkfApprovalUser getByUser(WkfApprovalUser wkfApprovalUser)throws ServiceException {
		try {
			wkfApprovalUser = wkfApprovalUserDao.getByUser(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfApprovalUser;
	}
	public int getUserCount(String wkfUserName)throws ServiceException{
		int count = 0;
		try {
			count = wkfApprovalUserDao.getUserCount(wkfUserName);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return count;
		
	}

	public void insert(WkfApprovalUser wkfApprovalUser) throws ServiceException {
		try {
//			String seq=wkfApprovalUserDao.getSeq();
//			wkfApprovalUser.setSeq(seq);
			wkfApprovalUserDao.insert(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void batchInsert(String wkfRoleNoStr,String roleNoStr,String brNoStr)throws ServiceException 
	{
//		WkfApprovalUser wkfApprovalUser=new WkfApprovalUser();
//		String seq = null;
//		String []wkfRoleNoArray=wkfRoleNoStr.replaceAll("\\s*", "").split(",");
//		String []roleNoArray=roleNoStr.replaceAll("\\s*", "").split(",");
//		String []brNoArray=brNoStr.replaceAll("\\s*", "").split(",");
//		TblOrgUser tblOrgUser;
//		try {
//			for(int i=0;i<wkfRoleNoArray.length;i++)
//			{
//				for(int j=0;j<roleNoArray.length;j++)
//				{
//					for(int k=0;k<brNoArray.length;k++)
//					{
//						tblOrgUser=new TblOrgUser();
//						tblOrgUser.setBrNo(brNoArray[k]);
//						tblOrgUser.setRoleNo(roleNoArray[j]);
////						List<TblOrgUser> userNameList = sysInterface.getTblOrgUserListByBrNoAndRoleNo(tblOrgUser);
////						if(null!=userNameList&&userNameList.size()>0)
////						{
////							for(int m=0;m<userNameList.size();m++)
////							{
////								wkfApprovalUser.setWkfRoleNo(wkfRoleNoArray[i]);
////								wkfApprovalUser.setWkfUserName(userNameList.get(m).getUsername());
////								WkfApprovalUser tempWkfApprovalUser=wkfApprovalUserDao.getByUser(wkfApprovalUser);
////								if(null==tempWkfApprovalUser)
////								{
////									seq=wkfApprovalUserDao.getSeq();
////									wkfApprovalUser.setSeq(seq);
////									wkfApprovalUser.setWkfBrNo(userNameList.get(m).getBrNo());
////									wkfApprovalUserDao.insert(wkfApprovalUser);
////								}
////							}
////						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException(e.getMessage());
//		}
	}

	public void update(WkfApprovalUser wkfApprovalUser) throws ServiceException {
		try {
			wkfApprovalUserDao.update(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public WkfApprovalUserDao getWkfApprovalUserDao() {
		return wkfApprovalUserDao;
	}

	public void setWkfApprovalUserDao(WkfApprovalUserDao wkfApprovalUserDao) {
		this.wkfApprovalUserDao = wkfApprovalUserDao;
	}


	public List<WkfApprovalUser> getByIdAndBrNo(WkfApprovalUser wkfApprovalUser)
			throws ServiceException {
		List<WkfApprovalUser> list = this.wkfApprovalUserDao.getByIdAndBrNo(wkfApprovalUser);
		
		return list;
	}

	@Override
	public WkfApprovalUser getByRoleName(WkfApprovalUser wkfApprovalUser)
			throws ServiceException {
		try {
			wkfApprovalUser = wkfApprovalUserDao.getByRoleName(wkfApprovalUser);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return wkfApprovalUser;
	}

}