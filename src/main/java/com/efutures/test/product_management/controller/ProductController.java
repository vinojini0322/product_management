package com.efutures.test.product_management.controller;

import com.efutures.test.product_management.dto.CreateProductDto;
import com.efutures.test.product_management.dto.ProductDto;
import com.efutures.test.product_management.entity.Product;
import com.efutures.test.product_management.response.ApiResponse;
import com.efutures.test.product_management.service.ProductService;
import com.efutures.test.product_management.utility.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping(Constants.API + Constants.V1 + Constants.PRODUCT)
public class ProductController {
    private final ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<?> createCategory(@RequestBody CreateProductDto createProductDto) {
        Product product = productService.createProduct(createProductDto);
        return new ResponseEntity<>(ApiResponse.success(product), HttpStatus.OK);
    }

    @PatchMapping("/updateProduct")
    public ResponseEntity<?> updateCategory(@RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(productDto);
        return new ResponseEntity<>(ApiResponse.success(product), HttpStatus.OK);
    }
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(@RequestBody String category) {
        List< ProductDto> products= productService.getProductsByCategory(category);
        return new ResponseEntity<>(ApiResponse.success(products), HttpStatus.OK);
    }
    @GetMapping("/getAllPremiumProducts")
    public ResponseEntity<?> getAllPremiumProducts(@RequestBody BigDecimal price) {
        List< ProductDto> products= productService.getAllProductsByPrice(price);
        return new ResponseEntity<>(ApiResponse.success(products), HttpStatus.OK);
    }
    @PatchMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody Long id) {
        Long deletedProductId= productService.deleteProduct(id);
        return new ResponseEntity<>(ApiResponse.success(deletedProductId), HttpStatus.OK);
    }


}
