package com.enigmacamp.shopify.model.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDetailRequest {
    private String productId;
    private Integer quantity;
}
