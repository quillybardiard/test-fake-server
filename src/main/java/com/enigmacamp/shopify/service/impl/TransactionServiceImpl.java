package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.dto.request.ProductRequest;
import com.enigmacamp.shopify.model.dto.request.TransactionDetailRequest;
import com.enigmacamp.shopify.model.dto.request.TransactionRequest;
import com.enigmacamp.shopify.model.dto.response.CustomerResponse;
import com.enigmacamp.shopify.model.dto.response.ProductResponse;
import com.enigmacamp.shopify.model.dto.response.TransactionResponse;
import com.enigmacamp.shopify.model.entity.*;
import com.enigmacamp.shopify.repository.TransactionDetailRepository;
import com.enigmacamp.shopify.repository.TransactionRepository;
import com.enigmacamp.shopify.service.CustomerService;
import com.enigmacamp.shopify.service.ProductService;
import com.enigmacamp.shopify.service.TransactionService;
import com.enigmacamp.shopify.utils.exeptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse create(TransactionRequest request) {
        // TODO: 1. Validate Id Customer
        CustomerResponse customerResponse = customerService.getCustomerById(request.getCustomerId());
        
        // TODO: 2. Prepare Data Customer
        Customer customer = Customer.builder()
                .id(customerResponse.getId())
                .name(customerResponse.getName())
                .address(customerResponse.getAddress())
                .email(customerResponse.getEmail())
                .phoneNumber(customerResponse.getPhoneNumber())
                .build();

        AtomicReference<Long> totalPayment = new AtomicReference<>(0L);

        // TODO: 3. Update product stock, create totalPayment, insert transaction details db
        List<TransactionDetail> transactionDetails = request.getTransactionDetailRequests().stream().map(detailTransaction -> {
            // TODO: Get Product
            ProductResponse product = productService.getById(detailTransaction.getProductId());
            if(product.getStock() < detailTransaction.getQuantity()) throw new ValidationException("Stock not enough", null);

            // TODO: Update product stock
            int stock = product.getStock() - detailTransaction.getQuantity();
            productService.updatePatch(ProductRequest.builder()
                    .id(product.getId())
                    .stock(stock)
                    .build());

            // TODO: Prepare Data Transaction Detail
            Product productEntity = Product.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(stock)
                    .build();

            TransactionDetail trxDetail = TransactionDetail.builder()
                    .product(productEntity)
                    .quantity(detailTransaction.getQuantity())
                    .totalPrice(productEntity.getPrice() * detailTransaction.getQuantity())
                    .build();

            // TODO: Update Total Payment
            totalPayment.updateAndGet(value -> value + trxDetail.getTotalPrice());

            // TODO: Create Transaction Detail
            transactionDetailRepository.saveAndFlush(trxDetail);
            return trxDetail;
        }).toList();
        
        // TODO: 4. Prepare Data Transaction
        Transaction transaction = Transaction.builder()
                .customer(customer)
                .transactionDetails(transactionDetails)
                .transactionDate(new Date())
                .build();

        // TODO: 5. Create transaction
        transactionRepository.saveAndFlush(transaction);

        return TransactionResponse.builder()
                .id(transaction.getId())
                .transactionDate(transaction.getTransactionDate())
                .transactionDetails(transaction.getTransactionDetails())
                .totalPayment(totalPayment.get())
                .build();
    }
}
