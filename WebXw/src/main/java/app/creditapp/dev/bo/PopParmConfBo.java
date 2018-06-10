package  app.creditapp.dev.bo;

import java.util.Map;

import app.base.ServiceException;
import app.creditapp.dev.entity.PopParmConf;
import app.util.toolkit.Ipage;

/**
* Title: PopParmConfBo.java
* Description:
**/
public interface PopParmConfBo {

	public PopParmConf getById(PopParmConf popParmConf) throws ServiceException;

	public void del(PopParmConf popParmConf) throws ServiceException;

	public void insert(PopParmConf popParmConf) throws ServiceException;

	public void update(PopParmConf popParmConf) throws ServiceException;

	public Ipage findByPage(Ipage ipg, PopParmConf popParmConf) throws ServiceException;
	/**
	 * @description 通过场景号以及参数异步查询
	 * @param scene_id
	 * @param parms
	 * @return
	 * @throws ServiceException
	 * @version 1.0
	 */
	public Map queryAjax(String scene_id,String parms) throws ServiceException;
	
	/**
	 * @description 通过场景号以及参数动态获取查询sql语句
	 * @param scene_id
	 * @param parms
	 * @return
	 * @throws ServiceException
	 * @version 1.0
	 */
	public Map createSql(String scene_id,String parms) throws ServiceException;
	
	/**
	 * @方法描述 分页查询
	 * @param scene_id
	 * @param parms
	 * @return
	 * @throws ServiceException
	 */
	public Map findByPop(String scene_id,String parms,String query_sql) throws ServiceException;

	public Map findByPopChB(String sceneId, String parms)throws ServiceException;
}