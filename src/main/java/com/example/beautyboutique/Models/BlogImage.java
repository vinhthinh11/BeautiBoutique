package com.example.beautyboutique.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogImage {
    @Id
    @Column(name = "imageId", columnDefinition = "varchar(30)", nullable = false)
    private String id;

    @Column(name = "imageUrl", columnDefinition = "varchar(255)")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "blogId", columnDefinition = "int")
    private BlogPost blogPost;
}
