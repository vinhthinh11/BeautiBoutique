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
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "likeCount", columnDefinition = "int")
    private Integer likeCount;

    @Column(name = "startDate", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreationTimestamp
    private Date startDate;

    @Column(name = "endDate", columnDefinition = "DATETIME", nullable = false, updatable = false)
    @CreationTimestamp
    private Date endDate;
}
