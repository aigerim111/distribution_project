package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonIgnore //not sure
    private Organization org;

    @OneToOne(mappedBy = "user")
    @JsonIgnore //not sure
    private Login login;

    private String nameRu;
    private String nameKz;
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
