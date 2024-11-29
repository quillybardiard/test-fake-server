package com.enigmacamp.shopify.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "m_user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role;
}