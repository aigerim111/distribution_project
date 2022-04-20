package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Catalogue catalogue; //mb Long catalogueId instead of Catalogue catalogue

    @ManyToOne
    @JoinColumn(name = "product_id")
    private DictProduct product;

    private Long quantity;

    public CatalogueProducts(Catalogue catalogue, DictProduct product, Long quantity){
        this.catalogue = catalogue;
        this.product = product;
        this.quantity = quantity;
    }

    public CatalogueProducts() {

    }
}
