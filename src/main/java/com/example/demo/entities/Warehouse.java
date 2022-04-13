package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long warehouseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private Organization org;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<WarehouseProducts> warehouseProducts = new HashSet<>();

    //@Column(nullable = false)
    private String address;
    //@Column(nullable = false)
    private String phone;
    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean isActive = true;

    @PrePersist
    protected void onCreate(){
        this.createdDateTime = LocalDateTime.now();
    };

}
