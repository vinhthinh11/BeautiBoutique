package com.example.beautyboutique.DTOs.Responses.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CancelOrder {
    private Boolean isCancelled;
    private String message;
}
