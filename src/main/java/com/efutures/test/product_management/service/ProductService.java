package com.efutures.test.product_management.service;

import com.efutures.test.product_management.dto.CreateProductDto;
import com.efutures.test.product_management.dto.ProductDto;
import com.efutures.test.product_management.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductDto product);
    Product updateProduct(ProductDto productDto);
    List<ProductDto> getProductsByCategory(String categoryName );
    List<ProductDto> getAllProductsByPrice(BigDecimal price);
    Long deleteProduct(Long id);

}
