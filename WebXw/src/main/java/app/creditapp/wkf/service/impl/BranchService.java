package app.creditapp.wkf.service.impl;

import java.util.List;

import com.dhcc.workflow.identity.app.IAppBranch;
import com.dhcc.workflow.identity.dao.IBranchDAO;
import com.dhcc.workflow.identity.service.interfaces.IBranchService;


/**
 * for flex query branch
 *
 */
public class BranchService implements IBranchService {

	private IBranchDAO branchDAO;
	
	public IAppBranch getById(String dataSource,String branchId) throws Exception {
		IAppBranch branch = null;
		try {
			branch =  branchDAO.getById(branchId);
			
		} catch(Exception e) {
			throw e;
		}
		return branch;
	}
	
	public String list(String dataSource,String branchId, String branchName) throws Exception {
		String branchXML = "";
		try {
			List<IAppBranch> list =  branchDAO.list(branchId, branchName);
			branchXML = listToXML(list);
			
		} catch(Exception e) {
			throw e;
		}
		return branchXML;
	}

	public IBranchDAO getBranchDAO() {
		return branchDAO;
	}

	public void setBranchDAO(IBranchDAO branchDAO) {
		this.branchDAO = branchDAO;
	}

	public String listToXML(List<IAppBranch> list) {
		String nodeXML = "<node label=\"root\">\n";
		nodeXML = createNode(list, "-1", nodeXML);
		nodeXML = nodeXML + "</node>\n";
		return nodeXML;
	}

	public String createNode(List<IAppBranch> list, String branchId, String nodeXML) {
		for (IAppBranch branch : list) {
			
			if (branchId.equals(branch.getParentId())) {
				
				if (hasChild(list, branch.getBranchId())) {
					
					nodeXML = nodeXML + "<node label=\"" + branch.getBranchName()
							+ "\" branchId=\"" + branch.getBranchId()
							+ "\" branchName=\"" + branch.getBranchName()
							+ "\" parentId=\"" + branch.getParentId() + "\">";
					nodeXML = createNode(list, branch.getBranchId(), nodeXML);
					nodeXML = nodeXML + "</node>\n";

				} else {

					nodeXML = nodeXML + "<node label=\"" + branch.getBranchName()
							+ "\" branchId=\"" + branch.getBranchId()
							+ "\" branchName=\"" + branch.getBranchName()
							+ "\" parentId=\"" + branch.getParentId()
							+ "\"></node>";
				}
			}
		}
		return nodeXML;
	}

	private Boolean hasChild(List<IAppBranch> list, String branchId) {
		for (IAppBranch branch : list) {
			if (branchId.equals(branch.getParentId())) {
				return true;
			}
		}
		return false;
	}

	


	
}
