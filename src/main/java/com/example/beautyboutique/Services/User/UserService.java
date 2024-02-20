package com.example.beautyboutique.Services.User;

import com.example.beautyboutique.Models.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> getUserByUserName(String userName);


}
