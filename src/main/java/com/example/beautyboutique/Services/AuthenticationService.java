package com.example.beautyboutique.Services;

import com.example.beautyboutique.DTOs.JwtAuthenticationResponse;
import com.example.beautyboutique.DTOs.RefreshTokenRequest;
import com.example.beautyboutique.DTOs.SignInRequest;
import com.example.beautyboutique.DTOs.SignUpRequest;
import com.example.beautyboutique.Models.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
