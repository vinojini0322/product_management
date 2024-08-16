package com.efutures.test.product_management.service;

import com.efutures.test.product_management.dto.CreateCategoryDto;
import com.efutures.test.product_management.entity.ProductCategory;

import java.util.Optional;

public interface ProductCategoryService {
    ProductCategory getCategoryById(Long id);

    ProductCategory createCategory(CreateCategoryDto createCategoryDto);
}
