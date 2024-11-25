package com.enigmacamp.shopify.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private Long price;
    private Integer stock;
}
