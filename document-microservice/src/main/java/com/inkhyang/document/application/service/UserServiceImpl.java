package com.inkhyang.document.application.service;


import com.inkhyang.document.application.SimpleIdCardGenerator;
import com.inkhyang.document.application.exception.PersonNotFoundException;
import com.inkhyang.document.entity.User;
import com.inkhyang.document.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final SimpleIdCardGenerator simpleIdCardGenerator;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User saveUser(User user) {
        User finishedUser = this.grantIdCard(user);
        return userRepository.save(finishedUser);
    }

    @Override
    public User findUserByIdCard(String idCard) {
        return this.validatePerson(idCard);
    }

    @Override
    public User grantIdCard(User user) {
        String idCard = simpleIdCardGenerator.generate(user);
        user.setIdCard(idCard);
        return user;
    }

    private User validatePerson(String idCard) {
        return userRepository.findPersonByIdCard(idCard).orElseThrow(
                () -> new PersonNotFoundException(idCard));
    }
}
