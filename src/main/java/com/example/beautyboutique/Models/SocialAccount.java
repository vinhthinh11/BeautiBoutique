package com.example.beautyboutique.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int", nullable = false)
    private Integer id;

    @Column(name = "provider", columnDefinition = "varchar(20)")
    private String provider;

    @Column(name = "providerId", columnDefinition = "varchar(20)")
    private String providerId;

    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "name", columnDefinition = "nvarchar(50)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
