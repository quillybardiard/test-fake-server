package com.enigmacamp.shopify.controller;

import com.enigmacamp.shopify.constant.APIUrl;
import com.enigmacamp.shopify.model.dto.request.CustomerRequest;
import com.enigmacamp.shopify.model.dto.response.CommonResponse;
import com.enigmacamp.shopify.model.dto.response.RegisterResponse;
import com.enigmacamp.shopify.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrl.BASE_URL + APIUrl.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/customer")
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomer(@RequestBody CustomerRequest request) {
        RegisterResponse response = authService.registerCustomer(request);

        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfuly register new customer")
                .data(response)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commonResponse);
    }

}
