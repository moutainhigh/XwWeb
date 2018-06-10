package app.creditapp.bat.dao;

import java.util.List;
import app.base.DAOException;
import app.creditapp.bat.entity.RptPrdtDaily;

public interface RptPrdtDailyDao {

	
	public List<RptPrdtDaily> getList(RptPrdtDaily rptPrdtDaily) throws DAOException;
	public int getCount(RptPrdtDaily rptPrdtDaily) throws DAOException;
}
