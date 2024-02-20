package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.BlogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogImageRepository extends JpaRepository<BlogImage,Integer> {

}
