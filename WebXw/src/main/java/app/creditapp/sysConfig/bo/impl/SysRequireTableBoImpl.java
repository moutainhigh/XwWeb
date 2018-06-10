package app.creditapp.sysConfig.bo.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.sys.bo.TableDocBO;
import app.creditapp.sys.entity.TableDoc;
import app.creditapp.sysConfig.bo.SysRequireTableBo;
import app.creditapp.sysConfig.dao.SysRequireTableDao;
import app.creditapp.sysConfig.entity.SysRequireTable;
import app.creditapp.sysConfig.entity.TableInfo;
import app.creditapp.sysConfig.util.GetTreeVal;
import app.util.IbatisUtils;
import app.util.toolkit.Ipage;

/**
 * Title: RequireLogTableBoImpl.java Description:
 * 
 **/

public class SysRequireTableBoImpl extends BaseService implements
		SysRequireTableBo {

	private SysRequireTableDao sysRequireTableDao;
	private TableDocBO tableDocBo;

	public void del(SysRequireTable sysRequireTable) throws ServiceException {
		try {
			sysRequireTableDao.del(sysRequireTable);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, SysRequireTable sysRequireTable)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) sysRequireTableDao
					.getCount(sysRequireTable) });// 初始化分页类
			HashMap<String, Object> map = (HashMap<String, Object>) IbatisUtils
					.getEntityPropertyMap(ipg, sysRequireTable);
			map.put("startNum", ipg.getStartRow());
			map.put("endNum", ipg.getEndNum());
			ipg.setResult(sysRequireTableDao.findByPage(map));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}

	public SysRequireTable getById(SysRequireTable sysRequireTable)
			throws ServiceException {
		try {
			sysRequireTable = sysRequireTableDao.getById(sysRequireTable);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return sysRequireTable;
	}

	public void insert(SysRequireTable sysRequireTable) throws ServiceException {
		try {
			sysRequireTableDao.insert(sysRequireTable);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(SysRequireTable sysRequireTable) throws ServiceException {
		try {
			sysRequireTableDao.update(sysRequireTable);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<TableInfo> getAllTable(TableInfo tableInfo)
			throws ServiceException {
		return this.sysRequireTableDao.getAllTable(tableInfo);
	}

	public List<TableInfo> getAllTableNotExistsDoc(TableInfo tableInfo)
			throws ServiceException {
		return this.sysRequireTableDao.getAllTableNotExistsDoc(tableInfo);
	}

	public String getStringFromList() throws ServiceException, SQLException {
		List<TableInfo> tablelist = this.getAllTableNotExistsDoc(new TableInfo());
		return GetTreeVal.toJsp2(tablelist);
	}

	public String getTblStringFromList() throws ServiceException {
		List<TableDoc> tableDocs = tableDocBo.getMkTblList();
		StringBuffer parm_str = new StringBuffer("[");
		for (TableDoc tableDoc : tableDocs) {
			parm_str.append(",{ id:").append("'").append(tableDoc.getDoc_no())
					.append("'").append(", pId:");
			parm_str.append("'").append(tableDoc.getUplev()).append("'")
					.append(", name:'");
			parm_str.append(tableDoc.getDoc_name()).append("',");
			if ("0".equals(tableDoc.getUplev())) {
				parm_str.append("open:true}");
			} else {
				parm_str.append("open:false}");
			}

		}
		parm_str.append("]");
		parm_str.replace(1, 2, "");
		return parm_str.toString();
	}

	public SysRequireTableDao getSysRequireTableDao() {
		return sysRequireTableDao;
	}

	public void setSysRequireTableDao(SysRequireTableDao sysRequireTableDao) {
		this.sysRequireTableDao = sysRequireTableDao;
	}

	public TableDocBO getTableDocBo() {
		return tableDocBo;
	}

	public void setTableDocBo(TableDocBO tableDocBo) {
		this.tableDocBo = tableDocBo;
	}

}
