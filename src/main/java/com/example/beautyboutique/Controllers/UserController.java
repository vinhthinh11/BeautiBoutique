package com.example.beautyboutique.Controllers;
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
    UserService userService;
    @GetMapping("")//get-user
    public ResponseEntity<String> gSetuser(){
        return ResponseEntity.ok("hiadmin");
    }
    @GetMapping("/getuser")
    public ResponseEntity<User> getuser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        User user =  userService.getUserByUsername(JwtUtil.getUsernameFromJwt(token));
        Integer userId= user.getId();
        return ResponseEntity.ok(user);
    }
}
