package app.creditapp.sec.dao.impl;

import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sec.dao.UserApptimeDao;
import app.creditapp.sec.entity.UserApptime;


/**
* Title: UserApptimeDaoImpl.java
* Description:
**/

public class UserApptimeDaoImpl extends BaseIbatisDao implements UserApptimeDao {

	public void del(long id) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("UserApptime.del", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("删除失败");
		}
	}

	public List<UserApptime> findByPage(Map map) throws DAOException {
		List userApptimeList = null;
		try {
			userApptimeList = getSqlMapClientTemplate().queryForList(
					"UserApptime.findByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return userApptimeList;
	}

	public UserApptime getById(long id) throws DAOException {
		UserApptime userApptime = null;
		try {
			userApptime = (UserApptime) getSqlMapClientTemplate()
					.queryForObject("UserApptime.getById", id);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询失败");
		}
		return userApptime;
	}

	public int getCount(UserApptime userApptime) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"UserApptime.findPage.count", userApptime);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	public int getAllCount(UserApptime userApptime) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"UserApptime.allFindPage.count", userApptime);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	public void insert(UserApptime userApptime) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("UserApptime.insert",
					userApptime);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("新增失败");
		}

	}

	public void update(UserApptime userApptime) throws DAOException {
		try {
			getSqlMapClientTemplate().update("UserApptime.update",
					userApptime);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("更新失败");
		}
	}
	
	public List<UserApptime> allFindByPage(Map map) throws DAOException {
		List userApptimeList = null;
		try {
			userApptimeList = getSqlMapClientTemplate().queryForList(
					"UserApptime.allFindByPage", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return userApptimeList;
	}
}
