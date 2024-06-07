package com.inkhyang.user.controller;

import com.inkhyang.base.dto.UserDto;
import com.inkhyang.user.application.service.UserService;
import com.inkhyang.user.dto.UserDtoMapper;
import com.inkhyang.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDtoMapper.toDomain(userDto));
        return userDtoMapper.toDto(user);
    }
}
