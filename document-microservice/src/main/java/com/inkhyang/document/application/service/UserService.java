package com.inkhyang.document.application.service;

import com.inkhyang.document.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User saveUser(User user);

    User findUserByIdCard(String idCard);

    User grantIdCard(User user);
}
