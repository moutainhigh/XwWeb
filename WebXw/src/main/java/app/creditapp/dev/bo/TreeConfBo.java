package  app.creditapp.dev.bo;

import java.util.Map;

import app.base.ServiceException;
import app.util.toolkit.Ipage;

import app.creditapp.dev.entity.TreeConf;

/**
* Title: TreeConfBo.java
* Description:
**/
public interface TreeConfBo {

	public TreeConf getById(TreeConf treeConf) throws ServiceException;

	public void del(TreeConf treeConf) throws ServiceException;

	public void insert(TreeConf treeConf) throws ServiceException;

	public void update(TreeConf treeConf) throws ServiceException;

	public Ipage findByPage(Ipage ipg, TreeConf treeConf) throws ServiceException;
	
	/**
	 * @方法说明: 通过场景号以及页面传入参数，动态生成查询的SQL语句
	 * @param scene_id 场景号
	 * @param Map 参数
	 * @return
	 * @throws ServiceException
	 * @return  Map
	 * @修改说明:
	 */
	public Map createSql(String scene_id,Map<String,Object> parms) throws ServiceException;
	
	public Map createSqlPot(String scene_id,Map<String,Object> parms) throws ServiceException;

	/**
	 * @方法说明: 通过查询sql 将结果组装成ztee所需要的 json
	 * @param sql
	 * @return
	 * @return  String
	 * @修改说明:
	 */
	public String getTreeJson(String sql) throws ServiceException;

}