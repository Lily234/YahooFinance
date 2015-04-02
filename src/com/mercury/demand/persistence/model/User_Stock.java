package com.mercury.demand.persistence.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class User_Stock implements java.io.Serializable {
	
	private static final long serialVersionUID = 1078875456278926797L;

	@Column(name="username")
	private String username;
	
	@Column(name="companyname")
	private String companyname;
	
	public User_Stock(){}

	public User_Stock(String username, String companyname) {
		super();
		this.username = username;
		this.companyname = companyname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	
	

}
