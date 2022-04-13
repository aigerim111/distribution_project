package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class DictOrgType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dictOrgTypeId;
    @Column(nullable = false)
    private String nameRu;
    private String nameKz;
    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
    private boolean isActive = true;

    @PrePersist
    protected void onCreate(){
        this.createdDateTime = LocalDateTime.now();
    }
}
