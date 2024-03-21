package com.example.beautyboutique.Services;

import com.example.beautyboutique.DTOs.JwtAuthenticationResponse;
import com.example.beautyboutique.DTOs.RefreshTokenRequest;
import com.example.beautyboutique.DTOs.SignInRequest;
import com.example.beautyboutique.DTOs.SignUpRequest;
import com.example.beautyboutique.Models.Role;
import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl  implements AuthenticationService{
    private final UserRepository    userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    public User signup(SignUpRequest signUpRequest){
        User user = new User();
        user.setFullName(signUpRequest.getFullName());
        user.setUsername(signUpRequest.getUsername());
        user.setAddress(signUpRequest.getAddress());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setDateOfBirth(signUpRequest.getDateOfBirth());
        user.setFacebookAccountId(signUpRequest.getFacebookAccountId());
        user.setGoogleAccountId(signUpRequest.getGoogleAccountId());
        Role role = new Role(); // Tạo một đối tượng Role mới
        role.setRoleId(2); // Gán khóa chính của vai trò cố định
        role.setRoleName("USER"); // Gán tên của vai trò cố định
        user.setRole(role);
        return userRepository.save(user);
    }
    public JwtAuthenticationResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (signInRequest.getUsername(),signInRequest.getPassword()));
        var user =userRepository.findByUsername(signInRequest.getUsername()).
                orElseThrow(()->new IllegalArgumentException("INVALID USERNAME OR PASSWORD"));
        var jwt = jwtService.generateToken(user);
        var refreshtoken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshtoken(refreshtoken);
        return  jwtAuthenticationResponse;
    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String username = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByUsername(username).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user))
        {
            var jwt =jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshtoken(refreshTokenRequest.getToken());
            return  jwtAuthenticationResponse;
        }
        return null;
    }
}
