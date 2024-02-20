package com.example.beautyboutique.Services.Voucher;

import com.example.beautyboutique.Models.Voucher;
import com.example.beautyboutique.Models.VoucherDetail;
import com.example.beautyboutique.Repositories.VoucherDetailRepository;
import com.example.beautyboutique.Repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    VoucherDetailRepository voucherDetailRepository;

    @Override
    public List<VoucherDetail> getListVouchersByUserId(Integer userId) {
        return voucherDetailRepository.findByUserId(userId);
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }
}
