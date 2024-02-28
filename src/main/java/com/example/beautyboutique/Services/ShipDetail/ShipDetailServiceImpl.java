package com.example.beautyboutique.Services.ShipDetail;


import com.example.beautyboutique.DTOs.Requests.ShipDetail.ShipDetailDTO;
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
    public Boolean createShipDetail(Integer userId, ShipDetailDTO shipDetailDTO) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return false;
            }
            User user = userOptional.get();
            ShipDetail shipDetail = new ShipDetail(shipDetailDTO.getFullName(), shipDetailDTO.getAddress(), shipDetailDTO.getPhoneNumber(), user);
            shipDetailRepository.save(shipDetail);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateShipDetail(Integer userId, Integer shipDetailId, ShipDetailDTO shipDetailDTO) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return false;
            }
            Optional<ShipDetail> shipDetailOptional = shipDetailRepository.findById(shipDetailId);
            if (shipDetailOptional.isEmpty()) {
                return false;
            }
            User user = userOptional.get();
            ShipDetail shipDetail = shipDetailOptional.get();
            if(!Objects.equals(shipDetail.getUser().getId(), user.getId())) {
                return false;
            }
            shipDetail.setFullName(shipDetailDTO.getFullName());
            shipDetail.setAddress(shipDetailDTO.getAddress());
            shipDetail.setPhoneNumber(shipDetailDTO.getPhoneNumber());
            shipDetailRepository.save(shipDetail);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteShipDetail(Integer userId, Integer shipDetailId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return false;
            }
            Optional<ShipDetail> shipDetailOptional = shipDetailRepository.findById(shipDetailId);
            if (shipDetailOptional.isEmpty()) {
                return false;
            }
            User user = userOptional.get();
            ShipDetail shipDetail = shipDetailOptional.get();
            if(!Objects.equals(shipDetail.getUser().getId(), user.getId())) {
                return false;
            }
            shipDetailRepository.delete(shipDetail);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
