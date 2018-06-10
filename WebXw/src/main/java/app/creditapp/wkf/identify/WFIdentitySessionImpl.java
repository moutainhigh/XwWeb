package app.creditapp.wkf.identify;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dhcc.workflow.api.WorkflowException;
import com.dhcc.workflow.api.identity.Branch;
import com.dhcc.workflow.api.identity.Group;
import com.dhcc.workflow.api.identity.User;
import com.dhcc.workflow.pvm.internal.cache.BranchCache;
import com.dhcc.workflow.pvm.internal.cache.UserCache;
import com.dhcc.workflow.pvm.internal.identity.impl.IdentitySessionImpl;
import com.dhcc.workflow.pvm.internal.util.CollectionUtil;
import com.dhcc.workflow.pvm.internal.util.StringUtil;

public class WFIdentitySessionImpl extends IdentitySessionImpl {
	
	public WFIdentitySessionImpl() {
		super.setFirstResult(0);
		super.setMaxResult(50);
	}
	
	public User findUserById(String userId) {
		UserCache userCache = UserCache.getCache();
		User user = userCache.get(userId);
		if( user == null ) {
			user = (User) session.createCriteria(WFUser.class).add(Restrictions.eq("id", userId)).uniqueResult();
			userCache.put(userId, user);
		}
		return user;
	}

	public List<User> findUsersById(String... userIds) {
		Criteria criterias = session.createCriteria(WFUser.class).add(Restrictions.in("id", userIds));
		criterias.setFirstResult(getFirstResult());
		criterias.setMaxResults(getMaxResult());
		
		List<?> users = criterias.list();
		if (userIds.length != users.size()) {
			throw new WorkflowException("not all users were found: "
					+ Arrays.toString(userIds));
		}
		return CollectionUtil.checkList(users, User.class);
	}

	public List<User> findUsers() {
		Criteria criterias = session.createCriteria(WFUser.class);
		criterias.setFirstResult(getFirstResult());
		criterias.setMaxResults(getMaxResult());
		return CollectionUtil.checkList(criterias.list(), User.class);
	}

	public List<User> findUsersByGroup(String roleId) {
		Criteria criterias = session.createCriteria(WFUserRole.class).createAlias(
							"role", "g").add(Restrictions.eq("g.id", roleId)).setProjection(
						Projections.property("user"));
		criterias.setFirstResult(getFirstResult());
		criterias.setMaxResults(getMaxResult());
		return CollectionUtil.checkList(criterias.list(), User.class);
	}

	public WFRole findGroupById(String roleId) {
		return (WFRole) session.createCriteria(WFRole.class).add(
				Restrictions.eq("id", roleId)).uniqueResult();
	}

	public List<Group> findGroupsByUser(String userid) {
		Query query = session.getNamedQuery("findGroupsByUserId").setParameter("userId", userid);
		query.setFirstResult(getFirstResult());
		query.setMaxResults(getMaxResult());
		return CollectionUtil.checkList(query.list(), Group.class);
	}

	public List<Group> findGroups() {
		Criteria criterias = session.createCriteria(WFRole.class);
		criterias.setFirstResult(getFirstResult());
		criterias.setMaxResults(getMaxResult());
		return CollectionUtil.checkList(criterias.list(), Group.class);
	}
	
	public List<User> findUsersByCondition(String userId, String userName) {
		Criteria criterias = session.createCriteria(WFUser.class);
		if (!StringUtil.isEmpty(userId)) { 
			criterias.add(Restrictions.eq("id", userId)); 
		}
		if (!StringUtil.isEmpty(userName)) { 
			criterias.add(Restrictions.like("name", userName, MatchMode.ANYWHERE)); 
		}
		criterias.setFirstResult(getFirstResult());
		criterias.setMaxResults(getMaxResult());
		return CollectionUtil.checkList(criterias.list(), User.class);
	}

	public Branch findBranchByUserId(String userid) {
		List list = session.getNamedQuery("findBranchByUserId").setParameter(
				"userId", userid).list();
		if (list != null && list.size() > 0) {
			return (WFBranch) list.get(0);
		}
		return new WFBranch("00000", "Ä¬ÈÏ»ú¹¹", "-1", "-1");
	}
	
	public Branch findBranchByBranchId(String branchId) {
		BranchCache branchCache = BranchCache.getCache();
		Branch branch = branchCache.get(branchId);
		if( branch == null ) {
			branch = (Branch) session.createCriteria(WFBranch.class).add(Restrictions.eq("id", branchId)).uniqueResult();
			branchCache.put(branchId, branch);
		}
		return branch;
	}
	@Override
	public Branch findBranchById(String branchId) {
		Branch branch = BranchCache.getCache().get(branchId);
		if( branch != null ) {
			return branch;
		}	
		
		branch =  (Branch) session.createCriteria(WFBranch.class).add(Restrictions.eq("id", branchId)).uniqueResult();
		if( branch != null ) {
			BranchCache.getCache().put(branchId, branch);
		}
		return branch;
	}
}
