package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.OperatorModelMenu;
/**
* Title: OperatorModelMenuDao.java
* Description:
**/
public interface OperatorModelMenuDao {

	public OperatorModelMenu getById(OperatorModelMenu operatorModelMenu) throws DAOException;

	public void del(OperatorModelMenu operatorModelMenu) throws DAOException;

	public void insert(OperatorModelMenu operatorModelMenu) throws DAOException;

	public void update(OperatorModelMenu operatorModelMenu) throws DAOException;
	
	public int getCount(OperatorModelMenu operatorModelMenu) throws DAOException;

	public List<OperatorModelMenu > findByPage(OperatorModelMenu operatorModelMenu) throws DAOException;
	
	/**
	 * @description 查找所有菜单
	 * @return
	 * @throws DAOException
	 * @version 1.0
	 */
	public List<OperatorModelMenu > findAll() throws DAOException;
	
	/**
	 * @description 通过客户类型查询所有菜单
	 * @return
	 * @throws DAOException
	 * @version 1.0
	 */
	public List<OperatorModelMenu > findAllByType(String cif_type) throws DAOException;
	
	/**
	 * @description 通过父菜单找所有
	 * @param menuNo
	 * @return
	 * @throws DAOException
	 * @version 1.0
	 */
	public List<OperatorModelMenu > getAllMenuByParent(String menuNo) throws DAOException;
	
	/**
	 * @description 通过上一请求URL地址获取菜单相关信息
	 * @param operatorModelMenu
	 * @return
	 * @throws DAOException
	 * @version 1.0
	 */
	public OperatorModelMenu getByLastUrl(String last_url) throws DAOException;

}