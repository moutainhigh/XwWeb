package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.SysUserDao;
import app.creditapp.sys.entity.SysUser;
/**
* Title: SysUserDaoImpl.java
* Description:
**/
public class SysUserDaoImpl extends BaseIbatisDao implements SysUserDao {

	public void del(SysUser SysUser) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("SysUser.del", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<SysUser> findByPage(SysUser SysUser) throws DAOException {
		List SysUserList = null;
		try {
			SysUserList = getSqlMapClientTemplate().queryForList(
					"SysUser.findByPage", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return SysUserList;
	}
	public List<SysUser> findByPageForPop(SysUser SysUser) throws DAOException {
		List SysUserList = null;
		try {
			SysUserList = getSqlMapClientTemplate().queryForList(
					"SysUser.findByPageForPop", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return SysUserList;
	}
	public List<SysUser> findMangNoPop(SysUser SysUser) throws DAOException {
		List SysUserList = null;
		try {
			SysUserList = getSqlMapClientTemplate().queryForList(
					"SysUser.findMangNoPop", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return SysUserList;
	}
	
	public SysUser getById(SysUser SysUser) throws DAOException {
		try {
			SysUser = (SysUser) getSqlMapClientTemplate()
					.queryForObject("SysUser.getById", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return SysUser;
	}

	public int getCount(SysUser SysUser) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysUser.findPage.count", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
	public int getCountForPop(SysUser SysUser) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysUser.findPageForPop.count", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}	
	public int getMangNoPopCount(SysUser SysUser) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysUser.findPage.MangNoPopCount", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(SysUser SysUser) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("SysUser.insert",
					SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(SysUser SysUser) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysUser.update",
					SysUser);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * @方法说明:
	 * @param SysUser
	 * @throws DAOException 
	 * @see app.creditapp.sys.dao.SysUserDao#updateOpSts(app.creditapp.sys.entity.SysUser)
	 * @修改说明:
	 */
	public void updateUserSts(SysUser SysUser) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysUser.updateUserSts",
					SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	//通讯录记录条数
	public int getCountForAddressBook(SysUser tou) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysUser.findPageForAddressBook.count", tou);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("查询统计失败");
		}
		return count;
	}
	//通讯录列表
	public List<SysUser> findPageForAddressBook(SysUser tou) throws DAOException {
		List<SysUser> lst = null;
		try {
			lst = getSqlMapClientTemplate().queryForList("SysUser.findPageForAddressBook", tou);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("分页查询失败");
		}
		return lst;
	}
	
	public SysUser getByLoginId(SysUser SysUser) throws DAOException {
		try {
			SysUser = (SysUser) getSqlMapClientTemplate()
					.queryForObject("SysUser.getLoginId", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return SysUser;
	}
	
	public void updateAddressBookInfo(SysUser SysUser) throws DAOException {
		try {
			getSqlMapClientTemplate().update("SysUser.updateAddressInfo",
					SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}
	/**
	 * 
		 * @方法说明:换肤
		 * @param op
		 * @throws DAOException
		 * @return  void
		 * @修改说明:
	 */

	public void updateSkin(SysUser op) throws DAOException {

		try {
			this.getSqlMapClientTemplate().update("SysUser.updateSkin", op);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException("修改用户皮肤失败");
		}
	}

	public List<SysUser> findZt(SysUser SysUser)
			throws DAOException {
		List SysUserList = null;
		try {
			SysUserList = getSqlMapClientTemplate().queryForList(
					"SysUser.findZt", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return SysUserList;
	}

	public int getCountZt(SysUser SysUser) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"SysUser.findPage.countZt", SysUser);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}
}