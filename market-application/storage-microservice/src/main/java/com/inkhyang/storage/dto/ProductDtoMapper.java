package com.inkhyang.storage.dto;

import com.inkhyang.storage.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDtoMapper {
    Product toDomain(ProductDto productDto);

    ProductDto toDto(Product product);
}
