package com.pack.fabo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.fabo.entity.User;
import com.pack.fabo.entity.UsersRoles;

public interface UserRolesRepository extends JpaRepository<UsersRoles, Long> {

	List<UsersRoles> findByUser(User user);

	void deleteByUserIn(List<User> findAllByUserName);

	List<UsersRoles> findByRoleId(Long roleId);


}
