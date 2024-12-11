package com.enigmacamp.shopify.model.dto.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtClaims {
    private String userAccountId;
    private List<String> roles;
}
