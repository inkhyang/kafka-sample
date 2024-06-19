package com.inkhyang.base.dto.order;

import com.inkhyang.base.dto.user.VerificatedUserDto;

import java.util.List;
public record OrderDto(VerificatedUserDto userDto, List<ProductDto> products) {
}
