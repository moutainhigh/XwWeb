package app.creditapp.acc.cancel.bo;

import accounting.domain.sys.AfferentDomain;
import app.base.ServiceException;

public interface CancelBo {

	//³åÕý½»Ò×
	public String reverseTrace(AfferentDomain afferentDomain)throws ServiceException ;
}
