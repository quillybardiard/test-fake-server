package com.enigmacamp.shopify.model.entity;

import com.enigmacamp.shopify.constant.ConstantTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = ConstantTable.PRODUCT)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "price", nullable = false, columnDefinition = "BIGINT CHECK (price >= 0)")
    private Long price;
    @Column(name = "stock", nullable = false, columnDefinition = "BIGINT CHECK (stock >= 0)")
    private Integer stock;
    @Column(
            name = "create_at"
    )
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
}
