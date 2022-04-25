package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_org_id")
    private Organization shopOrgId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_user_id")
    private User shopUserId;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderProducts> orderProducts = new ArrayList<>();


    private Long totalPrice;
    @Column(updatable = false)
    private LocalDateTime dateOfOrderRegistration;
    private LocalDateTime dateOfDelivery;

    //0 - created, 1 - accepted, 2 - in process, 3 - ended, 4 - something wrong happened, omg
    private Long status;


    @PrePersist
    protected void onCreate(){
        this.dateOfOrderRegistration = LocalDateTime.now();
        this.status = 0L;
        this.totalPrice = 0L;
    };

}
