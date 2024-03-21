package com.example.beautyboutique.Controllers;

import com.example.beautyboutique.DTOs.*;
import com.example.beautyboutique.Exceptions.DataNotFoundException;
import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Services.AuthenticationService;
import com.example.beautyboutique.Services.EmailService;
import com.example.beautyboutique.Services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    private final EmailService mailService;
    @PostMapping("/register")
    public ResponseEntity <?> signup(@RequestBody SignUpRequest signUpRequest) throws DataNotFoundException {
        try {
            User user =  authenticationService.signup(signUpRequest);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> siginin(
            @RequestBody SignInRequest signInRequest){
        try {
            JwtAuthenticationResponse authenticationResponse = authenticationService.signin(signInRequest);
            return ResponseEntity.ok(authenticationResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(
            @RequestBody RefreshTokenRequest refreshTokenRequest)
    {
        return  ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
    @PostMapping("/forgot")
    public ResponseEntity<String> reset(
            @RequestParam(name="username") String username)
    {
        mailService.sendEmail(username);
        return  ResponseEntity.ok("ok");
    }


}
