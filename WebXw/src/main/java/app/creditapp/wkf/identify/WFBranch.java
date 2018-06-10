package app.creditapp.wkf.identify;

import com.dhcc.workflow.api.identity.Branch;
import com.dhcc.workflow.api.identity.Level;

public class WFBranch implements Branch,Level {
	
	private String id;
	private String name;
	private String upone;
	private String uptwo;
	private String upthree;
	private String upfour;
	private String upfive;
	private String level;

	public WFBranch() {
	}
	
	public WFBranch(String id, String name, String upone, String uptwo) {
		this.id = id;
		this.name = name;
		this.upone = upone;
//		this.uptwo = uptwo;
	}
	
	public WFBranch(String id, String name, String upone, String uptwo, String upthree) {
		this.id = id;
		this.name = name;
		this.upone = upone;
//		this.uptwo = uptwo;
//		this.upthree = upthree;
	}
	
	public WFBranch(String id, String name, String upone, String uptwo, String upthree, String upfour) {
		this.id = id;
		this.name = name;
		this.upone = upone;
//		this.uptwo = uptwo;
//		this.upthree = upthree;
//		this.upfour = upfour;
	}
	
	public WFBranch(String id, String name, String upone, String uptwo, String upthree, String upfour, String upfive) {
		this.id = id;
		this.name = name;
		this.upone = upone;
//		this.uptwo = uptwo;
//		this.upthree = upthree;
//		this.upfour = upfour;
//		this.upfive = upfive;
	}
	
	public String getId() {
		return id;
	}

	public String getUpOneBranch() {
		return upone;
	}

	public String getUpTwoBranch() {
		return uptwo;
	}

	public String getUpThreeBranch() {
		return upthree;
	}

	public String getUpFourBranch() {
		return upfour;
	}

	public String getUpFiveBranch() {
		return upfive;
	}

	public void setUpone(String upone) {
		this.upone = upone;
	}

	public void setUptwo(String uptwo) {
		this.uptwo = uptwo;
	}

	public void setUpthree(String upthree) {
		this.upthree = upthree;
	}

	public void setUpfour(String upfour) {
		this.upfour = upfour;
	}

	public void setUpfive(String upfive) {
		this.upfive = upfive;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level){
		this.level=level;
	}
}
