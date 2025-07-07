package com.capstone.tvshowtracker.service;

import com.capstone.tvshowtracker.dao.UserDao;
import com.capstone.tvshowtracker.model.User;
import com.capstone.tvshowtracker.exception.AuthenticationException;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) throws AuthenticationException {
        User user = userDao.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new AuthenticationException("Invalid username or password.");
        }
        return user;
    }

    public boolean register(User user) throws AuthenticationException {
        if (userDao.findByUsername(user.getUsername()) != null) {
            throw new AuthenticationException("Username already exists.");
        }
        return userDao.createUser(user);
    }
} 