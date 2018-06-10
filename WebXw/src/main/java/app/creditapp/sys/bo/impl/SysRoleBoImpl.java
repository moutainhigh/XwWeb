package app.creditapp.sys.bo.impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.entity.SysRoleButton;
import app.creditapp.sys.bo.SysMenuBo;
import app.creditapp.sys.bo.SysRoleBo;
import app.creditapp.sys.dao.SysRoleDao;
import app.creditapp.sys.dao.SysUserDao;
import app.creditapp.sys.entity.SysButton;
import app.creditapp.sys.entity.SysRole;
import app.creditapp.sys.entity.SysRoleMenu;
import app.creditapp.sys.entity.SysUser;
import app.util.User;
import app.util.toolkit.Ipage;

/**
 * Title: SysRoleBoImplImpl.java Description:
 **/
public class SysRoleBoImpl extends BaseService implements SysRoleBo {

    private SysRoleDao sysRoleDao;
    private SysMenuBo sysMenuBo;
    private SysUserDao sysUserDao;

    public void del(SysRole sysRole) throws ServiceException {
	try {
	    sysRoleDao.del(sysRole);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
    }

    public Ipage findByPage(Ipage ipg, SysRole sysRole) throws ServiceException {
	try {
		SysUser sysUser = new SysUser();
		sysUser.setUser_no(User.getLoginid(ServletActionContext.getRequest()));
		sysUser = sysUserDao.getById(sysUser);
	    ipg.initPageCounts(new Integer[] { (Integer) sysRoleDao
		    .getCount(sysRole) });// 初始化分页类
	    sysRole.setStartNumAndEndNum(ipg);
	    ipg.setResult(sysRoleDao.findByPage(sysRole));
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return ipg;
    }
	public Ipage findByPageForPop(Ipage ipg, SysRole sysRole) throws ServiceException{
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysRoleDao
					.getfindByPageForPopCount(sysRole) });// 初始化分页类
			sysRole.setStartNumAndEndNum (ipg);
			ipg.setResult(sysRoleDao.findByPageForPop(sysRole));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
    public SysRole getById(SysRole sysRole) throws ServiceException {
	try {
	    sysRole = sysRoleDao.getById(sysRole);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return sysRole;
    }
    public int getCountByRoleNo(String role_no) throws ServiceException {
    	int count= 0;
    	try {
    	    count = sysRoleDao.getCountByRoleNo(role_no);
    	} catch (Exception e) {
    	    throw new ServiceException(e.getMessage());
    		}
    	return count;
       	}
    public void insert(SysRole sysRole) throws ServiceException {
	try {
	    sysRoleDao.insert(sysRole);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
    }

    public void update(SysRole sysRole) throws ServiceException {
	try {
	    sysRoleDao.update(sysRole);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
    }

    public String getAllJsonMenu() throws ServiceException {
	String menuStr = null;
	try {
	    menuStr = sysMenuBo.getAllJsonMenu1();
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return menuStr;
    }

    public String checkJsp(String role_no) throws ServiceException {
	StringBuffer roleno_strbuf = new StringBuffer("");
	List<SysRoleMenu> rolelist;
	try {
	    rolelist = sysRoleDao.checkJsp(role_no);
	    if (rolelist != null && rolelist.size() > 0) {
		for (SysRoleMenu srm : rolelist) {
		    roleno_strbuf.append("'");
		    roleno_strbuf.append(srm.getMenu_no());
		    roleno_strbuf.append("',");
		}
		roleno_strbuf.delete(roleno_strbuf.length() - 1,
			roleno_strbuf.length());
	    }
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return roleno_strbuf.toString();
    }

    public String getAllMenuByRoleNo(String role_no) throws ServiceException {
	String menuStr = null;
	try {
	    menuStr = sysMenuBo.getAllMenuByRoleNo(role_no);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return menuStr;
    }

    public List<SysRoleButton> getButtonList(String role_no, String menu_no)
	    throws ServiceException {
	List<SysRoleButton> list = null;
	try {
	    SysRoleButton sysRoleButton = new SysRoleButton();
	    sysRoleButton.setRole_no(role_no);
	    sysRoleButton.setMenu_no(menu_no);
	    list = sysRoleDao.getButtonList(sysRoleButton);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return list;
    }

    public List<SysButton> getMenuButton(String menu_no)
	    throws ServiceException {
	List<SysButton> list = null;
	try {
	    list = sysRoleDao.getMenuButton(menu_no);
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return list;
    }

   
    public String saveButton(String role_no, String menu_no, String butlist)
	    throws ServiceException {
	String data = null;
	try {
	    SysRoleButton sysRoleButton = new SysRoleButton();
	    String[] butlistArr = butlist.split("@");
	    sysRoleButton.setRole_no(role_no);
	    sysRoleButton.setMenu_no(menu_no);
	    sysRoleDao.deleteButton(sysRoleButton);
	    for (int i = 0; i < butlistArr.length; i++) {
		sysRoleButton.setButton_no(butlistArr[i]);
		sysRoleDao.saveButton(sysRoleButton);
	    }
	    data = "1";
	} catch (Exception e) {
	    throw new ServiceException(e.getMessage());
	}
	return data;
    }

    public SysRoleDao getSysRoleDao() {
	return sysRoleDao;
    }

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
	this.sysRoleDao = sysRoleDao;
    }

    public SysMenuBo getSysMenuBo() {
	return sysMenuBo;
    }

    public void setSysMenuBo(SysMenuBo sysMenuBo) {
	this.sysMenuBo = sysMenuBo;
    }

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

}