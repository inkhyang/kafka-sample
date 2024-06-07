package com.inkhyang.document.application;

import com.inkhyang.document.entity.User;

public interface IdCardGenerator {
    String generate(User user);
    User identity(String idCard);
}
