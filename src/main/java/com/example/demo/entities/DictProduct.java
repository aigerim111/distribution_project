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
    private Long product_id;

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
    private String name_ru;
    private String name_kz;
    private String name_eng;
    @Lob
    @Column(columnDefinition = "BYTEA", nullable = false)
    private byte[] imageByte;
    @Column(nullable = false)
    private String descr_ru;
    private String descr_kz;
    private String descr_eng;
    @Column(nullable = false)
    private String originCountry_ru;
    private String originCountry_kz;
    private String originCountry_eng;
    @Column(nullable = false)
    private String producer_ru;
    private String producer_kz;
    private String producer_eng;
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
