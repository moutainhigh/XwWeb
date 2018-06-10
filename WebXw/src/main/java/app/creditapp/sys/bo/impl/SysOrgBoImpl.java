package  app.creditapp.sys.bo.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysOrgBo;
import app.creditapp.sys.dao.SysOrgDao;
import app.creditapp.sys.entity.SysOrg;
/**
* Title: SysOrgBoImplImpl.java
* Description:
**/
public class SysOrgBoImpl extends BaseService implements SysOrgBo {

	private SysOrgDao sysOrgDao;

	public void del(SysOrg sysOrg) throws ServiceException {
		try {
			sysOrgDao.del(sysOrg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysOrg sysOrg)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysOrgDao
					.getCount(sysOrg) });// 初始化分页类
			sysOrg.setStartNumAndEndNum (ipg);
			ipg.setResult(sysOrgDao.findByPage(sysOrg));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysOrg getById(SysOrg sysOrg) throws ServiceException {
		try {
			sysOrg = sysOrgDao.getById(sysOrg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysOrg;
	}

	public void insert(SysOrg sysOrg) throws ServiceException {
		try {
			sysOrgDao.insert(sysOrg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysOrg sysOrg) throws ServiceException {
		try {
			sysOrgDao.update(sysOrg);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public String getBrnoChildren(String orgNo, String deepLev) throws ServiceException {
		List<SysOrg> list = new ArrayList<SysOrg>();
		list = sysOrgDao.getBrnoChildren(orgNo);
		StringBuilder topOneSbf = new StringBuilder("");
		int deepLevInt = Integer.valueOf(deepLev);
		for(int i=0;i<list.size();i++) {
			SysOrg root = list.get(i);
//			if(Integer.valueOf(root.getBr_lev()) == deepLevInt){
				topOneSbf = addBrnoChildrenString(root,topOneSbf);
//			}else{
//				topOneSbf = addNodeDepartString(root,topOneSbf,deepLev);
//			}
		}
		return topOneSbf.toString();
	}
	private StringBuilder addBrnoChildrenString(SysOrg childElt,StringBuilder childrenSbf){
		childrenSbf.append("<li id='")
		.append(childElt.getOrgNo())
		.append("'><input type='checkbox' id='orgno'" +" value='"+childElt.getOrgNo()+"')\">")
		.append(childElt.getOrgNo()).append(" - ").append(childElt.getOrgName())
		.append("</li>\n");
		return childrenSbf;
	}
	public SysOrgDao getSysOrgDao() {
		return sysOrgDao;
	}

	public void setSysOrgDao(SysOrgDao sysOrgDao) {
		this.sysOrgDao = sysOrgDao;
	}
}