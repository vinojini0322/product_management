package com.efutures.test.product_management.dto;

import com.efutures.test.product_management.entity.ProductCategory;
import com.efutures.test.product_management.entity.ProductComment;
import com.efutures.test.product_management.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {
    private Long id;

    private String description;

    private BigDecimal price;

    private ProductStatus status;

    private LocalDateTime launchDate;

    private ProductCategory productCategory;

    private List<ProductComment> productComments;
}
