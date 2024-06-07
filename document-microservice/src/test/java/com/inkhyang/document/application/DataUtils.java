package com.inkhyang.document.application;

import com.inkhyang.document.entity.User;

public class DataUtils {

    public User createVasyaTransient() {
        return User.builder()
                .name("Vasya")
                .age(11)
                .build();
    }

    public User createPetyaTransient() {
        return User.builder()
                .name("Petya")
                .age(111)
                .build();
    }
}
