package app.creditapp.sys.bo.impl;

import java.sql.Connection;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sys.bo.TableDocBO;
import app.creditapp.sys.dao.TableDocDAO;
import app.creditapp.sys.entity.TableDoc;
import app.creditapp.sysConfig.entity.TreeNode;
import app.util.DBUtils;
import app.util.json.TableDocMenuJson;
import app.util.toolkit.Ipage;

public class TableDocBOImpl extends BaseService implements TableDocBO {
	private TableDocDAO tableDocDao;

	public void del(String id) throws ServiceException {
		try {
			tableDocDao.del(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, TableDoc tabledoc)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) tableDocDao
					.getCount(tabledoc) });// 初始化分页类
			if (tabledoc == null)
				tabledoc = new TableDoc();
			tabledoc.setStartNumAndEndNum(ipg);
			ipg.setResult(tableDocDao.findByPage(tabledoc));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public TableDoc getById(String id) throws ServiceException {
		TableDoc tabledoc = null;
		try {
			tabledoc = tableDocDao.getById(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return tabledoc;
	}

	public List<TableDoc> getMkTblList() throws ServiceException {
		return tableDocDao.getMkTblList();
	}

	public void insertOrUpdate(TableDoc tabledoc) throws ServiceException {
		try {
			TableDoc tabledocs = tableDocDao.getById(tabledoc.getDoc_no());
			if (tabledocs == null) {
				tableDocDao.insert(tabledoc);
			} else {
				tableDocDao.update(tabledoc);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc) 返回所有菜单组成的字符串
	 * 
	 * @see app.creditapp.bo.TableDocBO#getAllJsonMenu()
	 */
	public String getAllJsonMenu() {
		List<TableDoc> menus = null;
		try {
			menus = tableDocDao.findAll("");
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		TableDocMenuJson json = new TableDocMenuJson(menus);
		String str = json.toJson();
		return str;
	}

	public void createdoc() {
		Connection conn = null;
		try {
			conn = DBUtils.getConn();
			tableDocDao.createdoc(conn);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}finally{
			DBUtils.closeConnection(conn);
		}
	};
	public void insertParmBatch(List<TreeNode> treeNodes) throws ServiceException {
		try {
			tableDocDao.insertParmBatch(treeNodes);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public TableDocDAO getTableDocDao() {
		return tableDocDao;
	}

	public void setTableDocDao(TableDocDAO tableDocDao) {
		this.tableDocDao = tableDocDao;
	}

}