package  app.creditapp.corp.dao.impl;

import java.util.List;

import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.corp.dao.CorpBaseDao;
import app.creditapp.corp.entity.CorpBase;
/**
* Title: CorpBaseDaoImpl.java
* Description:
**/
public class CorpBaseDaoImpl extends BaseIbatisDao implements CorpBaseDao {

	public void del(CorpBase corpBase) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("CorpBase.delcont", corpBase);
			getSqlMapClientTemplate().delete("CorpBase.del", corpBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<CorpBase> findByPage(CorpBase corpBase) throws DAOException {
		List corpBaseList = null;
		try {
			corpBaseList = getSqlMapClientTemplate().queryForList(
					"CorpBase.findByPage", corpBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpBaseList;
	}

	public CorpBase getById(CorpBase corpBase) throws DAOException {
		try {
			corpBase = (CorpBase) getSqlMapClientTemplate()
					.queryForObject("CorpBase.getById", corpBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpBase;
	}

	public int getCount(CorpBase corpBase) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"CorpBase.findPage.count", corpBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(CorpBase corpBase) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("CorpBase.insert",
					corpBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(CorpBase corpBase) throws DAOException {
		try {
			getSqlMapClientTemplate().update("CorpBase.update",
					corpBase);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String getKey() throws DAOException {
		String corpSeq = "";
		try {
			corpSeq = (String) getSqlMapClientTemplate()
					.queryForObject("CorpBase.getKey");
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpSeq;
	}
	//增加合作机构 通用方法 判断信息 是否填写完整
	public int getAllInf(String brNo) throws DAOException {
		//一共存在的数据
		int count = 0;
		//配置信息 存在数据
		int count1 = 0;
		//联系人 存在数据
		int count2 = 0;
		//账户信息 存在数据
		int count4 = 0;
		CorpBase corpBase = new CorpBase();
		corpBase.setBrNo(brNo);
		try {
			count1 = (Integer) getSqlMapClientTemplate().queryForObject("CorpParm.findPage.count", corpBase);
			count2 = (Integer) getSqlMapClientTemplate().queryForObject("CorpCont.findPage.count", corpBase);
			count4 = (Integer) getSqlMapClientTemplate().queryForObject("CorpAcct.findPage.count", corpBase);
			if(count1>=1){
				count1 = 1;
			}else{
				count1 = 0;
			}
			if(count2>=1){
				count2 = 1;
			}else{
				count2 = 0;
			}
			if(count4>=1){
				count4 = 1;
			}else{
				count4 = 0;
			}
			count = count1+count2+count4;
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	@Override
	public CorpBase getByBrNo(String brNo) {
		CorpBase corpBase = null;
		try {
			corpBase = (CorpBase) getSqlMapClientTemplate()
					.queryForObject("CorpBase.getByBrNo",brNo);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return corpBase;
	}
	
	
	
	
	
	
	
	
}