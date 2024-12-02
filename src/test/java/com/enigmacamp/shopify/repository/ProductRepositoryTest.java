package com.enigmacamp.shopify.repository;

import com.enigmacamp.shopify.model.entity.Product;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void saveProductSuccess() {
        Product product = Product.builder()
                .name("product-jpa-test")
                .price(2000000L)
                .stock(4).
                build();

        Product saveProduct = productRepository.save(product);
        assertNotNull(entityManager.find(Product.class, saveProduct.getId()));
    }
}
