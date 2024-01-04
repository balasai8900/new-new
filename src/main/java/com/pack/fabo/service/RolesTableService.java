package com.pack.fabo.service;

import java.util.List;
import java.util.Map;

import com.pack.fabo.entity.ClientUser;

public interface RolesTableService {

	String getConcatenatedRoleNamesByEmail(String email, List<Long> roleIds);

	

}
