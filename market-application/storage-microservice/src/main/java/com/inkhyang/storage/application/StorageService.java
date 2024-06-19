package com.inkhyang.storage.application;

import com.inkhyang.storage.entity.Product;

import java.util.List;

public interface StorageService {
    Product createProduct(Product product);
    Product getProductByArticle(String article);
    List<Product> getAllProductsByAdult(Boolean adult);
    Product updateProductAmtByArticle(String article, Integer newAmt);
    Product decreaseProductAmtByArticle(String article, Integer soldAmt);
    Product updateProductAdultByArticle(String article, Boolean adult);
    void deleteProductByArticle(String article);
}
