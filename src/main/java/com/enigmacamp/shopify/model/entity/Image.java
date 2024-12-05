package com.enigmacamp.shopify.model.entity;

import com.enigmacamp.shopify.constant.ConstantTable;
import jakarta.persistence.*;

@Entity
@Table(name = ConstantTable.IMAGE)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "path", nullable = false)
    private String path;
}
