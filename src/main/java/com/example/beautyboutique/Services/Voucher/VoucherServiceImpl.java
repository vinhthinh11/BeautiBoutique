package com.example.beautyboutique.Services.Voucher;

import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Models.Voucher;
import com.example.beautyboutique.Models.VoucherDetail;
import com.example.beautyboutique.Repositories.UserRepository;
import com.example.beautyboutique.Repositories.VoucherDetailRepository;
import com.example.beautyboutique.Repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    VoucherDetailRepository voucherDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<VoucherDetail> getListVouchersByUserId(Integer userId) {
        return voucherDetailRepository.findByUserId(userId);
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public Boolean addVoucher(Integer userId, Integer voucherId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty())
                return false;
            Optional<Voucher> voucherOptional = voucherRepository.findById(voucherId);
            if (voucherOptional.isEmpty())
                return false;

            User user = userOptional.get();
            Voucher voucher = voucherOptional.get();

            VoucherDetail voucherDetail = new VoucherDetail(user, voucher);
            voucherDetailRepository.save(voucherDetail);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
