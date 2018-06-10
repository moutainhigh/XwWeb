package app.creditapp.dao;

import java.util.List;
import java.util.Map;

import app.base.DAOException;
import app.creditapp.entity.SysRoleButton;

/**
 * 功能描述：权限对应按钮 DAO interface
 *
 */
public interface SysRoleButtonDAO {
	
	public List<SysRoleButton> getById(Map<String,String> map) throws DAOException;
	public List<SysRoleButton> getAll() throws DAOException;
	/**
	 * 通过按钮编号及菜单编号删除 权限记录
	 * @param menuNo 菜单编号
	 * @param buttonNo 按钮编号
	 * @throws DAOException
	 */
	public void deleteByMenuButtonNo(String menuNo, String buttonNo) throws DAOException;
}
