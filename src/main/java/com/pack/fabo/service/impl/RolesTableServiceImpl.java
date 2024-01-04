package com.pack.fabo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pack.fabo.entity.ClientUser;
import com.pack.fabo.repository.ClientUserRepository;
import com.pack.fabo.repository.RolesTableRepository;
import com.pack.fabo.service.RolesTableService;

@Service
public class RolesTableServiceImpl implements RolesTableService{
	
	private RolesTableRepository rolesTableRepository;



public RolesTableServiceImpl(RolesTableRepository rolesTableRepository) {
		this.rolesTableRepository = rolesTableRepository;
	}


public String getConcatenatedRoleNamesByEmail(String email, List<Long> roleIds) { 
    List<String> roleNames = new ArrayList<>();
    for (Long roleId : roleIds) {
        String roleName = rolesTableRepository.findRoleNameById(roleId);
        if (roleName != null) {
            roleNames.add(roleName);
        }
    }
    return String.join(", ", roleNames);
}




/*
@Override
public List<ClientUser> getClientUsersWithConcatenatedRoleNames() {
    List<ClientUser> clientUsers = clientUserRepository.findAll(); // Fetch all client users

    // Iterate through each client user and set their concatenated role names
    for (ClientUser clientUser : clientUsers) {
        List<Long> roleIds = clientUser.getRoleIds(); // Retrieve role IDs for the client user
        String concatenatedRoleNames = getConcatenatedRoleNames(roleIds); // Assuming you have a method to generate concatenated role names
        clientUser.setConcatenatedRoleNames(concatenatedRoleNames); // Set concatenated role names to each client user
    }

    return clientUsers;
}*/

}
