package com.pack.fabo.service;

import java.util.List;

import com.pack.fabo.entity.Admin;

public interface AdminService {
	
	List<Admin> getAllAdmins();

	Admin saveAdmin(Admin admin);

	Admin getAdminById(String userName);

	Admin updateAdmin(Admin Admin);

	void deleteAdminById(String email);

	void addAdminAndRoles(Admin admin, List<Long> roleIds);

	void removeAdminAndAssociationsByEmail(String email);

	void updateConcatenatedRolesByEmail(String email, String concatenatedRoleNames);

	boolean isUsernameDuplicate(String username);



}
