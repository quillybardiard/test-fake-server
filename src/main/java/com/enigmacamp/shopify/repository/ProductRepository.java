package com.enigmacamp.shopify.repository;

import com.enigmacamp.shopify.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    // Built-in methods:
    // save(), findById(), findAll(), delete()

    // Custom query methods:
    List<Product> findAllByNameLikeOrderByNameAsc(
            String name
    );
}
