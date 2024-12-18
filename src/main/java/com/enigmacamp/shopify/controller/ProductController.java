package com.enigmacamp.shopify.controller;

import com.enigmacamp.shopify.constant.APIUrl;
import com.enigmacamp.shopify.model.dto.request.ProductRequest;
import com.enigmacamp.shopify.model.dto.response.CommonResponse;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.service.ProductService;
import com.enigmacamp.shopify.utils.exeptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(APIUrl.BASE_URL + APIUrl.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final Validator validator;

    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>>
    createNewProduct(@RequestBody ProductRequest payload) {
        
        Set<ConstraintViolation<ProductRequest>> violations = validator.validate(payload);
       if (!violations.isEmpty()) {
           throw new ValidationException(violations.iterator().next().getMessage(), null);
       }

        ProductResponse productResp = productService.create(payload);

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
    public ResponseEntity<List<ProductResponse>> getAllProduct(
            @RequestParam(required = false) String name
    ) {
        List<ProductResponse> products = productService.getAll(name);
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
    public ResponseEntity<CommonResponse<ProductResponse>> getProductById(@PathVariable String id) {
        ProductResponse product = productService.getById(id);

        return ResponseEntity.ok(CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Product found!")
                .data(product)
                .build());
    }
}
