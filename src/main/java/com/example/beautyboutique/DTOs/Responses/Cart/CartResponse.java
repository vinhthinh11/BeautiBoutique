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
public class CartResponse {
    private Integer error;
    private String message;
    private List<CartItem> carts;
    private Integer totalPages;
    private BigDecimal totalPrice;
    private Integer quantity;
    private Integer status;

    public CartResponse(Integer error, String message, Integer status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }
}
