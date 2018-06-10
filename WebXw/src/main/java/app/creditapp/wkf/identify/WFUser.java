package app.creditapp.wkf.identify;

import java.io.Serializable;
import com.dhcc.workflow.api.identity.Branch;
import com.dhcc.workflow.api.identity.User;

public class WFUser implements Serializable, User {

	private static final long serialVersionUID = 1L;

	/**
	 * Credit System User Model
	 */
	private String id;// Ïàµ±ÓÚuserid
	private String username;
	private String displayname;
	private String email;
	
	private Branch br_no;

	public WFUser() {
	}

	public WFUser(String id, String username, String displayname) {
		this.id = id;
		this.username = username;
		this.displayname = displayname;
	}

	public WFUser(String id, String username, String displayname,
			String email) {
		this(id, username, displayname);
		this.email = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return username;
	}

	public String getUserid() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Branch getBr_no() {
		return br_no;
	}

	public void setBr_no(Branch br_no) {
		this.br_no = br_no;
	}

	public String toString() {
		return "[id:" + id + ",userid:" + username + ",name:" + displayname
				+ ",email:" + email + ",branch:" + br_no
				+ "]";
	}

}