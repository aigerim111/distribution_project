package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "enduser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dictUserTypeId")
    private DictUserType dictUserType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private Organization org;

    @Column(nullable = false)
    private String nameRu;
    private String nameKz;
    @Column(nullable = false)
    private String phone;
    private String email;
    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean isActive = true;

    @PrePersist
    protected void onCreate(){
        this.createdDateTime = LocalDateTime.now();
    }
}
