package com.example.beautyboutique.DTOs.Responses.Cart;

import com.example.beautyboutique.Models.CartItem;
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
public class PageCart {
    private BigDecimal totalPrice;
    private Integer totalPages;
    private List<CartItem> cartItems;
}
