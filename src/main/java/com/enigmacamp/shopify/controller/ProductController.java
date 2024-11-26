package com.enigmacamp.shopify.controller;

import com.enigmacamp.shopify.model.dto.request.ProductRequest;
import com.enigmacamp.shopify.model.dto.response.CommonResponse;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.entity.Product;
import com.enigmacamp.shopify.utils.exeptions.ResourceNotFoundException;
import com.enigmacamp.shopify.utils.exeptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    List<Product> products = new ArrayList<>();

    @Autowired
    private Validator validator;

    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>>
    createNewProduct(@RequestBody ProductRequest payload) {

        //TODO: validate request
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(payload);
       if (!violations.isEmpty()) {
           throw new ValidationException(violations.iterator().next().getMessage(), null);
       }

        //TODO: change payload to product
        Product product = Product.builder()
                .id(payload.getId())
                .name(payload.getName())
                .price(payload.getPrice())
                .stock(payload.getStock())
                .build();

        //TODO: add product payload to list
        products.add(product);

        //TODO change produt to product response
        ProductResponse productResp = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

         CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("New product added!")
                        .data(productResp)
                        .build();

        return ResponseEntity
                .status(HttpStatus.CREATED.value())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(
            @RequestParam(required = false) String name
    ) {
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Product not found!", null);
        }

        // Optionally filter products by name
        if (name != null) {
            List<Product> filteredProducts = products.stream()
                    .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
            return ResponseEntity.ok(filteredProducts);
        }

        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get product by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Product>> getProductById(@PathVariable String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                CommonResponse<Product> response = CommonResponse.<Product>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Product found!")
                        .data(product)
                        .build();

                return ResponseEntity
                        .status(HttpStatus.OK.value())
                        .body(response);
            }
        }

        throw new ResourceNotFoundException("Product not found!", null);
    }
}
