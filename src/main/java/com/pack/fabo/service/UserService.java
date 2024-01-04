package com.pack.fabo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pack.fabo.entity.User;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

}