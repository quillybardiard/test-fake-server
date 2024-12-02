package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.dto.request.CustomerRequest;
import com.enigmacamp.shopify.model.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(CustomerRequest request);
}
