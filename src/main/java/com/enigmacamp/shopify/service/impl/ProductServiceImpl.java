package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.request.ProductRequest;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.entity.Product;
import com.enigmacamp.shopify.repository.ProductRepository;
import com.enigmacamp.shopify.service.ProductService;
import com.enigmacamp.shopify.utils.exeptions.ResourceNotFoundException;
import com.enigmacamp.shopify.utils.exeptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest payload) {
        Product product = Product.builder()
                .name(payload.getName())
                .price(payload.getPrice())
                .stock(payload.getStock())
                .build();

        try {
             productRepository.save(product);
        } catch (Exception e) {
            throw new ValidationException("Constrain Error", e);
        }

        return convertToProductResponse(product);
    }

    @Override
    public ProductResponse getById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found", null));
        return convertToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getAll(String name) {
        // Optionally filter products by name
        if (name != null) {
            return productRepository.findProductByNameJPQL("%" + name + "%").stream().map(this::convertToProductResponse).toList();
        }

        List<ProductResponse> products = productRepository.findAll().stream().map(this::convertToProductResponse).toList();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product not found!", null);
        }

        return products;
    }

    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
