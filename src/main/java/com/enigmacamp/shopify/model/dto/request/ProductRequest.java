package com.enigmacamp.shopify.model.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductRequest {
    private String id;
    private String name;
    private Long price;
    private Integer stock;
}
