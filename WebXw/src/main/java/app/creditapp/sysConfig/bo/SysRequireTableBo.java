package app.creditapp.sysConfig.bo;

import java.sql.SQLException;
import java.util.List;

import app.base.ServiceException;
import app.creditapp.sysConfig.entity.SysRequireTable;
import app.creditapp.sysConfig.entity.TableInfo;
import app.util.toolkit.Ipage;

/**
 * Title: RequireLogTableBo.java Description:
 * 
 **/

public interface SysRequireTableBo {

	public SysRequireTable getById(SysRequireTable requireLogTable)
			throws ServiceException;

	public void del(SysRequireTable requireLogTable) throws ServiceException;

	public void insert(SysRequireTable requireLogTable) throws ServiceException;

	public void update(SysRequireTable requireLogTable) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysRequireTable requireLogTable)
			throws ServiceException;

	public List<TableInfo> getAllTable(TableInfo tableInfo)
			throws ServiceException;

	public String getTblStringFromList() throws ServiceException;

	public String getStringFromList() throws ServiceException, SQLException;
}
