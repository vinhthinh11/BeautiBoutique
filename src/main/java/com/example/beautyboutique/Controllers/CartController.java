package com.example.beautyboutique.Controllers;

import com.example.beautyboutique.DTOs.Responses.Cart.CartResponse;
import com.example.beautyboutique.DTOs.Responses.Cart.PageCart;
import com.example.beautyboutique.Models.Cart;
import com.example.beautyboutique.Models.CartItem;
import com.example.beautyboutique.Services.Cart.CartServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {


    @Autowired
    CartServiceImpl cartService;

    @GetMapping(value = "/get-cart")
    public ResponseEntity<?> getCart(@RequestParam(value = "userId", required = true) Integer userId,
                                     @RequestParam(value = "pageNo", required = false, defaultValue = "0") Integer pageNo,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {

        try {
            PageCart pageCart = cartService.getCartByUserId(userId, pageNo, pageSize);
            List<CartItem> cartItems = pageCart.getCartItems();
            Integer totalPages = pageCart.getTotalPages();
            Integer quantity = cartItems.size();
            BigDecimal totalPrice = pageCart.getTotalPrice();
            if (!cartItems.isEmpty()) {
                return new ResponseEntity<>(new CartResponse(0, "Get cart successfully!", cartItems, totalPages, totalPrice, quantity, 200), HttpStatus.OK);
            }
            return new ResponseEntity<>(new CartResponse(0, "Cart is empty!", 404), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new CartResponse(0, "Get cart fail!", 400), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping(value = "/update-cart")
    public ResponseEntity<?> updateCart(@RequestParam(value = "userId", required = true) Integer userId,
                                        @RequestParam(value = "cartItemId", required = true) Integer cartItemId,
                                        @RequestParam(value = "quantity", required = true) Integer quantity) {
        try {
            Boolean isUpdate = cartService.updateCart(userId, cartItemId, quantity);
            if (isUpdate)
                return new ResponseEntity<>("Update cart successfully!", HttpStatus.OK);
            return new ResponseEntity<>("Update cart fail!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Update cart fail!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-cart"
    )
    public  ResponseEntity<?> deleteCart(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "cartItemId") Integer cartItemId) {
        try {
            Boolean isDelete = cartService.deleteCartItem(userId, cartItemId);
            if (isDelete)
                return new ResponseEntity<>("Delete cart item successfully!", HttpStatus.OK);
            return new ResponseEntity<>("Delete cart item fail!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete cart item fail!", HttpStatus.BAD_REQUEST);
        }
    }
}
