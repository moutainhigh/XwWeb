package app.creditapp.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.dao.SysRoleButtonDAO;
import app.creditapp.entity.SysRoleButton;
/**
 * 功能描述：权限对应按钮 DAOImpl class
 *
 */
public class SysRoleButtonDAOImpl extends BaseIbatisDao implements SysRoleButtonDAO {

	@SuppressWarnings("unchecked")
	public List<SysRoleButton> getById(Map<String, String> map)
			throws DAOException {
		List<SysRoleButton> list = null;
		try{
			list = this.getSqlMapClientTemplate().queryForList("SysRoleButton.getById",map);
		}catch(Exception e){
			log.error(e);
			throw new DAOException("查询失败");
		}
		return list;
	}
	public List<SysRoleButton> getAll() throws DAOException {
		List<SysRoleButton> list = null;
		try {
			list = this.getSqlMapClientTemplate().queryForList(
					"SysRoleButton.getAll");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return list;
	}
	public void deleteByMenuButtonNo(String menuNo, String buttonNo) throws DAOException {
		try {
			SysRoleButton sysRoleButton = new SysRoleButton();
			sysRoleButton.setButton_no(buttonNo);
			sysRoleButton.setMenu_no(menuNo);
			this.getSqlMapClientTemplate().delete("SysRoleButton.deleteByMenuButtonNo",sysRoleButton);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
	}
}
