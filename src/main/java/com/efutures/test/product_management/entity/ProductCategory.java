package com.efutures.test.product_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_category", indexes = {
        @Index(name = "idx_product_category_name", columnList = "name")
})
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
}

