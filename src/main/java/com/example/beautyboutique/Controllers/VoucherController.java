package com.example.beautyboutique.Controllers;

import com.example.beautyboutique.DTOs.Responses.Voucher.AllVouchers;
import com.example.beautyboutique.DTOs.Responses.Voucher.VoucherOfUser;
import com.example.beautyboutique.Models.Voucher;
import com.example.beautyboutique.Models.VoucherDetail;
import com.example.beautyboutique.Services.Voucher.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

    @Autowired
    VoucherServiceImpl voucherService;

    @GetMapping(value = "/get-voucher")
    public ResponseEntity<?> getVouchers(@RequestParam(value = "userId", required = false) Integer userId) {
        try {
            if (userId == null) {
                List<Voucher> listVouchers = voucherService.getAllVouchers();
                return new ResponseEntity<>(new AllVouchers(0, "Get vouchers successfully!", listVouchers), HttpStatus.OK);
            }
            List<VoucherDetail> listVouchers = voucherService.getListVouchersByUserId(userId);
            return new ResponseEntity<>(new VoucherOfUser(0, "Get vouchers of user successfully!", listVouchers), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Get vouchers fail!", HttpStatus.BAD_REQUEST);
        }
    }
}
