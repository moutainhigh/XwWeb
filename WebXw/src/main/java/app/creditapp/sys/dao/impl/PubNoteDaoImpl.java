package  app.creditapp.sys.dao.impl;

import java.util.List;
import app.base.BaseIbatisDao;
import app.base.DAOException;
import app.creditapp.sys.dao.PubNoteDao;
import app.creditapp.sys.entity.PubNote;
/**
* Title: PubNoteDaoImpl.java
* Description:
**/
public class PubNoteDaoImpl extends BaseIbatisDao implements PubNoteDao {

	public void del(PubNote pubNote) throws DAOException {
		try {
			getSqlMapClientTemplate().delete("PubNote.del", pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	public List<PubNote> findByPage(PubNote pubNote) throws DAOException {
		List pubNoteList = null;
		try {
			pubNoteList = getSqlMapClientTemplate().queryForList(
					"PubNote.findByPage", pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return pubNoteList;
	}

	public PubNote getById(PubNote pubNote) throws DAOException {
		try {
			pubNote = (PubNote) getSqlMapClientTemplate()
					.queryForObject("PubNote.getById", pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return pubNote;
	}

	public int getCount(PubNote pubNote) throws DAOException {
		int count = 0;
		try {
			count = (Integer) getSqlMapClientTemplate().queryForObject(
					"PubNote.findPage.count", pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return count;
	}

	public void insert(PubNote pubNote) throws DAOException {
		try {
			getSqlMapClientTemplate().insert("PubNote.insert",
					pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}

	}

	public void update(PubNote pubNote) throws DAOException {
		try {
			getSqlMapClientTemplate().update("PubNote.update",
					pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<PubNote> getTop10(PubNote pubNote) throws DAOException {
		List<PubNote> pubNoteList = null;
		try {
			pubNoteList = (List<PubNote>)getSqlMapClientTemplate().queryForList(
					"PubNote.getTop10", pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return pubNoteList;
	}

	public List<PubNote> findAllByNote_type(PubNote pubNote) throws DAOException {
		List<PubNote> pubNoteList = null;
		try {
			pubNoteList = (List<PubNote>)getSqlMapClientTemplate().queryForList(
					"PubNote.findAllByNote_type", pubNote);
		} catch (Exception e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
		return pubNoteList;
	}
}