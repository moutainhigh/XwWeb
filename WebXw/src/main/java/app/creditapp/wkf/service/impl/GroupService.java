package app.creditapp.wkf.service.impl;

import java.util.List;

import com.dhcc.workflow.identity.dao.IGroupDAO;
import com.dhcc.workflow.identity.service.interfaces.IGroupService;


/**
 * for flex query group
 *
 */
public class GroupService implements IGroupService {

	private IGroupDAO groupDAO;
	
	public List<String> list(String dataSource,String branchId, String roleId, String roleName) throws Exception {
		try {
			return groupDAO.list(branchId, roleId, roleName);
			
		} catch(Exception e) {
			throw e;
		}
	}

	public IGroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(IGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

}
