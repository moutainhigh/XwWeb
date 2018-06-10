package app.creditapp.ln.bo;

import app.base.ServiceException;
import app.creditapp.ln.entity.LoanCount;
import app.util.toolkit.Ipage;

public interface LoanCountBo {
	public Ipage findByPage(Ipage ipg, LoanCount loanCount) throws ServiceException;

}
