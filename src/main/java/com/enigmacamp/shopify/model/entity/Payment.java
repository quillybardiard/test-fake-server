package com.enigmacamp.shopify.model.entity;

import com.enigmacamp.shopify.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = ConstantTable.PAYMENT)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "redirect_url", nullable = false)
    private String redirectUrl;

    @Column(name = "method", nullable = false)
    private String method;

    @OneToOne(mappedBy = "payment")
    private Transaction transaction;
}
