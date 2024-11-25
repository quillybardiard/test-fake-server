package com.enigmacamp.shopify.controller;

import com.enigmacamp.shopify.model.dto.request.ProductRequest;
import com.enigmacamp.shopify.model.dto.response.CommonResponse;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    List<Product> products = new ArrayList<>();

    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>>
    createNewProduct(@RequestBody ProductRequest payload) {
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
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND.value())
                    .body(products);
        }

        System.out.println("Query Params: " + name);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return ResponseEntity
                        .status(HttpStatus.OK.value())
                        .body(product);
            }
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(null);
    }
}
