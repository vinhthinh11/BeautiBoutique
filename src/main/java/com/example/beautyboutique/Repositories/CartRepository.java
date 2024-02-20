package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.Cart;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@ReadingConverter
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserId(Integer userId);

}
