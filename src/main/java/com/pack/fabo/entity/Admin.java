package com.pack.fabo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
	
	@Id
	private String userName;
	private String email;
	private String adminName;
	private String phoneNumber;
	private String displayName;
	private String concatenatedRoleNames;
	
	@Override
	public String toString() {
		return "Admin [userName=" + userName + ", email=" + email + ", adminName=" + adminName + ", phoneNumber="
				+ phoneNumber + ", displayName=" + displayName + ", concatenatedRoleNames=" + concatenatedRoleNames
				+ "]";
	}

	public Admin(String userName, String email, String adminName, String phoneNumber, String displayName,
			String concatenatedRoleNames) {
		this.userName = userName;
		this.email = email;
		this.adminName = adminName;
		this.phoneNumber = phoneNumber;
		this.displayName = displayName;
		this.concatenatedRoleNames = concatenatedRoleNames;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getConcatenatedRoleNames() {
		return concatenatedRoleNames;
	}

	public void setConcatenatedRoleNames(String concatenatedRoleNames) {
		this.concatenatedRoleNames = concatenatedRoleNames;
	}

	public Admin() {
		
	}

	
	
}
