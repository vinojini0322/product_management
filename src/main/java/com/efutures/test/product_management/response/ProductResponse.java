package com.efutures.test.product_management.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String description;

    private BigDecimal price;

    private Character status;
    private LocalDateTime launchDate;
}
