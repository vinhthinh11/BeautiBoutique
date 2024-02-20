package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<OrderStatus, Integer> {
}
