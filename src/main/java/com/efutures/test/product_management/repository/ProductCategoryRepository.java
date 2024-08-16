package com.efutures.test.product_management.repository;

import com.efutures.test.product_management.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    Optional<ProductCategory> findById(Long id);
}
