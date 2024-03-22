package com.example.beautyboutique.Controllers;

import com.example.beautyboutique.DTOs.UserDto;
import com.example.beautyboutique.Services.User.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Utils.ZaloAlgorithem.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/users")

@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getser")
    public ResponseEntity<UserDto> getuser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        User user =  userService.getUserByUsername(JwtUtil.getUsernameFromJwt(token));
        String username = user.getUsername();
        Integer userId= user.getId();
        String roleName=user.getRole().getRoleName();
        String email = user.getEmail();
        return ResponseEntity.ok(new UserDto(userId,username,roleName,email));
    }
}
