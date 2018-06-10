package  app.creditapp.sys.bo;

import java.util.List;
import app.base.ServiceException;
import app.creditapp.sys.entity.SysMenu;

/**
* Title: SysMenuBo.java
* Description:
**/

public interface SysMenuBo {
	/**
	 * 获得所有菜单
	 * @param stats
	 * @return
	 * @throws DAOException
	 */
//	public List<SysMenu> findAll(String stats) throws ServiceException;
	/**
	 * 获得角色对应的菜单
	 * @param role_no
	 * @return
	 * @throws ServiceException
	 */
//	public List<SysRoleMenu> getSysMenuByRole_no(String role_no) throws ServiceException;
	/**
	 * 获得用户对应的菜单
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
//	public List<SysRoleMenu> getSysMenuByUser_id(String userid)throws ServiceException;
	/**
	 * 获得角色对应的所有子菜单，生成nav.js
	 * @param role_no
	 * @return 
	 * @throws ServiceException
	 */
//	public List<SysMenu> getAllMenuByRole(String role_no) throws ServiceException;
	/**
	 * 获得角色对应的所有1,2,3菜单
	 * @param role_no
	 * @return 
	 * @throws ServiceException
	 */
//	public List<SysMenu> getAllMenuByRoles(String role_no) throws ServiceException;
	/**
	 * 获得用户对应的所有子菜单，生成nav.js
	 * @param userid
	 * @return
	 * @throws ServiceException
	 */
//	public List<SysMenu> getAllMenuByUserId(String userid)throws ServiceException;
	/**
	 * 获得所有Json格式菜单
	 * @return
	 * @throws ServiceException
	 */
//	public String getAllJsonMenu() throws ServiceException;
	public String getAllJsonMenu1() throws ServiceException;
	public String getAllJsonMenu2() throws ServiceException;
	/**
	 * 分页查询
	 * @param ipg
	 * @param sm
	 * @return
	 * @throws ServiceException
	 */
//	public Ipage findPage(Ipage ipg,SysMenu sm) throws ServiceException;
	/**
	 * 新增和更新
	 * @param sm
	 * @throws ServiceException
	 */
//	public void saveOrUpdate(SysMenu sm) throws ServiceException;
	
	public void insert(SysMenu sm) throws ServiceException;
	
	public void update(SysMenu sm) throws ServiceException;
	/**
	 * 菜单号删除
	 * @param menu_no
	 * @throws ServiceException
	 */
	public void delete(String menuNo) throws ServiceException;
	
	public String getallsysmenu()throws ServiceException;
	public String getallrolemenu(String roleNo)throws ServiceException;
	public List<SysMenu> getmu()throws ServiceException;
	/**
	 * 查询一个操作员号下面对应的所有菜单的内容
	 * @param role_no
	 * @return 
	 * @throws ServiceException
	 */
	public String getAllMenuByRoleNo(String role_no) throws ServiceException;
	/*
	 * 根据快捷菜单号查询上级及上上级的菜单名称
	 */
	public String[] getParentMenuNameByMenuNo(String menuNo)throws ServiceException;
	
	/**
	 * 根据角色号查询所有菜单
	 * @param roleNo
	 * @return
	 * @throws ServiceException
	 */
	public List<SysMenu> getAllMenuByRole_no(String roleNo) throws ServiceException;
	
	public List<SysMenu> getAllMenuByParent(String menuNo) throws ServiceException;
	
	public SysMenu getById(String menuNo) throws ServiceException;
	/**
	 * 查询角色下所有一级菜单
	 * @param roleNo
	 * @return
	 * @throws ServiceException
	 */
	public List<SysMenu> findMenuLev1ByRole(String roleNo) throws ServiceException ;
	
	/*
	 * 拖拽菜单修改（需要动态修改序号）
	 */
	public void moveUpdate(SysMenu entity) throws ServiceException ;
	
	/*
	 * 同级节点拖拽
	 */
	public boolean changeMenuSeqForMove(SysMenu moveSm, SysMenu targetSm) throws ServiceException ;
	public boolean changeSysmenuSeq(SysMenu moveSm, SysMenu targetSm) throws ServiceException ;
}