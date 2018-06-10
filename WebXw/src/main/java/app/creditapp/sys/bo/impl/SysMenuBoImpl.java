package  app.creditapp.sys.bo.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.IbatisUtils;
import app.util.json.MenuJson;
import app.util.json.SysJsonTree;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.SysMenuBo;
import app.creditapp.sys.dao.SysMenuDao;
import app.creditapp.sys.entity.SysMenu;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysMenuBoImplImpl.java
* Description:
**/

public class SysMenuBoImpl extends BaseService implements SysMenuBo {

	// spring注入sys_MenuDAO
	private SysMenuDao sysMenuDao;

	public void setSysMenuDao(SysMenuDao sysMenuDao) {
		this.sysMenuDao = sysMenuDao;
	}

	/*
	 * @see app.creditapp.bo.SysMenuBO#findAll(java.lang.String)
	 */
	public List<SysMenu> findAll(String stats) throws ServiceException {
		return sysMenuDao.findAll(stats);
	}

	/**
	 * 
	 */
	public List<SysRoleMenu> getSysMenuByRole_no(String role_no)
			throws ServiceException {
		// TODO 获得角色对应的菜单 Leopard
		return sysMenuDao.getSysMenuByRoleNo(role_no);
	}

	/*
	 * @see app.creditapp.bo.SysMenuBO#findPage(app.util.toolkit.Ipage,
	 *      app.creditapp.entity.SysMenu)
	 */
	public Ipage findPage(Ipage ipg, SysMenu sm) throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { sysMenuDao.getCount(sm) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, sm);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(sysMenuDao.findPage(map));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	/*
	 * @see app.creditapp.bo.SysMenuBO#saveOrUpdate(app.creditapp.entity.SysMenu)
	 */
	public void saveOrUpdate(SysMenu sm) throws ServiceException {
		try {
			SysMenu orig = sysMenuDao.getById(sm.getMenuNo());
			if (orig == null) {
				sysMenuDao.insert(sm);
			} else {
				sysMenuDao.update(sm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void insert(SysMenu sm) throws ServiceException {
		try {
			sysMenuDao.insert(sm);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void update(SysMenu sm) throws ServiceException {
		try {
			sysMenuDao.update(sm);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(String menuNo) throws ServiceException {
		try {
			sysMenuDao.delete(menuNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * @see app.creditapp.bo.SysMenuBO#getAllJsonMenu()
	 */
	public String getAllJsonMenu() throws ServiceException {
		List<SysMenu> menus = null;
		try {
			menus = sysMenuDao.findAll("");
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		MenuJson json = new MenuJson(menus);
		// json.setHasboxs(false);
		String str = json.toJson();
		return str;
	}
	
	public String getAllJsonMenu1() throws ServiceException{
		List<SysMenu> menus = null;
		try {
			menus = sysMenuDao.findAll1();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		MenuJson json = new MenuJson(menus);
		// json.setHasboxs(false);
		String str = json.toJson();
		return str;
	}

    public String getAllJsonMenu2() throws ServiceException{
		List<SysMenu> menus = null;
		try {
			menus = sysMenuDao.findAll1();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		StringBuffer menuStr = new StringBuffer();
		menuStr.append("[");
		SysMenu sysMenu = new SysMenu();
		for (int i = 0; i < menus.size(); i++) {
			sysMenu = menus.get(i);
			if("0".equals(sysMenu.getMenuLvl())){
				menuStr.append(",{'checked':"+true+",'text':'"+sysMenu.getMenuName()+"','name':'"+sysMenu.getMenuName()+"','id':'"+sysMenu.getMenuNo()+"','url':'"+sysMenu.getMenuUrl()+"','sts':'"+sysMenu.getMenuSts() +"','lvl':'"+sysMenu.getMenuLvl()+"','pId':'"+sysMenu.getMenuParent()+"','open':"+true+"}");
			} else {
				menuStr.append(",{'checked':"+true+",'text':'"+sysMenu.getMenuName()+"','name':'"+sysMenu.getMenuName()+"','id':'"+sysMenu.getMenuNo()+"','url':'"+sysMenu.getMenuUrl()+"','sts':'"+sysMenu.getMenuSts() +"','lvl':'"+sysMenu.getMenuLvl()+"','pId':'"+sysMenu.getMenuParent()+"','open':"+false+"}");
			}
		}
		menuStr.append("]");
		menuStr.replace(1, 2, "");
		System.out.println("菜单列表：" + menuStr.toString());
		return menuStr.toString();
	}

	/**
	 * 查询一个操作员号下面对应的所有菜单的内容
	 */
	public String getAllMenuByRoleNo(String role_no) throws ServiceException {
		List<SysMenu> menus = null;
		try {
			menus = sysMenuDao.getAllMenuByRoleNo(role_no);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		MenuJson json = new MenuJson(menus);
		String str = json.toJson();
		return str;
	}

	/**
	 * 根据角色获得所有子非一级菜单
	 */
	public List<SysMenu> getAllMenuByRole(String role_no)
			throws ServiceException {
		List<SysMenu> menus = null;

		try {
			menus = sysMenuDao.getAllMenuByRole(role_no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return menus;
	}
	
	/**
	 * 根据角色获得所有子菜单
	 */
	public List<SysMenu> getAllMenuByRoles(String role_no)
	throws ServiceException {
		List<SysMenu> menus = null;
		
		try {
			menus = sysMenuDao.getAllMenuByRoles(role_no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return menus;
	}

	public String getAllBrno() throws ServiceException {
		List<SysMenu> menus = null;
		try {
			menus = sysMenuDao.findAll("");
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		MenuJson json = new MenuJson(menus);
		// json.setHasboxs(false);
		String str = json.toJson();
		return str;
	}

	public String getallsysmenu() throws ServiceException {
		// TODO Auto-generated method stub
		String str = "";
		// ArrayList<SysMenu> list=(ArrayList<SysMenu>)
		// this.sys_MenuDAO.getallsysmenu();
		ArrayList<SysMenu> list = (ArrayList<SysMenu>) ServletActionContext
				.getRequest().getSession().getAttribute("sysmenu");
		SysJsonTree sysJsonTree = new SysJsonTree("0", "信贷管理系统", list);
		str = sysJsonTree.getJson();

		return str;
	}

	public List<SysMenu> getmu() throws ServiceException {
		// TODO Auto-generated method stub
		ArrayList<SysMenu> list = null;
		try {
			list = (ArrayList<SysMenu>) this.sysMenuDao.getallsysmenu();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<SysRoleMenu> getSysMenuByUser_id(String roleNo)
			throws ServiceException {
		// TODO Auto-generated method stub
		List<SysRoleMenu> list = null;
		try {
			list = this.sysMenuDao.getSysMenuByUserId(roleNo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public List<SysMenu> getAllMenuByUserId(String roleNo)
			throws ServiceException {
		// TODO Auto-generated method stub
		List<SysMenu> menus = null;
		try {
			menus = sysMenuDao.getAllMenuByUserId(roleNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return menus;
	}

	/**
	 * 按照角色号查询菜单 放入session防止多次查询
	 */
	public String getallrolemenu(String roleNo) throws ServiceException {

		String str = "";
		Object roleMenustr = ServletActionContext.getRequest().getSession()
				.getAttribute("sysrolemenu");
		if (null != roleMenustr && !("".equals(roleMenustr.toString()))) {
			str = roleMenustr.toString();
		} else {
			List<SysMenu> list = sysMenuDao.getAllMenuByRole_no(roleNo);
			SysJsonTree sysJsonTree = new SysJsonTree("0", "信贷管理系统",
					(ArrayList) list, true);
			str = sysJsonTree.getJson();
			ServletActionContext.getRequest().getSession().setAttribute(
					"sysrolemenu", str);
		}

		return str;
	}
	/**
	 * 根据角色号查询所有菜单
	 * @param roleNo
	 * @return
	 * @throws ServiceException
	 */
	public List<SysMenu> getAllMenuByRole_no(String roleNo) throws ServiceException {
		List<SysMenu> list = null;
		try {
			list = sysMenuDao.getAllMenuByRole_no(roleNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}

	public String[] getParentMenuNameByMenuNo(String menuNo)
			throws ServiceException {
		// TODO Auto-generated method stub
		String[] menuName = new String[2];
		try {
			SysMenu sysMenu = sysMenuDao.getParentByMenuNo(menuNo);
			if (null != sysMenu) {
				menuName[0] = sysMenu.getMenuName();
				SysMenu sysMenu2 = sysMenuDao.getParentByMenuNo(sysMenu
						.getMenuNo());
				if (null != sysMenu2) {
					menuName[1] = sysMenu2.getMenuName();
				}
			}

		} catch (Exception e) { 
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return menuName;
	}

	public List<SysMenu> getAllMenuByParent(String menuNo) throws ServiceException {
		ArrayList<SysMenu> list = null;
		try {
			list = (ArrayList<SysMenu>) this.sysMenuDao.getAllMenuByParent(menuNo);
		} catch (Exception e) {
		}
		return list;
	}
	
	public SysMenu getById(String menuNo) throws ServiceException {
		SysMenu orig = null;
		try {
			orig = sysMenuDao.getById(menuNo);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return orig;
	}
	/**
	 * 根据角色号查询所有菜单
	 * @param roleNo
	 * @return
	 * @throws ServiceException
	 */
	public List<SysMenu> findMenuLev1ByRole(String roleNo) throws ServiceException {
		List<SysMenu> list = null;
		try {
			list = sysMenuDao.findMenuLev1ByRole(roleNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return list;
	}
	
	public void moveUpdate(SysMenu entity) throws ServiceException {
		try{
			sysMenuDao.moveUpdate(entity);
		} catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public boolean changeMenuSeqForMove(SysMenu moveSm, SysMenu targetSm) throws ServiceException {
		if(moveSm.getMenuParent().equals(targetSm.getMenuParent())){//若父级菜单相同，则直接排序
			return changeSysmenuSeq(moveSm,targetSm);
		}else{//若不同，则先将菜单项移动至要更换的父级菜单下，再进行排序
			moveSm.setMenuParent(targetSm.getMenuParent());
			moveUpdate(moveSm);
			return changeSysmenuSeq(moveSm,targetSm);
		}
	}	
	public boolean changeSysmenuSeq(SysMenu moveSm, SysMenu targetSm) {
		try {
			// 1.取出该父级菜单下的子菜单列表
			List<SysMenu> thisLvlList = sysMenuDao.getAllMenuByParent(moveSm
					.getMenuParent());
			Iterator<SysMenu> menuIter = thisLvlList.iterator();
			SysMenu sm = new SysMenu();
			if (compareSeqNum(moveSm, targetSm) < 0) {//拖拽菜单序号在目标序号之前
				while (menuIter.hasNext()) {
					sm = menuIter.next();
					if((sm.getMenuSeq() != null) && (moveSm.getMenuSeq()!=null) && (targetSm.getMenuSeq()!=null)){
						if((sm.getMenuSeq()>moveSm.getMenuSeq()) && (sm.getMenuSeq()<= targetSm.getMenuSeq())){
							sm.setMenuSeq(sm.getMenuSeq()-1);
							sysMenuDao.updateMenuSeq(sm);
						}
					}
				}
				moveSm.setMenuSeq(targetSm.getMenuSeq());
				sysMenuDao.updateMenuSeq(moveSm);
			} else {//拖拽菜单序号在目标序号之后
				while (menuIter.hasNext()) {
					sm = menuIter.next();
					if((sm.getMenuSeq() != null) && (moveSm.getMenuSeq()!=null) && (targetSm.getMenuSeq()!=null)){
						if((sm.getMenuSeq()<moveSm.getMenuSeq()) && (sm.getMenuSeq()>targetSm.getMenuSeq())){
							sm.setMenuSeq(sm.getMenuSeq()+1);
							sysMenuDao.updateMenuSeq(sm);
						}
					}
				}
				moveSm.setMenuSeq(targetSm.getMenuSeq() + 1);
				sysMenuDao.updateMenuSeq(moveSm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private int compareSeqNum(SysMenu firstSm, SysMenu secondSm){
		return Integer.valueOf(firstSm.getMenuSeq()) - Integer.valueOf(secondSm.getMenuSeq());
	}
}