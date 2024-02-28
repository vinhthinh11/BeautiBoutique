package com.example.beautyboutique.Services.Cart;

import com.example.beautyboutique.DTOs.Responses.Cart.PageCart;
import com.example.beautyboutique.Models.Cart;
import com.example.beautyboutique.Models.CartItem;
import com.example.beautyboutique.Repositories.CartItemRepository;
import com.example.beautyboutique.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    public BigDecimal calculateTotalPrice(List<CartItem> cartItems) {
        BigDecimal total = BigDecimal.ZERO; // Tổng giá trị khởi đầu từ 0

        for (CartItem cartItem : cartItems) {
//            BigDecimal itemTotalPrice = cartItem.getProduct().getSalePrice().multiply(new BigDecimal(cartItem.getQuantity()));
            BigDecimal itemTotalPrice = cartItem.getTotalPrice();
            total = total.add(itemTotalPrice);
        }
        return total;
    }

    @Override
    public PageCart getCartByUserId(Integer userId, Integer pageNo, Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
            if (cartOptional.isPresent()) {
                Integer cartId = cartOptional.get().getId();
                Page<CartItem> pageCart = cartItemRepository.getCartItemsByCart_Id(cartId, pageable);
                Integer totalPages = pageCart.getTotalPages();
                List<CartItem> cartItems = pageCart.getContent();
                BigDecimal totalPrice = calculateTotalPrice(cartItems);
                return new PageCart(totalPrice, totalPages, cartItems);
            } else {
                return new PageCart();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new PageCart();
        }
    }

    @Override
    public Boolean updateCart(Integer userId, Integer cartItemId, Integer quantity) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isEmpty()) {
            return false;
        }
        Integer cartId = cartOptional.get().getId();
        Optional<CartItem> cartItemOptional = cartItemRepository.findByIdAndCart_Id(cartItemId, cartId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            if (quantity > 0) {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
                return true;
            }
            return false;
        }
        return false;

    }

    @Override
    public Boolean deleteCartItem(Integer userId, Integer cartItemId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isEmpty()) {
            return false;
        }
        Integer cartId = cartOptional.get().getId();
        Optional<CartItem> cartItemOptional = cartItemRepository.findByIdAndCart_Id(cartItemId, cartId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

}
