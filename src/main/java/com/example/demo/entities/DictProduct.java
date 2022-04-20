package com.example.demo.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<WarehouseProducts> warehouseProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderProducts> orderProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CatalogueProducts> catalogueProducts = new ArrayList<>();

    @Column(nullable = false)
    private String nameRu;
    private String nameKz;
    private String nameEng;

    @Type(type="org.hibernate.type.BinaryType")
    @Column(columnDefinition = "BYTEA", nullable = true)
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
