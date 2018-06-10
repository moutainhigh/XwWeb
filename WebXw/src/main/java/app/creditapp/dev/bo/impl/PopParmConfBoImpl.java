package  app.creditapp.dev.bo.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.struts2.ServletActionContext;

import app.base.BaseService;
import app.base.ServiceException;
import app.creditapp.dev.bo.PopParmConfBo;
import app.creditapp.dev.dao.PopParmConfDao;
import app.creditapp.dev.entity.PopParmConf;
import app.util.PopUtil;
import app.util.User;
import app.util.toolkit.Ipage;
/**
* Title: PopParmConfBoImplImpl.java
* Description:
**/
public class PopParmConfBoImpl extends BaseService implements PopParmConfBo {

	private PopParmConfDao popParmConfDao;
	private DataSource dataSource;

	public void del(PopParmConf popParmConf) throws ServiceException {
		try {
			popParmConfDao.del(popParmConf);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Ipage findByPage(Ipage ipg, PopParmConf popParmConf)
			throws ServiceException {
		try {
			ipg.initPageCounts(new Integer[] { (Integer) popParmConfDao
					.getCount(popParmConf) });// 初始化分页类
			popParmConf.setStartNumAndEndNum (ipg);
			ipg.setResult(popParmConfDao.findByPage(popParmConf));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return ipg;
	}
	@Override
	public Map findByPopChB(String sceneId, String parms)
			throws ServiceException {
		Map<String,Object> map = new HashMap<String,Object>();
		Connection conn = null;

		try{
			//1、查询该POP的相关属性
			PopParmConf popParmConf = new PopParmConf();
			popParmConf.setScene_id(sceneId);
			popParmConf = popParmConfDao.getById(popParmConf);
			//2、获得配置的SQL、进行参数替换
			String sql = popParmConf.getSql();
			String dyn_or = popParmConf.getDyn_or();
			String dyn_and = popParmConf.getDyn_and();
			String orderBy = popParmConf.getOrderby();
			if( dyn_and!=null && !"".equals(dyn_and) ){
				sql = PopUtil.getSql(sql, dyn_and, parms, "AND");
			}
			if( dyn_or!=null && !"".equals(dyn_or) ){
				sql = PopUtil.getSql(sql, dyn_and, parms, "OR");
			}
			if( orderBy!=null && !"".equals(orderBy) ){
				sql = sql+" "+orderBy;
			}
			String count_sql = "SELECT count(*) from (" + sql +")";
			conn = dataSource.getConnection();
			int count = popParmConfDao.getCountPop(conn, count_sql);
			//3、map相关的赋值
			map.put("sql", sql);
			map.put("rel", popParmConf.getDb_bean_rel());
			map.put("sceneId", sceneId);
			map.put("size", count);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public Map findByPop(String sceneId, String parms,String query_sql) throws ServiceException {
		Map<String,Object> map = new HashMap<String,Object>();
		Connection conn = null;
		try{
			//1、查询该POP的相关属性
			PopParmConf popParmConf = new PopParmConf();
			popParmConf.setScene_id(sceneId);
			popParmConf = popParmConfDao.getById(popParmConf);
			//2、获得配置的SQL、进行参数替换
			String sql = popParmConf.getSql();
			String dyn_or = popParmConf.getDyn_or();
			String dyn_and = popParmConf.getDyn_and();
			String orderBy = popParmConf.getOrderby();
			String if_query = popParmConf.getIf_query();
			
			
			if( dyn_and!=null && !"".equals(dyn_and) ){
				
				//sql = PopUtil.getSql(sql, dyn_and, parms, "AND");
				if( query_sql!=null && !"".equals(query_sql) ){
					sql = PopUtil.getSql(query_sql, dyn_and, parms, "AND");
				}else{
					sql = PopUtil.getSql(sql, dyn_and, parms, "AND");
				}
			}
			if( dyn_or!=null && !"".equals(dyn_or) ){
				// sql已经过dyn_and加工，直接拼接	
				sql = PopUtil.getSql(sql, dyn_or, parms, "OR");
			}
			
			// 查询权限控制
			String loginid = User.getLoginIdByAuth(ServletActionContext.getRequest());
			if(!"".equals(loginid) && !parms.contains("proj_no")){
				if("POP031".equals(sceneId)){ // 资金上查询项目的POP
					sql = sql + " AND PROJ_NO IN (SELECT PROJ_NO FROM proj_mang WHERE login_id='"+loginid+"') ";
				}
			}
////			if("POP056".equals(sceneId) || "POP061".equals(sceneId)){//start with
//			if("POP056".equals(sceneId)){//start with
//				sql = sql + " START WITH br_no=(select br_no from sys_user_info where user_no = '" + User.getLoginid(ServletActionContext.getRequest()) + "') connect by prior br_no=parent";
//			}
			//System.out.println("没有没拼接order by的POP");
			System.out.println("POP_SQL = " + sql);
		    //3、获得count
			String count_sql = "SELECT count(*) from (" + sql +")";
			conn = dataSource.getConnection();
			int count = popParmConfDao.getCountPop(conn, count_sql);
			//4、获得分页查询的sql
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM (SELECT m.*, rownum as rnum  FROM (");
			sb.append(sql);
			if( orderBy!=null && !"".equals(orderBy) ){
				sb.append(" "+orderBy);
			}
			sb.append(") m WHERE  rownum < ? ) WHERE rnum >= ? ");
			//5、map相关的赋值
			map.put("sql", sb.toString());
			map.put("query_sql", sql);//放隐藏域--》供查询的时候拼接使用
			map.put("size", count);
			map.put("col_name", popParmConf.getCol_name());
			map.put("query_name", popParmConf.getQuery_name());
			map.put("disName", popParmConf.getQuery_disName());
			map.put("pageNo", popParmConf.getPage_no());
			map.put("rel", popParmConf.getDb_bean_rel());
			map.put("sceneId", sceneId);
			if( if_query!=null && !"".equals(if_query) ){
				map.put("if_query", if_query);
			}else{
				map.put("if_query", "null");
			}
			String hidden = popParmConf.getHidden_col();
			if( hidden!=null && !"".equals(hidden) ){
				map.put("hidden_col", hidden);
			}else{
				map.put("hidden_col", "null");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public PopParmConf getById(PopParmConf popParmConf) throws ServiceException {
		try {
			popParmConf = popParmConfDao.getById(popParmConf);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return popParmConf;
	}
	
	public void insert(PopParmConf popParmConf) throws ServiceException {
		int maxId = 0;
		try {
//			maxId = popParmConfDao.getMaxId();
//			if( maxId<10 ){//如果是一位，前面加"00"
//				popParmConf.setScene_id("pop_00"+maxId);
//			}else if( maxId<100 && maxId>9 ){//如果是两位，前加“0”
//				popParmConf.setScene_id("pop_0"+maxId);
//			}else{
//				popParmConf.setScene_id("pop_"+maxId);
//			}
			popParmConfDao.insert(popParmConf);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Map queryAjax(String scene_id,String parms) throws ServiceException{
		Connection conn = null;
		Map map = new HashMap();
		String sql = "";
		try{
			conn = dataSource.getConnection();
			map = createSql(scene_id,parms);
			sql = (String) map.get("sql");
			map = popParmConfDao.queryAjax(conn, sql,map);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * @description 通过场景号以及页面传入参数，动态生成查询的SQL语句
	 * @param scene_id
	 * @param parms
	 * @return
	 * @throws ServiceException
	 * @version 1.0
	 */
	public Map createSql(String scene_id,String parms) throws ServiceException {
		Map map = new HashMap();
		String col = "";
		//多个查询条件 {cif_no=22,cif_name=jk}
		String[] arr = new String[] {};
		//上面以逗号分隔 {cif_no,22}
		String[] arr_split = new String[]{};
		//数据库对应实体关系{cif_no-cif_no,cif_name-cifname}
		String[] db_bean_arr = new String[] {};
		//以"-"分隔{cif_no,cif_no}
		String[] db_b_arr_split = new String[] {};
		PopParmConf popParmConf = new PopParmConf();
		popParmConf.setScene_id(scene_id);
		popParmConf = getById(popParmConf);
		String sql = popParmConf.getSql();
		//数据库与实体属性对应关系
		String db_bean_rel = popParmConf.getDb_bean_rel();
		db_bean_arr = db_bean_rel.split(",");
		//动态配置sql语句一部分
		if( !"".equals(parms) ){//多个查询条件：cif_no=222,cif_type=1
			arr = parms.split(",");
			for( int i=0;i<arr.length;i++ ){
				arr_split = arr[i].split("=");
				for (int j = 0; j < db_bean_arr.length; j++) {
					db_b_arr_split = db_bean_arr[j].split("-");
					if( arr_split[0].contains("@") ){
						if (db_b_arr_split[1].equals(arr_split[0].split("@")[0])) {
							col += "" + db_b_arr_split[0]+" like "+"'%"+arr_split[1]+"%'" ;
							if( i==arr.length-1 ){
								col += " " ;
							}else{
								col += " and " ;
							}
								
						}
					}else{
						if (db_b_arr_split[1].equals(arr_split[0])) {
							col += "" + db_b_arr_split[0]+"="+"'"+arr_split[1]+"'" ;
							if( i==arr.length-1 ){
								col += " " ;
							}else{
								col += " and " ;
							}
								
						}
					}
				}
			}
			sql = sql + " where " + col ;
		}
		System.out.println("POP查询SQL:"+sql);
		map.put("sql", sql);
		map.put("rel", db_bean_rel);
		map.put("col_name", popParmConf.getCol_name());
		return map;
	}
	public void update(PopParmConf popParmConf) throws ServiceException {
		try {
			popParmConfDao.update(popParmConf);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	public PopParmConfDao getPopParmConfDao() {
		return popParmConfDao;
	}

	public void setPopParmConfDao(PopParmConfDao popParmConfDao) {
		this.popParmConfDao = popParmConfDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}