package com.example.beautyboutique.Controllers;


import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Services.User.UserService;
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
    @GetMapping("/getuser")
    public ResponseEntity<?> getuser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        User user =  userService.getUserByUsername(JwtUtil.getUsernameFromJwt(token));
        String username = user.getUsername();
        String role=user.getRole().getRoleName();
        return ResponseEntity.ok("username :"+ username +"  role:ROLE_"+ role);
    }
}
