package com.enigmacamp.shopify.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(
            mappedBy = "transaction",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<TransactionDetail> transactionDetails;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
