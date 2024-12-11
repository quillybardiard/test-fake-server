package com.enigmacamp.shopify.service;

import com.enigmacamp.shopify.model.dto.response.JwtClaims;
import com.enigmacamp.shopify.model.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    boolean verifyJwtToken(String token);
    JwtClaims getJwtClaims(String token);
}
