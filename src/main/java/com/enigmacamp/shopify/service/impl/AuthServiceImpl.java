package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.request.CustomerRequest;
import com.enigmacamp.shopify.model.dto.response.RegisterResponse;
import com.enigmacamp.shopify.model.entity.Customer;
import com.enigmacamp.shopify.model.entity.UserAccount;
import com.enigmacamp.shopify.service.AuthService;
import com.enigmacamp.shopify.service.CustomerService;
import com.enigmacamp.shopify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

   private final CustomerService customerService;
   private final UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse registerCustomer(CustomerRequest request) {
        // TODO: insert user account
        UserAccount account = UserAccount.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        account = userService.create(account);

        // TODO: insert customer
        Customer customer = Customer.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .userAccount(account)
                .createAt(Date.from(Instant.now()))
                .build();

        customerService.create(customer);

        return RegisterResponse.builder()
                .username(request.getUsername())
                .build();
    }
}
