package com.enigmacamp.shopify.model.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Product {
    private String id;
    private String name;
    private Long price;
    private Integer stock;
}
