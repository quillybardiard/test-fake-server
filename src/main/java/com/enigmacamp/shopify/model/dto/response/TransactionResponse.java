package com.enigmacamp.shopify.model.dto.response;

import com.enigmacamp.shopify.model.entity.Customer;
import com.enigmacamp.shopify.model.entity.TransactionDetail;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    private String id;
    private Customer customer;
    private Date transactionDate;
    private List<TransactionDetail> transactionDetails;
    private Long totalPayment;
}
