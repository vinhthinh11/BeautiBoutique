package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Page<CartItem> getCartItemsByCart_Id(Integer cartId, Pageable pageable);
    Optional<CartItem> findByIdAndCart_Id(Integer cartItemId, Integer cartId);
}
