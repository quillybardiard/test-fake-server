package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.request.ProductRequest;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.entity.Product;
import com.enigmacamp.shopify.repository.ProductRepository;
import com.enigmacamp.shopify.utils.mapper.ProductMapper;
import com.enigmacamp.shopify.service.ProductService;
import com.enigmacamp.shopify.utils.exeptions.ResourceNotFoundException;
import com.enigmacamp.shopify.utils.exeptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse getById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found", null));
        return productMapper.toResponse(product);
    }


    @Override
    public ProductResponse updatePatch(ProductRequest payload) {
        Product product = findOrThrowProduct(payload.getId());

        // Update Patch Process
        if (payload.getName() != null) product.setName(payload.getName());
        if (payload.getPrice() != null) product.setPrice(payload.getPrice());
        if (payload.getStock() != null) product.setStock(payload.getStock());

        product = productRepository.saveAndFlush(product);
        return productMapper.toResponse(product);
    }

    private Product findOrThrowProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found", null));
    }

    @Override
    public List<ProductResponse> getAll(String name) {
        // Optionally filter products by name
        if (name != null) {
            return productRepository.findProductByNameJPQL("%" + name + "%").stream().map(productMapper::toResponse).toList();
        }

        List<ProductResponse> products = productRepository.findAll().stream().map(productMapper::toResponse).toList();

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product not found!", null);
        }

        return products;
    }
}
