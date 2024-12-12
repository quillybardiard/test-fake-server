package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.dto.request.PaymentRequest;
import com.enigmacamp.shopify.model.dto.response.PaymentResponse;
import com.enigmacamp.shopify.model.entity.Product;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest, String token);
    List<Product> searchProducts(String query, int page);
    public void updateProduct(String id, Product product);
    public void deleteProduct(String id);
}
