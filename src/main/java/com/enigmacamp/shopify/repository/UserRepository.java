package com.enigmacamp.shopify.repository;

import com.enigmacamp.shopify.model.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, String> {
}
