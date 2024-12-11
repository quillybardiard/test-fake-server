package com.enigmacamp.shopify.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtClaims {
    private String userAccountId;
    private List<String> roles;
}
