package com.enigmacamp.shopify.controller;

import com.enigmacamp.shopify.model.dto.request.PaymentRequest;
import com.enigmacamp.shopify.model.dto.response.CommonResponse;
import com.enigmacamp.shopify.model.dto.response.PaymentResponse;
import com.enigmacamp.shopify.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<CommonResponse<PaymentResponse>> createPayment(
            @Valid @RequestBody PaymentRequest request,
            @RequestHeader("Authorization") String token) {
        // Pastikan token tidak termasuk prefix "Bearer "
        String jwtToken = token.replace("Bearer ", "");
        PaymentResponse paymentResponse = paymentService.createPayment(request, jwtToken);

        CommonResponse<PaymentResponse> commonResponse = CommonResponse.<PaymentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Payment created successfully")
                .data(paymentResponse)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
