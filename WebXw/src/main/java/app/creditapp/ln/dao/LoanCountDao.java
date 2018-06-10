package app.creditapp.ln.dao;

import java.util.List;

import app.base.DAOException;
import app.creditapp.ln.entity.LoanCount;

public interface LoanCountDao {

	public int getCount(LoanCount loanCount) throws DAOException;
	
	public List<LoanCount > findByPage(LoanCount loanCount) throws DAOException;

}
