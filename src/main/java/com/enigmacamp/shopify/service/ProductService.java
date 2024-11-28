package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.dto.request.ProductRequest;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest payload);
    ProductResponse getById(String id);
    List<ProductResponse> getAll();

}
