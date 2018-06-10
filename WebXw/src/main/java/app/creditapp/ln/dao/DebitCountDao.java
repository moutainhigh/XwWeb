package app.creditapp.ln.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.ln.entity.DebitCount;

public interface DebitCountDao {
	
	public int getCount(DebitCount debitCount) throws DAOException;
	
	public List<DebitCount > findByPage(DebitCount debitCount) throws DAOException;

}
