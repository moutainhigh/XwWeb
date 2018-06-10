package  app.creditapp.sys.dao.impl;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysMenuDao;
import app.creditapp.sys.entity.SysMenu;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysMenuDaoImpl.java
* Description:
**/

public class SysMenuDaoImpl extends BaseIbatisDao implements SysMenuDao {

	/*
	 * @see app.creditapp.dao.Sys_MenuDAO#save(app.creditapp.entity.Sys_Menu)
	 */
	public void insert(SysMenu entity) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysMenu.insert", entity);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("新增失败");
		}
	}
	

	/*
	 * @see app.creditapp.dao.Sys_MenuDAO#update(app.creditapp.entity.Sys_Menu)
	 */
	public void update(SysMenu entity) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysMenu.update", entity);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("更新失败");
		}
	}

	/*
	 * @see app.creditapp.dao.Sys_MenuDAO#findPage(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> findPage(Map<String, Object> map) throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.findByPage",
					map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return lst;
	}

	public String delete(String menuNo) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysMenu.delete", menuNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("菜单号删除失败");
		}
		return null;
	}
	
	/*
	 * @see app.creditapp.dao.Sys_MenuDAO#getCount(app.creditapp.entity.Sys_Menu)
	 */
	public int getCount(SysMenu sm) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysMenu.findPage.count", sm);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("失败于查询记录条数!");
		}
		return count;
	}

	/*
	 * @see app.creditapp.dao.Sys_MenuDAO#findAll(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> findAll(String stats) throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.findAll",
					stats);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}
	
	public List<SysMenu> findAll1() throws DAOException{
		List<SysMenu> list = null;
		try{
			list = getSqlMapClientTemplate().queryForList("SysMenu.findAll1");
		}catch(Exception e){
			log.error(e);
			throw new DAOException("查询失败");
		}
		return list;
	}

	/**
	 * 功能：根据角色编号获得相应的菜单
	 */
	@SuppressWarnings("unchecked")
	public List<SysRoleMenu> getSysMenuByRoleNo(String role_no)
			throws DAOException {
		List<SysRoleMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysRoleMenu.getMenuByRole", role_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}

	@SuppressWarnings("unchecked")
	public List<SysMenu> getAllMenuByRole(String role_no) throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.getAllMenuByRole",role_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}
	
	//查询角色下的所有菜单，包括1,2,3级
	@SuppressWarnings("unchecked")
	public List<SysMenu> getAllMenuByRoles(String role_no) throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.getAllMenuByRoles",role_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}

	/*
	 * @see app.creditapp.dao.Sys_MenuDAO#getParentByMenu_url(java.lang.String)
	 */
	public SysMenu getParentByMenuUrl(String menu_url) throws DAOException {
		SysMenu menu = null;
		try {
			if(StringUtils.isNotBlank(menu_url)){
				menu = (SysMenu)getSqlMapClientTemplate().queryForObject("SysMenu.getParentByMenu_url",menu_url);
			}
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return menu;
	}
	public SysMenu getParentByMenuNo(String menuNo) throws DAOException {
		SysMenu menu = null;
		try {
			if(StringUtils.isNotBlank(menuNo)){
				menu = (SysMenu)getSqlMapClientTemplate().queryForObject("SysMenu.getParentByMenu_no",menuNo);
			}
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询父菜单失败");
		}
		return menu;
	}
	/*
	 * @see app.creditapp.dao.Sys_MenuDAO#getAllLev2Menu()
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> getAllLev2Menu() throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.getAllLev2Menu");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有二级菜单失败");
		}
		return lst;
	}


	public List<SysMenu> getallsysmenu() throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.findAll");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}


	public List<SysMenu> getAllMenuByRoleNo(String role_no) throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.findByRoleNo",role_no);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询操作员权限下所有菜单失败");
		}
		return lst;
	}


	public List<SysRoleMenu> getSysMenuByUserId(String roleno)
			throws DAOException {
		// TODO Auto-generated method stub
		List<SysRoleMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysRoleMenu.getMenuByUserId", roleno);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}


	public List<SysMenu> getAllMenuByUserId(String roleNo) throws DAOException {
		// TODO Auto-generated method stub
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.getAllMenuByUserId",roleNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}


	public List<SysMenu> getAllMenuByRole_no(String roleNo)
			throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.findAllByRole",
					roleNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}
	
	public List<SysMenu> getAllMenuByParent(String menuParent) throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.getAllMenuByParent",
					menuParent);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return lst;
	}
	
	public SysMenu getById(String menuNo) throws DAOException {
		SysMenu menu = null;
		try {
			if(StringUtils.isNotBlank(menuNo)){
				menu = (SysMenu)getSqlMapClientTemplate().queryForObject("SysMenu.getById",menuNo);
			}
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询菜单失败");
		}
		return menu;
	}
	
	public List<SysMenu> findMenuLev1ByRole(String roleno)throws DAOException {
		List<SysMenu> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysMenu.findMenuLev1ByRole",roleno);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询所有菜单失败");
		}
		return lst;
	}
	
	/*
	 * 拖拽菜单修改（需要动态修改序号）
	 */
	public void moveUpdate(SysMenu entity) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysMenu.moveUpdate", entity);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("更新失败");
		}
	}
	
	/*
	 * 拖拽后修改菜单序号
	 */
	public void updateMenuSeq(SysMenu entity) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysMenu.updateMenuSeq", entity);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("拖拽后修改编号失败");
		}
	}
}