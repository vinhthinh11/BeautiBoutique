package com.example.beautyboutique.Services.ShipDetail;

import com.example.beautyboutique.DTOs.Requests.ShipDetail.ShipDetailDTO;
import com.example.beautyboutique.DTOs.Responses.ResponseDTO;
import com.example.beautyboutique.Models.ShipDetail;

import java.util.List;

public interface ShipDetailService {

    List<ShipDetail> getShipDetailList(Integer userId);

    ResponseDTO createShipDetail(Integer userId, ShipDetailDTO shipDetail);

    ResponseDTO updateShipDetail(Integer userId, Integer shipDetailId, ShipDetailDTO shipDetail);

    ResponseDTO deleteShipDetail(Integer userId, Integer shipDetailId);
}
