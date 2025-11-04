package com.voltlab.app_backend.favorite.model;

import com.voltlab.app_backend.product.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorites", indexes = {
        @Index(name = "idx_fav_user", columnList = "user_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uk_fav_user_product", columnNames = {"user_id", "product_id"})
})
@Getter
@Setter
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}


