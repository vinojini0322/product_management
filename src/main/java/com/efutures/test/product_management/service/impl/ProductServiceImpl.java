package com.efutures.test.product_management.service.impl;

import com.efutures.test.product_management.exception.ProductDeleteException;
import com.efutures.test.product_management.exception.ProductFetchException;
import com.efutures.test.product_management.exception.ProductUpdateException;
import com.efutures.test.product_management.dto.CreateProductDto;
import com.efutures.test.product_management.dto.ProductDto;
import com.efutures.test.product_management.entity.Product;
import com.efutures.test.product_management.entity.ProductCategory;
import com.efutures.test.product_management.entity.ProductComment;
import com.efutures.test.product_management.enums.ProductStatus;
import com.efutures.test.product_management.exception.ProductCreationException;
import com.efutures.test.product_management.repository.ProductCategoryRepository;
import com.efutures.test.product_management.repository.ProductCommentRepository;
import com.efutures.test.product_management.repository.ProductRepository;
import com.efutures.test.product_management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCommentRepository productCommentRepository;

    @Override
    public Product createProduct(CreateProductDto product) {

        if (product == null || product.getDescription() == null || product.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product name / price cannot be null or empty");
        }
        ProductCategory productCategory = productCategoryRepository.findById(product.getCategoryId()).orElseThrow(RuntimeException::new);
        try {
            Product createdProduct = Product.builder()
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .status(ProductStatus.C)
                    .productCategory(productCategory).build();

            return productRepository.save(createdProduct);
        } catch (Exception e) {
            throw new ProductCreationException("An error occurred while creating the product." + e);
        }
    }

    @Override
    public Product updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found with id: " + productDto.getId()));

        List<ProductComment> productComments = productCommentRepository.findByProductId(productDto.getId());

        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStatus(ProductStatus.U);
        product.setProductComments(productComments);

        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new ProductUpdateException("An error occurred while updating the product with id: "
                    + productDto.getId(), e);
        }
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryName) {
        try{
            List<Product> products = productRepository.findAllByProductCategory(categoryName);
            return mapToProductDto(products);

        }catch (Exception e){
            throw new ProductFetchException("There was some error fetching the product details",e);
        }

    }

    @Override
    public List<ProductDto> getAllProductsByPrice(BigDecimal price) {
        try{
            List<Product> products = productRepository.findByPriceGreaterThanEqual(price);
            return mapToProductDto(products);

        }catch (Exception e){
            throw new ProductFetchException("There was some error fetching the product details",e);
        }
    }

    public Long deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStatus().equals(ProductStatus.D)) {
            throw new ProductDeleteException("Product deleted already");
        }
        try {
            product.setStatus(ProductStatus.D);
           Product deletedProduct= productRepository.save(product);
            return deletedProduct.getId();
        } catch (Exception e) {
            throw new ProductDeleteException("There is a error deleting the product",e);
        }
    }
    private static List<ProductDto> mapToProductDto(List<Product> products) {
        return products.stream().map(product -> {

            return ProductDto.builder()
                    .id(product.getId())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .productCategory(product.getProductCategory())
                    .productComments(product.getProductComments())
                    .build();
        }).collect(Collectors.toList());
    }


}
