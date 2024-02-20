package com.example.beautyboutique.Services.Voucher;

import com.example.beautyboutique.Models.Comment;
import com.example.beautyboutique.Models.Voucher;
import com.example.beautyboutique.Models.VoucherDetail;

import java.util.List;

public interface VoucherService {
    public List<Voucher> getAllVoucher();

    List<VoucherDetail> getListVouchersByUserId(Integer userId);

    public Voucher getAVoucherId(Integer id);
    public boolean deleteVoucher(Integer id );
    public Voucher createVoucher(Voucher voucher);
    public Voucher updateVoucher(Integer id, Voucher updateVoucher);
}
