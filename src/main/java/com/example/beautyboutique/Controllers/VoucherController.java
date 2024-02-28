package com.example.beautyboutique.Controllers;

import com.example.beautyboutique.DTOs.Responses.Voucher.AllVouchers;
import com.example.beautyboutique.DTOs.Responses.Voucher.VoucherOfUser;
import com.example.beautyboutique.Models.Voucher;
import com.example.beautyboutique.Models.VoucherDetail;
import com.example.beautyboutique.Services.Voucher.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping(value = "add-voucher")
    public ResponseEntity<?> addVoucher(@RequestParam(value = "userId", required = false) Integer userId,
                                        @RequestParam(value = "voucherId", required = false) Integer voucherId) {
        try {
            Boolean isAddVoucher = voucherService.addVoucher(userId, voucherId);
            if (!isAddVoucher)
                return new ResponseEntity<>("Add vouchers successfully!", HttpStatus.CREATED);

            return new ResponseEntity<>("Add vouchers fail!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Add voucher fail!", HttpStatus.BAD_REQUEST);
        }
    }


}
