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
public class ShipDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int", nullable = false)
    private Integer id;

    @Column(name = "fullName", columnDefinition = "nvarchar(50)")
    private String fullName;

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "phoneNumber", columnDefinition = "varchar(10)")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public ShipDetail(String fullName, String address, String phoneNumber, User user) {
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }
}
