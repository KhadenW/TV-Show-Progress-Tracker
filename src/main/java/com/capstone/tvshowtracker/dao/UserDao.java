package com.capstone.tvshowtracker.dao;

import com.capstone.tvshowtracker.model.User;

public interface UserDao {
    User findByUsername(String username);
    boolean createUser(User user);
    // Add more methods as needed
} 