package com.enigmacamp.shopify.model.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {
    private String username;
    private String password;
    @JsonAlias("full_name")
    private String name;
    private String address;
    private String email;
    @JsonAlias("phone_number")
    private String phoneNumber;

}
