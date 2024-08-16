package com.efutures.test.product_management.entity;

import com.efutures.test.product_management.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @CreatedDate
    @Column(name = "launch_date" ,nullable = false, updatable = false)
    private LocalDateTime launchDate;

    @ManyToOne
    @JoinColumn(name = "category_id") // Corrected to link to ProductCategory
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    private List<ProductComment> productComments;
}
