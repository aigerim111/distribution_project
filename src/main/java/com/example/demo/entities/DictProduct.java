package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class DictProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dictProductTypeId")
    private DictProductType dictProductType;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<WarehouseProducts> warehouseProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<OrderProducts> orderProducts = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<CatalogueProducts> catalogueProducts = new HashSet<>();

    @Column(nullable = false)
    private String nameRu;
    private String nameKz;
    private String nameEng;
    @Lob
    @Column(columnDefinition = "BYTEA", nullable = false)
    private byte[] imageByte;
    @Column(nullable = false)
    private String descrRu;
    private String descrKz;
    private String descrEng;
    @Column(nullable = false)
    private String originCountryRu;
    private String originCountryKz;
    private String originCountryEng;
    @Column(nullable = false)
    private String producerRu;
    private String producerKz;
    private String producerEng;
    private String weightNetto;
    private String weightBrutto;
    private String measureUnit;
    private String protein;
    private String fats;
    private String carbs;
    private String energyValue;
    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean isActive = true;

    @PrePersist
    protected void onCreate(){
        this.createdDateTime = LocalDateTime.now();
    };
}
