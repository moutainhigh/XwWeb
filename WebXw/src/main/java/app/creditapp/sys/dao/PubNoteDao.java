package  app.creditapp.sys.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.sys.entity.PubNote;
/**
* Title: PubNoteDao.java
* Description:
**/
public interface PubNoteDao {

	public PubNote getById(PubNote pubNote) throws DAOException;

	public void del(PubNote pubNote) throws DAOException;

	public void insert(PubNote pubNote) throws DAOException;

	public void update(PubNote pubNote) throws DAOException;
	
	public int getCount(PubNote pubNote) throws DAOException;

	public List<PubNote > findByPage(PubNote pubNote) throws DAOException;

	public List<PubNote> getTop10(PubNote pubNote)throws DAOException;
	
	public List<PubNote> findAllByNote_type(PubNote pubNote)throws DAOException;

}