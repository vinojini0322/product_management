package com.efutures.test.product_management.repository;

import com.efutures.test.product_management.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment,Long> {
    List<ProductComment> findByProductId(Long productId);
}
