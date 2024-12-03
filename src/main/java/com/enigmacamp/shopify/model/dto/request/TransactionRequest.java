package com.enigmacamp.shopify.model.dto.request;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionRequest {
    private String customerId;
    private List<TransactionDetailRequest> transactionDetailRequests;
}
