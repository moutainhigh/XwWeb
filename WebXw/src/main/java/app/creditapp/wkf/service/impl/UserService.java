package app.creditapp.wkf.service.impl;

import java.util.List;

import com.dhcc.workflow.identity.dao.IUserDAO;
import com.dhcc.workflow.identity.service.interfaces.IUserService;


/**
 * for flex query user
 *
 */
public class UserService implements IUserService {
	
	private IUserDAO userDAO;
	
	public List<String> list(String dataSource,String branchId, String userId, String userName) throws Exception {
		try {
			return userDAO.list(branchId, userId, userName);
			
		} catch(Exception e) {
			throw e;
		}
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
