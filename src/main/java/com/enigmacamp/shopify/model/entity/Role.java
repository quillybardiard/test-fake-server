package com.enigmacamp.shopify.model.entity;

import com.enigmacamp.shopify.constant.ConstantTable;
import com.enigmacamp.shopify.constant.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = ConstantTable.ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
