package com.example.beautyboutique.Services.User;

import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
