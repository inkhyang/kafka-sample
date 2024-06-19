package com.inkhyang.storage.controller;

import com.inkhyang.storage.application.StorageService;
import com.inkhyang.storage.dto.ProductDto;
import com.inkhyang.storage.dto.ProductDtoMapper;
import com.inkhyang.storage.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/products")
@AllArgsConstructor
public class StorageController {
    private final StorageService storageService;
    private final ProductDtoMapper productDtoMapper;

    @GetMapping("/{article}")
    public ProductDto getProductByArticle(@PathVariable String article) {
        Product product = storageService.getProductByArticle(article);
        return productDtoMapper.toDto(product);
    }

    @GetMapping("/adult/{adult}")
    public List<ProductDto> allByAdult(@PathVariable Boolean adult) {
        List<Product> list = storageService.getAllProductsByAdult(adult);
        return list.stream().map(productDtoMapper::toDto).toList();
    }

    @PostMapping("/new")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product savedProduct = storageService.createProduct(
                productDtoMapper.toDomain(productDto)
        );
        return productDtoMapper.toDto(savedProduct);
    }

    @DeleteMapping("/{article}/del")
    public void deleteProductByArticle(@PathVariable String article) {
        storageService.deleteProductByArticle(article);
    }
}
