package com.example.beautyboutique.Services.User;

import com.example.beautyboutique.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

  User findById(Integer id);

    List<User> findByName(String userName);

    User delete(Integer id);

    User saveAfterCheck (User user);

    User update(Integer id,User UserUpdate);


    User save(User user);

    public User getUserByUsername(String username);
    UserDetailsService userDetailsService ();
    Optional<User> getUserById(Integer userId);

}
