package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.dto.request.TransactionRequest;
import com.enigmacamp.shopify.model.dto.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
}
