package com.example.beautyboutique.DTOs;

import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String username;
    private String  roleName;

    public UserDto(Integer userId, String username, String roleName) {
        this.userId = userId;
        this.username = username;
        this.roleName = roleName;
    }
}
