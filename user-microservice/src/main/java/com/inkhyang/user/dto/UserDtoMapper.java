package com.inkhyang.user.dto;

import com.inkhyang.base.dto.UserDto;
import com.inkhyang.user.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
    User toDomain(UserDto userDto);
    UserDto toDto(User user);
}
