package app.base;

import org.apache.log4j.Logger;

public abstract class BaseService {
	private static Logger log = Logger.getLogger(BaseService.class);

	public void log(Object message) throws ServiceException {
		if (log.isDebugEnabled()) {
			log.debug(message);
		}
	}
}
