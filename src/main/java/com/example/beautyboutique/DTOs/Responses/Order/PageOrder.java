package com.example.beautyboutique.DTOs.Responses.Order;

import com.example.beautyboutique.Models.CartItem;
import com.example.beautyboutique.Models.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageOrder {
    private Integer totalPages;
    private List<Orders> orders;
}
