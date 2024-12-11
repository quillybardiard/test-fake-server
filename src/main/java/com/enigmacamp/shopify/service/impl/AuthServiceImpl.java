package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.request.AuthRequest;
import com.enigmacamp.shopify.model.dto.request.CustomerRequest;
import com.enigmacamp.shopify.model.dto.response.LoginResponse;
import com.enigmacamp.shopify.model.dto.response.RegisterResponse;
import com.enigmacamp.shopify.model.entity.Customer;
import com.enigmacamp.shopify.model.entity.UserAccount;
import com.enigmacamp.shopify.service.AuthService;
import com.enigmacamp.shopify.service.CustomerService;
import com.enigmacamp.shopify.service.JwtService;
import com.enigmacamp.shopify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

   private final CustomerService customerService;
   private final UserService userService;
   private final AuthenticationManager authenticationManager;
   private final PasswordEncoder passwordEncoder;
   private final JwtService jwtService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse registerCustomer(CustomerRequest request) {
        // TODO: insert user account
        UserAccount account = UserAccount.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
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

    @Override
    public LoginResponse login(AuthRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Set authentication to context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate Token
        UserAccount user = (UserAccount) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return LoginResponse.builder()
                .username(request.getUsername())
                .token(token)
                .role(user.getRole())
                .build();
    }
}
