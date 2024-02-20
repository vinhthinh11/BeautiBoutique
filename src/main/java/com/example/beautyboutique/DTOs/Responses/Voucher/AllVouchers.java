package com.example.beautyboutique.DTOs.Responses.Voucher;

import com.example.beautyboutique.Models.CartItem;
import com.example.beautyboutique.Models.Voucher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AllVouchers {
    private Integer error;
    private String message;
    private List<Voucher> vouchers;
}
