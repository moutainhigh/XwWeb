package app.creditapp.ln.bo;

import app.base.ServiceException;
import app.creditapp.ln.entity.DebitCount;
import app.util.toolkit.Ipage;

public interface DebitCountBo {
	public Ipage findByPage(Ipage ipg, DebitCount debitCount) throws ServiceException;

}
