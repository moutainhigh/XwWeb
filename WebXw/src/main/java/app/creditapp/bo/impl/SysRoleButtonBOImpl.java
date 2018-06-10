package app.creditapp.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.ServiceException;
import app.base.SourceTemplate;
import app.creditapp.bo.SysRoleButtonBO;
import app.creditapp.dao.SysRoleButtonDAO;
import app.creditapp.entity.SysRoleButton;

public class SysRoleButtonBOImpl implements SysRoleButtonBO {
	
	private SysRoleButtonDAO sysRoleButtonDAO;

	public boolean isView(String info)
			throws ServiceException {
		List<SysRoleButton> list = null;
		String[] infos = info.split("@");
		Map<String,String> map = new HashMap<String,String>();
		map.put("menu_no", infos[0]);
		map.put("button_no", infos[1]);
		map.put("role_no", infos[2]);
		try{
			list = sysRoleButtonDAO.getById(map);
		}catch(Exception e){
			throw new ServiceException(e.getMessage());
		}
		if(list.size() == 0){
			map.clear();
			map = null;
			list = null;
			return false;
		}else{
			map.clear();
			map = null;
			list = null;
			return true;
		}
	}
	public List<SysRoleButton> getAll() throws ServiceException{
		SysRoleButtonDAO sysRoleButtonDAO = (SysRoleButtonDAO) SourceTemplate
		.getContext().getBean("sysRoleButtonDAO");
		List<SysRoleButton> list = sysRoleButtonDAO.getAll();
		return list;
	}

	public SysRoleButtonDAO getSysRoleButtonDAO() {
		return sysRoleButtonDAO;
	}

	public void setSysRoleButtonDAO(SysRoleButtonDAO sysRoleButtonDAO) {
		this.sysRoleButtonDAO = sysRoleButtonDAO;
	}
	

}
