package com.student.dto;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private List grantedAccessList;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List getGrantedAccessList() {
		return grantedAccessList;
	}

	public void setGrantedAccessList(List grantedAccessList) {
		this.grantedAccessList = grantedAccessList;
	}
	
	
}
