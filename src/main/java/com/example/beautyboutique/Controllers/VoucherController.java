package com.example.beautyboutique.Controllers;

import com.example.beautyboutique.DTOs.Responses.Voucher.AllVouchers;
import com.example.beautyboutique.DTOs.Responses.Voucher.PageVoucher;
import com.example.beautyboutique.DTOs.Responses.Voucher.VoucherOfUser;
import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Models.Voucher;
import com.example.beautyboutique.Models.VoucherDetail;
import com.example.beautyboutique.Services.JWTServiceImpl;
import com.example.beautyboutique.Services.User.UserService;
import com.example.beautyboutique.Services.Voucher.VoucherServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/voucher")
public class VoucherController {
    @Autowired
    VoucherServices voucherServices;
    @Autowired
    UserService userService;

    @Autowired
    JWTServiceImpl jwtService;

    @GetMapping(value = "/get-voucher")
    public ResponseEntity<?> getVouchers(HttpServletRequest request) {
        try {
            Integer userId = jwtService.getUserIdByToken(request);
            List<VoucherDetail> listVouchers = voucherServices.getListVouchersByUserId(userId);
            return new ResponseEntity<>(new VoucherOfUser(0, "Get vouchers of user successfully!", listVouchers), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Get vouchers fail!", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "add-voucher")
    public ResponseEntity<?> addVoucher(@RequestParam(value = "userId", required = false) Integer userId,
                                        @RequestParam(value = "voucherId", required = false) Integer voucherId) {
        try {
            Boolean isAddVoucher = voucherServices.addVoucher(userId, voucherId);
            if (!isAddVoucher)
                return new ResponseEntity<>("Add vouchers successfully!", HttpStatus.CREATED);

            return new ResponseEntity<>("Add vouchers fail!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Add voucher fail!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-all-voucher")
    public ResponseEntity<?> getAllVoucher() {
        try {
            List<Voucher> vouchersList = voucherServices.getAllVouchers();
            return new ResponseEntity<>(new PageVoucher("Get All Voucher Successfully", vouchersList.size(), vouchersList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving information failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create-voucher", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE

    }, produces = {MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    ResponseEntity<?> createVoucher(Voucher voucher) {

        try {

            Voucher createdVoucher = voucherServices.createVoucher(voucher);
            if (createdVoucher != null) {
                return new ResponseEntity<>("Created a voucher successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to create comment", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/save-voucher-for-user")
    ResponseEntity<?> saveVoucher(@RequestParam(value = "voucherId") Integer voucherId,
                                  @RequestParam(value = "userId") Integer userId) {

        try {
            boolean isSave = voucherServices.getVoucherDetailByUserIdAndVoucherId(userId, voucherId);
            if (isSave) {
                return new ResponseEntity<>("You have already saved this voucher", HttpStatus.OK);
            }
            Optional<User> userSaveVoucher = userService.getUserById(userId);
            Voucher voucher = voucherServices.getAVoucherId(voucherId);
            Integer remainAmount = voucher.getQuantity() - voucher.getNumUsedVoucher();

            if (remainAmount <= 0) {
                return new ResponseEntity<>("Vouchers are sold out", HttpStatus.OK);
            }

            VoucherDetail voucherDetail = new VoucherDetail();
            voucherDetail.setUser(userSaveVoucher.get());
            voucherDetail.setVoucher(voucher);
            VoucherDetail createdVoucher = voucherServices.saveVoucherForUser(voucherDetail);
            if (createdVoucher != null) {
                return new ResponseEntity<>("Save voucher for user successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to save voucher for user", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-voucher", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE
    }, produces = {MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    ResponseEntity<?> updateVoucher(Voucher voucher,
                                    @RequestParam(value = "id") Integer id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Invalid voucher ID");
            }
            Voucher updatedVoucherResult = voucherServices.updateVoucher(id, voucher);
            if (updatedVoucherResult != null) {
                return new ResponseEntity<>("Updated Voucher successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Failed to update comment", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-voucher")
    public ResponseEntity<?> deleteComment(@RequestParam(value = "id") Integer id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Invalid comment ID");
            }
            boolean isDelete = voucherServices.deleteVoucher(id);
            if (isDelete) {
                return new ResponseEntity<>("Delete voucher successfully!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Delete voucher fail!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server failed", HttpStatus.BAD_REQUEST);
        }
    }


}
