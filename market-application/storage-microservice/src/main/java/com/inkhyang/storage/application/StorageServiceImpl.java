package com.inkhyang.storage.application;

import com.inkhyang.storage.entity.Product;
import com.inkhyang.storage.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductByArticle(String article) {
        return productRepository.findByArticle(article);
    }

    @Override
    public List<Product> getAllProductsByAdult(Boolean adult) {
        return productRepository.findAllByAdult(adult);
    }

    @Override
    public Product updateProductAmtByArticle(String article, Integer newAmt) {
        Product product = productRepository.findByArticle(article);
        product.setAvailableAmt(newAmt);
        return productRepository.save(product);
    }

    @Override
    public Product decreaseProductAmtByArticle(String article, Integer soldAmt) {
        Product product = productRepository.findByArticle(article);
        product.setAvailableAmt(product.getAvailableAmt() - soldAmt);
        product.setReservedAmt(product.getReservedAmt() + soldAmt);
        return productRepository.save(product);
    }

    @Override
    public Product updateProductPriceByArticle(String article, Double price) {
        Product product = productRepository.findByArticle(article);
        product.setPrice(price);
        return product;
    }

    @Override
    public void deleteProductByArticle(String article) {
        productRepository.deleteByArticle(article);
    }
}
