package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@Table(name = "enduser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id")
    @JsonIgnore //not sure
    private Organization org;

//    @Column(nullable = false)
    private String username;
//    @Column(nullable = false)
    private String password;
//    @Column(nullable = false)
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    private String nameRu;
    private String nameKz;
    private String phone;

    @Column(updatable = false)
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;


    private boolean isActive;

    @PrePersist
    protected void onCreate(){
        this.createdDateTime = LocalDateTime.now();
        this.isActive = false;
    }

}