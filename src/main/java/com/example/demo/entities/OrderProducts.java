package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supp_org_id")
    private Organization suppOrgId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supp_user_id")
    private User suppUserId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private DictProduct product;

    private Long offeredPrice;
    @Column(nullable = false)
    private Long requestedPrice;
    @Column(nullable = false)
    private Long quantity;
    @Column(nullable = false)
    private Long status;
}
