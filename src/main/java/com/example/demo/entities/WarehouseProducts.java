package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WarehouseProducts {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private DictProduct product;

    private Long quantity;
}
