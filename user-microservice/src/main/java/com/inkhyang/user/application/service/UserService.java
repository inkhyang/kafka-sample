package com.inkhyang.user.application.service;

import com.inkhyang.user.entity.User;

public interface UserService {
    User createUser(User user);
    User findUserById(String passport);
}
