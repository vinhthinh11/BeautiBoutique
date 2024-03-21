package com.example.beautyboutique.Services.User;


import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username){
                return userRepository.findByUsername(username)
                        .orElseThrow(()->new UsernameNotFoundException("username not found"));
            }
        };
    }
    @Override
    public Optional<User> getUserById(Integer userId) {
        return Optional.empty();
    }

}
