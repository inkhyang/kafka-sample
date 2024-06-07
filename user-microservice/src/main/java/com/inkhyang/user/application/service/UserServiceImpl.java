package com.inkhyang.user.application.service;

import com.inkhyang.user.entity.User;
import com.inkhyang.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(String idCard) {
        return userRepository.findUserByIdCard(idCard);
    }
}
