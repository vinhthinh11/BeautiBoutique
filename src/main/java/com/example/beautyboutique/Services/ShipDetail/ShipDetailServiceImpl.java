package com.example.beautyboutique.Services.ShipDetail;


import com.example.beautyboutique.DTOs.Requests.ShipDetail.ShipDetailDTO;
import com.example.beautyboutique.DTOs.Responses.ResponseDTO;
import com.example.beautyboutique.Models.ShipDetail;
import com.example.beautyboutique.Models.User;
import com.example.beautyboutique.Repositories.ShipDetailRepository;
import com.example.beautyboutique.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShipDetailServiceImpl implements ShipDetailService {

    @Autowired
    ShipDetailRepository shipDetailRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<ShipDetail> getShipDetailList(Integer userId) {
        return shipDetailRepository.findByUserId(userId);
    }

    @Override
    public ResponseDTO createShipDetail(Integer userId, ShipDetailDTO shipDetailDTO) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return new ResponseDTO(false, "User not found!");
            }
            User user = userOptional.get();
            ShipDetail shipDetail = new ShipDetail(shipDetailDTO.getFullName(), shipDetailDTO.getAddress(), shipDetailDTO.getPhoneNumber(), user);
            shipDetailRepository.save(shipDetail);
            return new ResponseDTO(true, "Create ship detail successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO(false, "Create ship detail fail!");
        }
    }

    @Override
    public ResponseDTO updateShipDetail(Integer userId, Integer shipDetailId, ShipDetailDTO shipDetailDTO) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return new ResponseDTO(false, "User not found!");
            }
            Optional<ShipDetail> shipDetailOptional = shipDetailRepository.findById(shipDetailId);
            if (shipDetailOptional.isEmpty()) {
                return new ResponseDTO(false, "Ship detail not found!");
            }
            User user = userOptional.get();
            ShipDetail shipDetail = shipDetailOptional.get();
            if (!Objects.equals(shipDetail.getUser().getId(), user.getId())) {
                return new ResponseDTO(false, "This shipping detail does not exist in your account!");
            }
            shipDetail.setFullName(shipDetailDTO.getFullName());
            shipDetail.setAddress(shipDetailDTO.getAddress());
            shipDetail.setPhoneNumber(shipDetailDTO.getPhoneNumber());
            shipDetailRepository.save(shipDetail);
            return new ResponseDTO(true, "Update ship detail successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO(false, "Update ship detail fail!!");
        }
    }

    @Override
    public ResponseDTO deleteShipDetail(Integer userId, Integer shipDetailId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return new ResponseDTO(false, "User not found!");
            }
            Optional<ShipDetail> shipDetailOptional = shipDetailRepository.findById(shipDetailId);
            if (shipDetailOptional.isEmpty()) {
                return new ResponseDTO(false, "Ship detail not found!");
            }
            User user = userOptional.get();
            ShipDetail shipDetail = shipDetailOptional.get();
            if (!Objects.equals(shipDetail.getUser().getId(), user.getId())) {
                return new ResponseDTO(false, "This shipping detail does not exist in your account!");
            }
            shipDetailRepository.delete(shipDetail);
            return new ResponseDTO(true, "Delete ship detail successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDTO(false, "Delete ship detail fail!");
        }
    }
}
