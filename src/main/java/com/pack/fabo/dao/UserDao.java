package com.pack.fabo.dao;

import com.pack.fabo.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
}
