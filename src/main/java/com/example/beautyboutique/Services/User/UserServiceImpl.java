package com.example.beautyboutique.Services.User;


import com.example.beautyboutique.Exception.ResourceNotFoundException;

import com.example.beautyboutique.Models.Product;
import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id"+ id +"not found"));
    }

    @Override
    public List<User> findByName(String username) {
        List<User> users = userRepository.findByUsernameContaining(username);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User with name " + username + " not found");
        }
        return users;
    }


    @Override
    public User delete(Integer id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.delete(userToDelete);
        return userToDelete;
    }


    @Override
    public User saveAfterCheck(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            System.out.println("UserName Have Exits");
            return  user;
        }
            return userRepository.save(user);
    }

    @Override
    public User update(Integer id, User UserUpdate) throws ResourceNotFoundException, DataIntegrityViolationException  {
        if (UserUpdate == null) {
            throw new IllegalArgumentException("Product update data cannot be null");
        }
        try {
            Optional<User> userOptional = userRepository.findUserById(id);
            if (!userOptional.isPresent()) {
                throw new ResourceNotFoundException("User not found");
            }

            User user = userOptional.get();

    }catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findUserById(id);
    }


    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username){
                return userRepository.findByUsername(username)
                        .orElseThrow(()->new UsernameNotFoundException("username not found"));
            }
        };
    }


}
