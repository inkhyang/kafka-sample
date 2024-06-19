package com.inkhyang.document.controller;

import com.inkhyang.base.dto.user.UserDto;
import com.inkhyang.document.application.service.UserService;
import com.inkhyang.document.dto.UserDtoMapper;
import com.inkhyang.document.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/{idCard}")
    public UserDto getUser(@PathVariable String idCard) {
        return userDtoMapper.toDto(userService.findUserByIdCard(idCard));
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User user = userService.saveUser(userDtoMapper.toDomain(userDto));
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
