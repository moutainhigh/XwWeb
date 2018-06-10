package  app.creditapp.sys.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.base.ServiceException;
import app.creditapp.sys.entity.SysMenu;
import app.creditapp.sys.entity.SysRoleMenu;
/**
* Title: SysMenuDao.java
* Description:
**/

public interface SysMenuDao {
	
	/**
	 * 获得所有菜单
	 * @param stats
	 * @return
	 * @throws DAOException
	 */
	public List<SysMenu> findAll(String stats) throws DAOException;
	
	/**
	 * 保存
	 * @param entity
	 * @throws DAOException
	 */
	public void insert(SysMenu entity)  throws DAOException;
	
	/**
	 * 更新
	 * @param entity
	 * @throws DAOException
	 */
	public void update(SysMenu entity) throws DAOException;
	
	/**
	 * 分页查找
	 * @param map
	 * @return
	 * @throws DAOException
	 */
	public List<SysMenu> findPage(Map<String,Object> map) throws DAOException;
	
	/**
	 * 菜单号删除
	 * @param menu_no
	 * @return
	 * @throws DAOException
	 */
	public String delete(String menuNo) throws DAOException;
	/**
	 * 获得计数
	 * @param sm
	 * @return
	 * @throws DAOException
	 */
	public int getCount(SysMenu sm) throws DAOException;
/**
 * 
 * @param roleno
 * @return 根据角色获取一级菜单
 * @throws DAOException
 */
	public List<SysRoleMenu> getSysMenuByRoleNo(String roleno) throws DAOException;
	/**
	 * 根据用户获取一级菜单
	 * @param userid
	 * @return
	 * @throws DAOException
	 */
	public List<SysRoleMenu> getSysMenuByUserId(String roleNo)throws DAOException;
	/**
	 * 
	 * @param roleno
	 * @return 根据角色获取一级菜单
	 * @throws DAOException
	 */
	public List<SysMenu> getAllMenuByRole(String roleno) throws DAOException;
	/**
	 * 
	 * @param roleno
	 * @return 根据角色获取1,2,3级菜单
	 * @throws DAOException
	 */
	public List<SysMenu> getAllMenuByRoles(String roleno) throws DAOException;
	/**
	 * 
	 * @param userid
	 * @return
	 * @throws DAOException
	 */
	public List<SysMenu> getAllMenuByUserId(String roleNo)throws DAOException;
	
	
	/**
	 * Url查询父菜单
	 * @param menu_url
	 * @return
	 * @throws DAOException
	 */
	public SysMenu getParentByMenuUrl(String menu_url) throws DAOException;
	/**
	 * 获得所有二级菜单
	 * @return
	 * @throws DAOException
	 */
	public List<SysMenu> getAllLev2Menu() throws DAOException;
	/**
	 * 
	 */
	public List<SysMenu> getallsysmenu()throws DAOException;
	/**
	 * 获得所有二级菜单
	 * @return
	 * @throws DAOException
	 */
	public List<SysMenu> getAllMenuByRoleNo(String role_no) throws DAOException;
	/**
	 * 
	 * @param roleno
	 * @return
	 * @throws DAOException
	 * @desc 根据角色号查询所有菜单
	 * List<SysRoleMenu>
	 */
	public List<SysMenu> getAllMenuByRole_no(String roleno) throws DAOException;
	public SysMenu getParentByMenuNo(String menuNo) throws DAOException ;
	public List<SysMenu> findAll1() throws DAOException;

	public List<SysMenu> getAllMenuByParent(String menuParent) throws DAOException;

	public SysMenu getById(String menuNo) throws DAOException;

	public List<SysMenu> findMenuLev1ByRole(String roleno)throws DAOException ;
	
	/*
	 * 拖拽菜单修改（需要动态修改序号）
	 */
	public void moveUpdate(SysMenu entity) throws DAOException ;
	/*
	 * 拖拽后修改菜单序号
	 */
	public void updateMenuSeq(SysMenu entity) throws DAOException ;
}
