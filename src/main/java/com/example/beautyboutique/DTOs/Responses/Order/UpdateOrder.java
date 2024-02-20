package com.example.beautyboutique.DTOs.Responses.Order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrder {
    private Boolean isUpdated;
    private String message;
}
