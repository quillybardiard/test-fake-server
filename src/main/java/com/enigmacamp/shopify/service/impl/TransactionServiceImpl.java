package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.request.TransactionDetailRequest;
import com.enigmacamp.shopify.model.dto.request.TransactionRequest;
import com.enigmacamp.shopify.model.dto.response.TransactionResponse;
import com.enigmacamp.shopify.model.entity.Payment;
import com.enigmacamp.shopify.model.entity.Transaction;
import com.enigmacamp.shopify.repository.TransactionRepository;
import com.enigmacamp.shopify.service.ProductService;
import com.enigmacamp.shopify.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductService productService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse create(TransactionRequest request) {
        // TODO: 1. Create transaction
        Transaction transaction = createTransaction(request);

        // TODO: 2. Update product stock
        updateProductStock(request.getTransactionDetailRequests());

        // TODO: 3. Create payment record
        Payment payment = createPayment(transaction);

        // All operations are atomic - will rollback if any fails
        return convertToResponse(transaction);
    }

    private Transaction createTransaction(TransactionRequest request) {
        System.out.println("Your Transaction is Created!");
        Transaction transaction = Transaction.builder()
                .transactionDate(new Date())
                .build();
//        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    private void updateProductStock(List<TransactionDetailRequest> transactionDetailRequests) {
        throw new RuntimeException("Error: Failed to update product stock");
//        System.out.println("Your Stock is Updated!");
    }

    private Payment createPayment(Transaction transaction) {
        System.out.println("Your Payment is Created!");
        return Payment.builder().build();
    }

    private TransactionResponse convertToResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .customer(transaction.getCustomer())
                .transactionDetails(transaction.getTransactionDetails())
                .totalPayment(8000000L)
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
