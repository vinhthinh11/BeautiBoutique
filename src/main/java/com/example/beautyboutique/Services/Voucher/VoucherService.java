package com.example.beautyboutique.Services.Voucher;

import com.example.beautyboutique.Models.Voucher;
import com.example.beautyboutique.Models.VoucherDetail;

import java.util.List;

public interface VoucherService {
    List<VoucherDetail> getListVouchersByUserId(Integer userId);
    List<Voucher> getAllVouchers();

    Boolean addVoucher( Integer userId, Integer voucherId);
}
