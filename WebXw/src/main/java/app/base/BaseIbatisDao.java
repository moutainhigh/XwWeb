package app.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * dao基类
 *
 * @see 
 * 修改记录:
 */
public abstract class BaseIbatisDao extends SqlMapClientDaoSupport {
    protected final Log log = LogFactory.getLog(getClass());
    
    protected static final int BATCH_COMMIT_NUM = 50;
    
    public void log(Object message) throws DAOException {
		if (log.isDebugEnabled()) {
			log.debug(message);
		}
	}
	

}
