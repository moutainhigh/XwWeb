package  app.creditapp.sys.bo.impl;

import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.util.toolkit.Ipage;
import app.creditapp.sys.bo.OperatorModelMenuBo;
import app.creditapp.sys.dao.OperatorModelMenuDao;
import app.creditapp.sys.entity.OperatorModelMenu;
/**
* Title: OperatorModelMenuBoImplImpl.java
* Description:
**/
public class OperatorModelMenuBoImpl extends BaseService implements OperatorModelMenuBo {

	private OperatorModelMenuDao operatorModelMenuDao;

	public void del(OperatorModelMenu operatorModelMenu) throws ServiceException {
		try {
			operatorModelMenuDao.del(operatorModelMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, OperatorModelMenu operatorModelMenu)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) operatorModelMenuDao
					.getCount(operatorModelMenu) });// 初始化分页类
			operatorModelMenu.setStartNumAndEndNum (ipg);
			ipg.setResult(operatorModelMenuDao.findByPage(operatorModelMenu));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public OperatorModelMenu getById(OperatorModelMenu operatorModelMenu) throws ServiceException {
		try {
			operatorModelMenu = operatorModelMenuDao.getById(operatorModelMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return operatorModelMenu;
	}

	public void insert(OperatorModelMenu operatorModelMenu) throws ServiceException {
		try {
			operatorModelMenuDao.insert(operatorModelMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(OperatorModelMenu operatorModelMenu) throws ServiceException {
		try {
			operatorModelMenuDao.update(operatorModelMenu);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public String getAllJsonMenu() throws ServiceException {
		List<OperatorModelMenu> menus = null;
		try {
			menus = operatorModelMenuDao.findAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		StringBuffer menuStr = new StringBuffer();
		menuStr.append("[");
		OperatorModelMenu sysMenu = new OperatorModelMenu();
		for (int i = 0; i < menus.size(); i++) {
			sysMenu = menus.get(i);
			if("0".equals(sysMenu.getMenu_lvl())){
				menuStr.append(",{'checked':"+true+",'text':'"+sysMenu.getMenu_name()+"','name':'"+sysMenu.getMenu_name()+
						"','id':'"+sysMenu.getMenu_no()+"','url':'"+sysMenu.getMenu_url()+"','stats':'"+sysMenu.getMenu_url()
						+"','lvl':'"+sysMenu.getMenu_lvl()+"','pId':'"+sysMenu.getMenu_parent()+"','last':'"+sysMenu.getLast_url()+
						"','cif':'"+sysMenu.getCif_type()+"','open':"+true+"}");
			} else {
				menuStr.append(",{'checked':"+true+",'text':'"+sysMenu.getMenu_name()+"','name':'"+sysMenu.getMenu_name()+
						"','id':'"+sysMenu.getMenu_no()+"','url':'"+sysMenu.getMenu_url()+"','stats':'"+sysMenu.getMenu_url() +
						"','lvl':'"+sysMenu.getMenu_lvl()+"','pId':'"+sysMenu.getMenu_parent()+"','last':'"+sysMenu.getLast_url()+
						"','cif':'"+sysMenu.getCif_type()+"','open':"+false+"}");
			}
		}
		menuStr.append("]");
		menuStr.replace(1, 2, "");
		System.out.println("菜单列表：" + menuStr.toString());
		return menuStr.toString();
	}
	
	public List<OperatorModelMenu> getAllMenuByParent(String menuNo)
			throws ServiceException {
		return operatorModelMenuDao.getAllMenuByParent(menuNo);
	}
	
	public String getAllJsonMenuByType(String cif_type) throws ServiceException {
		List<OperatorModelMenu> menus = null;
		try {
			menus = operatorModelMenuDao.findAllByType(cif_type);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		StringBuffer menuStr = new StringBuffer();
		menuStr.append("[");
		OperatorModelMenu sysMenu = new OperatorModelMenu();
		for (int i = 0; i < menus.size(); i++) {
			sysMenu = menus.get(i);
			if("0".equals(sysMenu.getMenu_lvl())){
				menuStr.append(",{'checked':"+true+",'text':'"+sysMenu.getMenu_name()+"','name':'"+sysMenu.getMenu_name()+
						"','id':'"+sysMenu.getMenu_no()+"','url':'"+sysMenu.getMenu_url()+"','stats':'"+sysMenu.getMenu_url()
						+"','lvl':'"+sysMenu.getMenu_lvl()+"','pId':'"+sysMenu.getMenu_parent()+"','last':'"+sysMenu.getLast_url()+
						"','cif':'"+sysMenu.getCif_type()+"','open':"+true+"}");
			} else {
				menuStr.append(",{'checked':"+true+",'text':'"+sysMenu.getMenu_name()+"','name':'"+sysMenu.getMenu_name()+
						"','id':'"+sysMenu.getMenu_no()+"','url':'"+sysMenu.getMenu_url()+"','stats':'"+sysMenu.getMenu_url() +
						"','lvl':'"+sysMenu.getMenu_lvl()+"','pId':'"+sysMenu.getMenu_parent()+"','last':'"+sysMenu.getLast_url()+
						"','cif':'"+sysMenu.getCif_type()+"','open':"+false+"}");
			}
		}
		menuStr.append("]");
		menuStr.replace(1, 2, "");
		System.out.println("菜单列表：" + menuStr.toString());
		return menuStr.toString();
	}
	
	public OperatorModelMenuDao getOperatorModelMenuDao() {
		return operatorModelMenuDao;
	}

	public void setOperatorModelMenuDao(OperatorModelMenuDao operatorModelMenuDao) {
		this.operatorModelMenuDao = operatorModelMenuDao;
	}

	public OperatorModelMenu getByLastUrl(String last_url)
			throws ServiceException {
		return operatorModelMenuDao.getByLastUrl(last_url);
	}


}