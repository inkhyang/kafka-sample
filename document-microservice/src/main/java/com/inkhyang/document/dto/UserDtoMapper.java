package com.inkhyang.document.dto;

import com.inkhyang.base.dto.UserDto;
import com.inkhyang.document.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
    User toDomain(UserDto userDto);
    UserDto toDto(User user);
}
