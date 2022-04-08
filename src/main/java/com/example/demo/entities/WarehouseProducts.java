package com.example.demo.entities;

import javax.persistence.*;


@Entity
public class WarehouseProducts {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private DictProduct product;

    private Long quantity;
}
