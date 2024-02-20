package com.example.beautyboutique.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id", columnDefinition = "int", nullable = false)
    private Integer id;

    @Column(name = "roleName", columnDefinition = "nvarchar(50)")
    private String roleName;
}
