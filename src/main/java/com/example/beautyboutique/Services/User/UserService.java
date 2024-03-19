package com.example.beautyboutique.Services.User;

<<<<<<< HEAD
import com.example.beautyboutique.Models.Product;
=======
import com.example.beautyboutique.Exceptions.DataNotFoundException;
>>>>>>> adf3154457492eb20b7409d1934b7f9936c3ad3d
import com.example.beautyboutique.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

<<<<<<< HEAD
    public Optional<User> getUserByUserName(String userName);
    public Optional<User> getUserById(Integer id);

    List<User> findAll();

  User findById(Integer id);

    List<User> findByName(String userName);

    User delete(Integer id);

    User saveAfterCheck (User user);


    User save(User user);
=======
    public User getUserByUsername(String username);
    UserDetailsService userDetailsService ();
    Optional<User> getUserById(Integer userId);
>>>>>>> adf3154457492eb20b7409d1934b7f9936c3ad3d
}
