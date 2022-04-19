package com.example.demo.entities;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogueId;

    @OneToOne
    @JoinColumn(name = "org_id")
    private Organization org;

    @OneToMany(mappedBy = "catalogue",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CatalogueProducts> catalogueProducts = new HashSet<>();

    //???????
    private Long quantity;
    private Long price;
    //??

    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean isActive = true;

    @PrePersist
    protected void onCreate(){
        this.createdDateTime = LocalDateTime.now();
    }

    public Catalogue addProducts(CatalogueProducts catalogueProducts){
        catalogueProducts.setCatalogue(this);
        this.getCatalogueProducts().add(catalogueProducts);
        return this;
    }
}
