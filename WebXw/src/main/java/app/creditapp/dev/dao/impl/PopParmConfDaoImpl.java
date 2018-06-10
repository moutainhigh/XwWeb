package  app.creditapp.dev.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.dev.dao.PopParmConfDao;
import app.creditapp.dev.entity.PopParmConf;
/**
* Title: PopParmConfDaoImpl.java
* Description:
**/
public class PopParmConfDaoImpl extends BaseIbatisDao implements PopParmConfDao {

	public void del(PopParmConf popParmConf) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("PopParmConf.del", popParmConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<PopParmConf> findByPage(PopParmConf popParmConf) throws DAOException {
		List popParmConfList = null;
		try {
			popParmConfList = getSqlMapClientTemplate().queryForList(
					"PopParmConf.findByPage", popParmConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return popParmConfList;
	}

	public PopParmConf getById(PopParmConf popParmConf) throws DAOException {
		try {
			popParmConf = (PopParmConf) getSqlMapClientTemplate()
					.queryForObject("PopParmConf.getById", popParmConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return popParmConf;
	}

	public int getCount(PopParmConf popParmConf) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PopParmConf.findPage.count", popParmConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(PopParmConf popParmConf) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PopParmConf.insert",
					popParmConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(PopParmConf popParmConf) throws DAOException {
		try {
			getSqlMapClientTemplate().update("PopParmConf.update",
					popParmConf);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public int getMaxId() throws DAOException {
		Integer maxId = 0;
		try{
			maxId = (Integer) getSqlMapClientTemplate().queryForObject(
					"PopParmConf.getMaxId");
			if( maxId == null ){
				maxId = 0;
			}
		}catch(Exception e){
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return maxId;
	}

	public Map queryAjax(Connection conn, String sql,Map map) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int size = 0;
		try{
			ps = conn.prepareStatement(sql);
			System.out.println("***************sql£º\n"+sql);
			rs = ps.executeQuery();
			int len = rs.getMetaData().getColumnCount();
			while( rs.next() ){
				size++;
				for( int i=0;i<len;i++ ){
					map.put(rs.getMetaData().getColumnName(i+1).toLowerCase(), rs.getObject(i+1));
				}
			}
			map.put("size", size);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public int getCountPop(Connection conn, String sql) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int size = 0;
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ){
				size = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
}