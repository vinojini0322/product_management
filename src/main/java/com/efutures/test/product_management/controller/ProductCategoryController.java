package com.efutures.test.product_management.controller;

import com.efutures.test.product_management.dto.CreateCategoryDto;
import com.efutures.test.product_management.entity.ProductCategory;
import com.efutures.test.product_management.response.ApiResponse;
import com.efutures.test.product_management.service.ProductCategoryService;
import com.efutures.test.product_management.utility.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping(Constants.API + Constants.V1 + Constants.PRODUCT_CATEGORY)
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/getCategoryById")
    public ResponseEntity<?> getCategoryById(@RequestParam Long id) {
        ProductCategory productCategory = productCategoryService.getCategoryById(id);
        return new ResponseEntity<>(ApiResponse.success(productCategory), HttpStatus.OK);
    }
    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        ProductCategory productCategory = productCategoryService.createCategory(createCategoryDto);
        return new ResponseEntity<>(ApiResponse.success(productCategory), HttpStatus.OK);
    }

}
