package com.example.beautyboutique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @Column(name = "quantity", columnDefinition = "int")
    private Integer quantity;

    @Column(name = "createdAt", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    public OrderDetail(Orders orders, Product product, Integer quantity) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }
}
