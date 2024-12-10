package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.dto.request.AuthRequest;
import com.enigmacamp.shopify.model.dto.request.CustomerRequest;
import com.enigmacamp.shopify.model.dto.response.LoginResponse;
import com.enigmacamp.shopify.model.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(CustomerRequest request);
    LoginResponse login(AuthRequest request);
}
