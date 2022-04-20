package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "catalogue",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CatalogueProducts> catalogueProducts = new ArrayList<>();


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

}
