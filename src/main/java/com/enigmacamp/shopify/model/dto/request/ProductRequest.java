package com.enigmacamp.shopify.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductRequest {
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    @Min(value = 0, message = "Price must be positive")
    private Long price;
    @Min(value = 0, message = "Stock must be positive")
    private Integer stock;
}
