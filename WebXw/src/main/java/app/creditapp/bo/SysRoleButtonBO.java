package app.creditapp.bo;

import java.util.List;

import app.base.ServiceException;
import app.creditapp.entity.SysRoleButton;

/**
 * 功能描述：权限对应按钮 BO interface
 *
 */
public interface SysRoleButtonBO {
	
	public boolean isView(String info) throws ServiceException;
	public List<SysRoleButton> getAll() throws ServiceException;

}
