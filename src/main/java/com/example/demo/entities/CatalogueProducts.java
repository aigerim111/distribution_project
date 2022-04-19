package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CatalogueProducts {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogue_id")
    private Catalogue catalogue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private DictProduct product;

    private Long quantity;

    public CatalogueProducts(DictProduct product, Long quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public CatalogueProducts() {

    }
}
