package com.example.beautyboutique.Models;

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
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int", nullable = false)
    private Integer id;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "quantity", columnDefinition = "int")
    private Integer quantity;

    @Column(name = "numUsedVoucher", columnDefinition = "int")
    private Integer numUsedVoucher;

    @Column(name = "startDate", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreationTimestamp
    private Date startDate;

    @Column(name = "endDate", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreationTimestamp
    private Date endDate;

}
