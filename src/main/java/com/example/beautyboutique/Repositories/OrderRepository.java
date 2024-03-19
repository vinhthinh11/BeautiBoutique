package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT o FROM Orders o LEFT JOIN o.shipDetail sd WHERE (:userId IS NULL OR sd.user.id = :userId)")
    Page<Orders> findByConditions(@Param("userId") Integer userId, Pageable pageable);

}
