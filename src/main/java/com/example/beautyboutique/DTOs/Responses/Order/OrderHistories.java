package com.example.beautyboutique.DTOs.Responses.Order;

import com.example.beautyboutique.Models.CartItem;
import com.example.beautyboutique.Models.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderHistories {
    private Integer error;
    private String message;
    private List<Orders> orderHistories;
    private Integer totalPages;
    private Integer quantity;
}
