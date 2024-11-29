package com.enigmacamp.shopify.model.entity;

import com.enigmacamp.shopify.constant.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "m_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
