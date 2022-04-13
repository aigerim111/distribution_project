package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CatalogueProducts {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "catalogue_id")
    private Catalogue catalogue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private DictProduct product;

    private Long quantity;
}
