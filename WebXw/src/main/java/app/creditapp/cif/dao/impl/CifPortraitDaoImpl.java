package  app.creditapp.cif.dao.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.cif.dao.CifPortraitDao;
import app.creditapp.cif.entity.CifPortrait;
/**
* Title: CifPortraitDaoImpl.java
* Description:
**/
public class CifPortraitDaoImpl extends BaseIbatisDao implements CifPortraitDao {

	public void del(CifPortrait cifPortrait) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CifPortrait.del", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CifPortrait> findByPage(CifPortrait cifPortrait) throws DAOException {
		List cifPortraitList = null;
		try {
			cifPortraitList = getSqlMapClientTemplate().queryForList(
					"CifPortrait.findByPage", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPortraitList;
	}

	public CifPortrait getById(CifPortrait cifPortrait) throws DAOException {
		try {
			cifPortrait = (CifPortrait) getSqlMapClientTemplate()
					.queryForObject("CifPortrait.getById", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPortrait;
	}

	public int getCount(CifPortrait cifPortrait) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.findPage.count", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CifPortrait cifPortrait) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CifPortrait.insert",
					cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}
	/**
	 * 获取客户类型
	 */
	public void update(CifPortrait cifPortrait) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CifPortrait.update",
					cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * 同类客户百分比
	 */
	@Override
	public double getPercent(CifPortrait cifPortrait) throws DAOException {
		double count = 0;
		DecimalFormat df = new DecimalFormat("0.00");
		try {
			 count = (Double) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getPercent", cifPortrait);
			count = Double.parseDouble(df.format(count*100));
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
/**
 * 获取逾期次数
 */
	@Override
	public int getRepdNum(CifPortrait cifPortrait) throws DAOException {
		
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getRepdNum", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	@Override
	public String getWorkType(CifPortrait cifPortrait) throws DAOException {
		String WorkType;
		try {
			WorkType = (String) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getWorkType", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return WorkType;
	}
	@Override
	public String getIfRoom(CifPortrait cifPortrait) throws DAOException {
		String ifRoom;
		try {
			ifRoom = (String) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getIfRoom", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return ifRoom;
	}
	@Override
	public String getIfCar(CifPortrait cifPortrait) throws DAOException {
		String ifCar;
		try {
			ifCar = (String) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getIfCar", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return ifCar;
	}
	@Override
	public int getCifBlackNum(CifPortrait cifPortrait) throws DAOException {
		
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getCifBlackNum", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public int getIfDC(CifPortrait cifPortrait) throws DAOException {
		int DC = 0;
		try {
			DC = (Integer)getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getIfDC", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return DC;
	}

	@Override
	public int getIfHG(CifPortrait cifPortrait) throws DAOException {
		int HG = 0;
		try {
			HG = (Integer) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getIfHG", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return HG;
	}

	@Override
	public List<CifPortrait > getCifGroup(CifPortrait cifPortrait) throws DAOException {
		List CifGroupList = null;
		try {
			CifGroupList = getSqlMapClientTemplate().queryForList("CifPortrait.getCifGroup", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return CifGroupList;
	}
	/**
	 * 按版本号和客户号查询最新版本的客户画像信息
	 */
	@Override
	public CifPortrait getCifPortraitByVersion(String cifno,String version) throws DAOException {
		Map map = new HashMap();
		map.put("cifNo", cifno);
		map.put("version", version);
		CifPortrait cifPortrait = new CifPortrait();
		try {
			cifPortrait = (CifPortrait) getSqlMapClientTemplate().queryForObject(
					"CifPortrait.getVersion", map);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return cifPortrait;
	}
	@Override
	public String getMaxVersion(CifPortrait cifPortrait) throws DAOException {
		String verSion;
		try {
			verSion = (String) getSqlMapClientTemplate()
					.queryForObject("CifPortrait.getMaxVersion", cifPortrait);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return verSion;
	}
}