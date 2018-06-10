package  app.creditapp.sysConfig.bo;


import java.util.Map;

import app.base.ServiceException;
import app.creditapp.sysConfig.entity.SysUpdateRecord;
import app.util.toolkit.Ipage;


/**
* Title: TableUpdateRecordBo.java
**/

public interface SysUpdateRecordBo {

	public SysUpdateRecord getById(String id) throws ServiceException;

	public void del(String id) throws ServiceException;

	public void insert(SysUpdateRecord sysUpdateRecord) throws ServiceException;

	public void update(SysUpdateRecord sysUpdateRecord) throws ServiceException;

	public Ipage findByPage(Ipage ipg, SysUpdateRecord sysUpdateRecord) throws ServiceException;

}
