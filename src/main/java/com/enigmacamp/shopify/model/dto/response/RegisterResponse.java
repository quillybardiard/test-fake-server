package com.enigmacamp.shopify.model.dto.response;

import com.enigmacamp.shopify.model.entity.Role;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterResponse {
    private String username;
    private List<Role> role;
}
