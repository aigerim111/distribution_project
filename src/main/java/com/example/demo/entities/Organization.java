package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dictOrgTypeId")
    private DictOrgType dictOrgType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentOrgId")
    private Organization parentOrg;

    @Column(nullable = false)
    private String name_ru;
    private String name_kz;
    @Column(unique = true, nullable = false)
    private String BIN;
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
    }

}
