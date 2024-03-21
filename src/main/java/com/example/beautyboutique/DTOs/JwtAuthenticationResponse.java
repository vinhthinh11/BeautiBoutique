package com.example.beautyboutique.DTOs;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private  String refreshtoken;
}
