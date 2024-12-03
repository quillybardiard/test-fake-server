package com.enigmacamp.shopify.controller;

import com.enigmacamp.shopify.model.dto.request.TransactionRequest;
import com.enigmacamp.shopify.model.dto.response.CommonResponse;
import com.enigmacamp.shopify.model.dto.response.TransactionResponse;
import com.enigmacamp.shopify.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/purchases")
public class PurchaseController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<CommonResponse<TransactionResponse>> createPurchase(@RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.create(request);

        CommonResponse<TransactionResponse> commonResponse = CommonResponse.<TransactionResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfuly create new transaction")
                .data(response)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }
}
