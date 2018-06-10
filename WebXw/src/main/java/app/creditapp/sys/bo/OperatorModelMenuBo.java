package  app.creditapp.sys.bo;

import java.util.List;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.sys.entity.OperatorModelMenu;

/**
* Title: OperatorModelMenuBo.java
* Description:
**/
public interface OperatorModelMenuBo {

	public OperatorModelMenu getById(OperatorModelMenu operatorModelMenu) throws ServiceException;

	public void del(OperatorModelMenu operatorModelMenu) throws ServiceException;

	public void insert(OperatorModelMenu operatorModelMenu) throws ServiceException;

	public void update(OperatorModelMenu operatorModelMenu) throws ServiceException;

	public Ipage findByPage(Ipage ipg, OperatorModelMenu operatorModelMenu) throws ServiceException;
	
	/**
	 * @description 获取JSON菜单字符串
	 * @return
	 * @throws ServiceException
	 * @version 1.0
	 */
	public String getAllJsonMenu() throws ServiceException;
	
	/**
	 * @description 通过客户类型获取字符串
	 * @return
	 * @throws ServiceException
	 * @version 1.0
	 */
	public String getAllJsonMenuByType(String cif_type) throws ServiceException;
	
	public List<OperatorModelMenu> getAllMenuByParent(String menuNo) throws ServiceException;
	
	public OperatorModelMenu getByLastUrl(String last_url) throws ServiceException;


}