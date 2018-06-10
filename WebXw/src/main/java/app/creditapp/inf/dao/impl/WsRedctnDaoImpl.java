package  app.creditapp.inf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.inf.dao.WsRedctnDao;
import app.creditapp.inf.entity.WsOut2902_1;
import app.creditapp.inf.entity.WsRedctn;
/**
* Title: WsRedctnDaoImpl.java
* Description:
**/
public class WsRedctnDaoImpl extends BaseIbatisDao implements WsRedctnDao {

	public void del(WsRedctn wsRedctn) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("WsRedctn.del", wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<WsRedctn> findByPage(WsRedctn wsRedctn) throws DAOException {
		List wsRedctnList = null;
		try {
			wsRedctnList = getSqlMapClientTemplate().queryForList(
					"WsRedctn.findByPage", wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRedctnList;
	}

	public WsRedctn getById(WsRedctn wsRedctn) throws DAOException {
		try {
			wsRedctn = (WsRedctn) getSqlMapClientTemplate()
					.queryForObject("WsRedctn.getById", wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsRedctn;
	}

	public int getCount(WsRedctn wsRedctn) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WsRedctn.findPage.count", wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(WsRedctn wsRedctn) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("WsRedctn.insert",
					wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(WsRedctn wsRedctn) throws DAOException {
		try {
			getSqlMapClientTemplate().update("WsRedctn.update",
					wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
	
	//接口ws2902符合条件返回列表
	public List<WsOut2902_1> findByPageforws2902(WsRedctn wsRedctn) throws DAOException
	{
		List<WsOut2902_1> wsOut2902_1list = new ArrayList<WsOut2902_1>();
		try {
			wsOut2902_1list = getSqlMapClientTemplate().queryForList(
					"WsRedctn.findByPageforws2902", wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return wsOut2902_1list;
	}
	//接口ws2902符合条件返回个数
	public int getCountforws2902(WsRedctn wsRedctn) throws DAOException{
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"WsRedctn.findPage.countforws2902", wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	//接口 ws2901删除操作
	public void deleteforws2901(WsRedctn wsRedctn) throws DAOException{
		try {
			getSqlMapClientTemplate().delete("WsRedctnforws2901.del", wsRedctn);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	
}