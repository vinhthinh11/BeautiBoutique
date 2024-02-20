package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.ShipDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipDetailRepository extends JpaRepository<ShipDetail, Integer> {

}
