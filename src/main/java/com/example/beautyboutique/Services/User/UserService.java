package com.example.beautyboutique.Services.User;

import com.example.beautyboutique.Exception.ResourceNotFoundException;
import com.example.beautyboutique.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {


    public Optional<User> getUserByUserName(String userName);

    public Optional<User> getUserById(Integer id) throws ResourceNotFoundException;

    List<User> findAll();

    List<User> findByName(String userName);

    User delete(Integer id);

    User saveAfterCheck (User user);

    User updateUser (Integer id , User userUpdate);

    User save(User user);
    public User getUserByUsername(String username);
    UserDetailsService userDetailsService ();


}
