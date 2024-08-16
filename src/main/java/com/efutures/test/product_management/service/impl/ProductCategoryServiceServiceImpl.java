package com.efutures.test.product_management.service.impl;

import com.efutures.test.product_management.dto.CreateCategoryDto;
import com.efutures.test.product_management.entity.ProductCategory;
import com.efutures.test.product_management.repository.ProductCategoryRepository;
import com.efutures.test.product_management.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory getCategoryById(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product category not found with ID " + id));
    }

    @Override
    public ProductCategory createCategory(CreateCategoryDto createCategoryDto) {
        if (createCategoryDto.getName() == null || createCategoryDto.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name cannot be null or empty");
        }
        try {
            ProductCategory productCategory = ProductCategory.builder()
                    .name(createCategoryDto.getName())
                    .description(createCategoryDto.getDescription())
                    .build();
            return productCategoryRepository.save(productCategory);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create category", exception);
        }
    }
}
