package com.efutures.test.product_management.repository;

import com.efutures.test.product_management.entity.Product;
import com.efutures.test.product_management.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findAllByProductCategory(String productName);


}
