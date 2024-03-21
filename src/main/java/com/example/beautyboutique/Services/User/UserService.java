package com.example.beautyboutique.Services.User;

import com.example.beautyboutique.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {


    public User getUserByUsername(String username);
    UserDetailsService userDetailsService ();
    Optional<User> getUserById(Integer userId);

}
