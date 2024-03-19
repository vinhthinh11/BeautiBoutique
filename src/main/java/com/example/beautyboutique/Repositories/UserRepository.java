package com.example.beautyboutique.Repositories;

import com.example.beautyboutique.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
<<<<<<< HEAD
    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

    List<User> findByUserNameContaining(String userName);

    Optional<User> findUserById(Integer id);
=======
    Optional<User> findByUsername(String username);
    Optional<User> findUserById(Integer id);
    boolean existsByUsername(String username);
>>>>>>> adf3154457492eb20b7409d1934b7f9936c3ad3d

}
