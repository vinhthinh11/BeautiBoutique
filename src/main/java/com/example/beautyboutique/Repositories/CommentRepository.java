package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
