package app.creditapp.bat.bo;
import java.util.List;

import app.base.ServiceException;
import app.creditapp.bat.entity.RptPrdtDaily;
public interface RptPrdtDailyBo {

	
	public List<RptPrdtDaily> getList(RptPrdtDaily rptPrdtDaily) throws ServiceException;
	public int getCount(RptPrdtDaily rptPrdtDaily) throws ServiceException;
}
