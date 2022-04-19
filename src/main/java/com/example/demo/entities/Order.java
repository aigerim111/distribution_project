package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderProducts> orderProducts = new HashSet<>();


    private Long totalPrice;
    @Column(updatable = false)
    private LocalDateTime dateOfOrderRegistration;
    private LocalDateTime dateOfDelivery;
    private Long status;


    @PrePersist
    protected void onCreate(){
        this.dateOfOrderRegistration = LocalDateTime.now();
    };

}
