package com.inkhyang.storage.repository;

import com.inkhyang.storage.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByArticle(String article);

    void deleteByArticle(String article);

    List<Product> findAllByAdult(Boolean adult);
}
