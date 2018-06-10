package app.creditapp.wkf.identify;

import java.io.Serializable;

import com.dhcc.workflow.api.identity.Group;
import com.dhcc.workflow.api.identity.Level;

public class WFRole implements Group, Level,Serializable {
	
	private static final long serialVersionUID = -7306009279623972330L;
	
	private String id;
	private String name;
	private String level;
	
	public WFRole() {
	}
	
	public WFRole(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return null;
	}

	public String getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	public void setLevel(String level){
		this.level=level;
	}
}
