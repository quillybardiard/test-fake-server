package com.enigmacamp.shopify.model.dto.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponse {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String createAt;
}
