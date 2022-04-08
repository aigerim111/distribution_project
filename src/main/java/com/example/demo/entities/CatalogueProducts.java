package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class CatalogueProducts {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catalogue_id")
    private Catalogue catalogue;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private DictProduct product;

    private Long quantity;
}
