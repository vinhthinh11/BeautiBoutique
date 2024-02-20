package com.example.beautyboutique.DTOs.Responses.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatedOrder {
    private Boolean status;
    private String message;
}
