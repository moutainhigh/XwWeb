package app.creditapp.wkf.identify;

import java.io.Serializable;

public class WFUserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected int id;
	protected WFUser user;
	protected WFRole role;

	public WFUserRole() {
	}
	
	public WFUser getUser() {
		return user;
	}

	public void setUser(WFUser user) {
		this.user = user;
	}

	public WFRole getRole() {
		return role;
	}

	public void setRole(WFRole role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
