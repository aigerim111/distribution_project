package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
public class EmailToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(
            nullable = false,
            name = "id"
    )
    private User user;

    public EmailToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.createdDate=LocalDateTime.now();
        this.expiryDate = createdDate.plusHours(2);
        this.user = user;
    }

    public EmailToken() {

    }
}
